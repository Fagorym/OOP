package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import ru.nsu.fit.oop.veber.checker.*;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.parser.Parser;
import ru.nsu.fit.oop.veber.provider.GitProvider;
import ru.nsu.fit.oop.veber.provider.HtmlProvider;
import ru.nsu.fit.oop.veber.provider.ReportProvider;
import ru.nsu.fit.oop.veber.provider.VersionControlProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {
    private final ReportProvider reportProvider;
    private final VersionControlProvider versionControlProvider;
    private final List<Executor> checkExecutors;
    private List<Lesson> lessons;

    private List<TaskDeadline> deadlines;
    private Group group;
    private List<Task> tasks;
    @Parameters(
            description = "Extra student score in syntax: STUDENT_NAME=SCORE_VALUE",
            defaultValue = ""
    )
    @SuppressWarnings({"all"})
    private Map<String, Integer> extraScoreStudents;
    private List<StudentResults> results;


    public ReportApi() {
        lessons = new ArrayList<>();
        parseConfigurationFiles();
        reportProvider = new HtmlProvider();
        versionControlProvider = new GitProvider();
        checkExecutors = List.of(
                new TaskBuilder(),
                new TaskTestChecker(),
                new TaskDocsGenerator(),
                new CheckstyleRunner(),
                new DeadlineChecker(versionControlProvider)
        );
    }

    private void makeReport() {
        log.info("Start making report process");
        reportProvider.generateReport(results);
        log.info("Generating report was success");

    }

    @SuppressWarnings("unchecked")
    private void parseConfigurationFiles() {
        Parser parser = new Parser();
        log.info("Parsing group.");
        this.group = (Group) parser.parse(Group.class);
        log.info("Parsing tasks");
        this.tasks = (List<Task>) parser.parse(Task.class);
        log.info("Parsing lessons");
        lessons = ((List<LessonDto>) parser.parse(LessonDto.class))
                .stream()
                .map(dto -> new Lesson(dto.getDate()))
                .toList();
        log.info("Parsing deadlines");
        deadlines = (List<TaskDeadline>) parser.parse(TaskDeadline.class);
        tasks.forEach(
                task -> deadlines.forEach(
                        deadline -> {
                            if (deadline.getTaskId().equals(task.getId())) {
                                task.setSoftDeadline(LocalDate.parse(deadline.getSoftDeadline()));
                                task.setHardDeadline(LocalDate.parse(deadline.getHardDeadline()));
                            }
                        })
        );
    }


    private void cloneRepositories() {
        log.info("Cloning group repositories");
        this.results = versionControlProvider.cloneRepository(group.getStudents());
        log.info("Repositories cloned successfully");
    }

    @Override
    public void run() {
        cloneRepositories();
        initializeTasksReports();
        initializeDayReports();
        tasks.forEach(this::checkTask);
        checkAttendance();
        countTotal();
        makeReport();
    }

    private void checkAttendance() {
        for (StudentResults result : results) {
            Map<String, Boolean> dayReports = result.getDayReports();
            for (Lesson lesson : lessons) {
                Boolean lessonResult = versionControlProvider.checkLessonAttendance(
                        lesson,
                        result.getStudentGit()
                );
                dayReports.put(
                        lesson.getDate().toString(),
                        lessonResult
                );
            }
        }
    }

    private void countTotal() {
        results.forEach(
                result -> {
                    Collection<Report> reports = result.getTaskReports().values();
                    String studentName = result.getStudent().getNickname();
                    int total = 0;
                    if (extraScoreStudents.containsKey(studentName)
                    ) {
                        total += extraScoreStudents.get(studentName);
                    }
                    for (Report report : reports) {
                        report.setScore(getScore(report));
                        total += report.getScore();
                    }
                    result.setTotal(total);
                }
        );
    }

    private double getScore(Report report) {
        double softScore = report.isWasSoftDeadline() ? 0.5 : 0;
        double hardScore = report.isWasSoftDeadline() ? 0.5 : 0;
        double buildFine = report.isWasBuilt() ? 0.3 : 0;
        double testFine = report.isWasTested() ? 0.1 : 0;
        double docsFine = report.isHasDocs() ? 0.1 : 0;
        return Math.max(softScore + hardScore - buildFine - testFine - docsFine, 0);
    }

    private void checkTask(Task task) {
        if (task.isGiven()) {
            checkExecutors.forEach(
                    executor -> {
                        log.info(
                                "Executing {} for task {}",
                                executor.getName(),
                                task.getId()
                        );
                        executor.execute(results, task);
                    }
            );
        } else {
            results.forEach(
                    result -> {
                        Report currentReport = result.getTaskReports().get(task.getId());
                        currentReport.setWasTested(true);
                        currentReport.setHasDocs(true);
                        currentReport.setWasBuilt(true);
                    }
            );
        }
    }

    private void initializeDayReports() {
        results.forEach(
                result -> lessons.forEach(
                        lesson -> result.getDayReports().put(lesson.getDate().toString(), false)
                )
        );
    }

    private void initializeTasksReports() {
        results.forEach(
                result -> tasks.forEach(
                        task -> result.getTaskReports().put(task.getId(), new Report())
                )
        );

    }
}
