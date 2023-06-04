package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import ru.nsu.fit.oop.veber.checker.Executor;
import ru.nsu.fit.oop.veber.checker.TaskBuilder;
import ru.nsu.fit.oop.veber.checker.TaskDocsGenerator;
import ru.nsu.fit.oop.veber.checker.TaskTestChecker;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.parser.Parser;
import ru.nsu.fit.oop.veber.provider.GitProvider;
import ru.nsu.fit.oop.veber.provider.HtmlProvider;
import ru.nsu.fit.oop.veber.provider.ReportProvider;
import ru.nsu.fit.oop.veber.provider.VersionControlProvider;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {
    private final Group group;
    private final List<Lesson> lessons;
    private final List<Task> tasks;
    private final ReportProvider reportProvider;
    private final VersionControlProvider versionControlProvider;

    private final List<Executor> checkExecutors;
    @Parameters(
            description = "Extra student score in syntax: STUDENT_NAME=SCORE_VALUE",
            defaultValue = ""
    )
    @SuppressWarnings({"all"})
    private Map<String, Integer> extraScoreStudents;
    private List<StudentResults> results;

    @SuppressWarnings("unchecked")
    public ReportApi() {
        Parser parser = new Parser();
        log.info("Parsing group.");
        this.group = (Group) parser.parse(Group.class);
        log.info("Parsing tasks");
        this.tasks = (List<Task>) parser.parse(Task.class);
        log.info("Parsing lessons");
        this.lessons = (List<Lesson>) parser.parse(Lesson.class);
        checkExecutors = List.of(
                new TaskBuilder(),
                new TaskTestChecker(),
                new TaskDocsGenerator()
        );
        reportProvider = new HtmlProvider();
        versionControlProvider = new GitProvider();
    }

    private void makeReport() {
        log.info("Start making report process");
        reportProvider.generateReport(results);
        log.info("Generating report was success");

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

    private int getScore(Report report) {
        return (report.isHasDocs() ? 1 : 0) *
                (report.isWasTested() ? 1 : 0) *
                (report.isWasBuilt() ? 1 : 0);
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
                result -> {
                    Map<String, Boolean> dayReports = new HashMap<>();
                    lessons.forEach(
                            lesson -> dayReports.put(lesson.getDate().toString(), false)
                    );
                    result.setDayReports(dayReports);
                }
        );
    }

    private void initializeTasksReports() {
        results.forEach(
                result -> {
                    Map<String, Report> reportMap = new HashMap<>();
                    tasks.forEach(
                            task -> reportMap.put(task.getId(), new Report())
                    );
                    result.setTaskReports(reportMap);
                }
        );

    }
}
