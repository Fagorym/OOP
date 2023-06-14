package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.checker.*;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.parser.Parser;
import ru.nsu.fit.oop.veber.provider.GitProvider;
import ru.nsu.fit.oop.veber.provider.HtmlProvider;
import ru.nsu.fit.oop.veber.provider.ReportProvider;
import ru.nsu.fit.oop.veber.provider.VersionControlProvider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private List<ExtraScore> extraScores;
    private Group group;
    private List<Task> tasks;
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
        log.info("Parsing extra scores");
        extraScores = (List<ExtraScore>) parser.parse(ExtraScore.class);
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
        //checkAttendance();
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
                    double total = 0;
                    ExtraScore extra = extraScores
                            .stream()
                            .filter(score -> score.getStudentNickname().equals(studentName))
                            .findFirst()
                            .orElse(null);
                    if (extra != null) {
                        total += extra.getExtraScore();
                    }
                    for (Report report : reports) {
                        report.setScore(getScore(report, result));
                        total += report.getScore();
                    }
                    result.setTotal(total);
                }
        );
    }

    private double getScore(Report report, StudentResults result) {
        double softScore = report.isWasSoftDeadline() ? 0.5 : 0;
        double hardScore = report.isWasSoftDeadline() ? 0.5 : 0;
        double buildFine = report.isWasBuilt() ? 0 : 0.5;
        double testFine = report.isWasTested() ? 0 : 0.3;
        double docsFine = report.isHasDocs() ? 0 : 0.1;
        CheckStyleReport checkStyle = result.getCheckStyleReport().get(report.getTaskId());
        if (checkStyle == null) {
            checkStyle = new CheckStyleReport(null);
        }
        countWarnings(checkStyle);
        double styleFine =
                (checkStyle.getWarningCount() == null || checkStyle.getWarningCount() > 150) ? 0.05 : 0;
        result.getCheckStyleReport().put(report.getTaskId(), checkStyle);
        String testCoverage = result.getTestCoverage().get(report.getTaskId());
        double coverageFine =
                (testCoverage == null || Integer.parseInt(testCoverage.substring(0, 2)) < 80) ? 0.05 : 0;

        return Math.max(softScore + hardScore - buildFine - testFine - docsFine - styleFine - coverageFine, 0);
    }

    private void countWarnings(CheckStyleReport checkStyle) {
        Integer warningCount = 0;
        String fileCheckStyleName = checkStyle.getFilePath();
        if (fileCheckStyleName == null) {
            checkStyle.setWarningCount(null);
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileCheckStyleName)
                    )
            );
            while (reader.ready()) {
                warningCount++;
                reader.readLine();
            }
            checkStyle.setWarningCount(warningCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
