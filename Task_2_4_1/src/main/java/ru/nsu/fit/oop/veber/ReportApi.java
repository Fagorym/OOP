package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.checker.TaskBuilder;
import ru.nsu.fit.oop.veber.checker.TaskDocsGenerator;
import ru.nsu.fit.oop.veber.checker.TaskTestChecker;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.provider.HtmlProvider;
import ru.nsu.fit.oop.veber.provider.RepositoryProvider;

import java.util.*;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {

    private final Group group;

    private final List<Lesson> lessons;
    private final List<Task> tasks;
    private final TaskBuilder taskBuilder;
    private final TaskDocsGenerator taskDocsGenerator;
    private final TaskTestChecker taskTestChecker;
    private final HtmlProvider htmlProvider;
    private List<StudentResults> results;

    public ReportApi() {
        log.info("Parsing group instance from config.");
        this.group = new Group().parse(Group.getConfigPath());
        log.info("Group {} was parsed", group.getNumber());
        log.info("Parsing tasks");
        this.tasks = new Task().parse(Task.getConfigPath());
        log.info("Tasks {} was parsed", tasks);
        log.info("Parsing lessons");
        this.lessons = new Lesson().parse(Lesson.getConfigPath());
        log.info("Lessons {} was parsed", lessons);
        taskBuilder = new TaskBuilder();
        taskTestChecker = new TaskTestChecker();
        taskDocsGenerator = new TaskDocsGenerator();
        htmlProvider = new HtmlProvider();
        results = new ArrayList<>();
    }

    private void buildProjects(Task task) {
        log.info("Start task {} building process", task.getId());
        taskBuilder.buildProject(results, task);
        log.info("Building process for task {} was success", task.getId());
    }

    private void checkTests(Task task) {
        log.info("Start task {} test checking process", task.getId());
        taskTestChecker.checkTasks(results, task);
        log.info("Test task {} checking was success", task.getId());
    }

    private void generateDocs(Task task) {
        log.info("Start generating javadocs process for task {}", task.getId());
        taskDocsGenerator.generateDocs(results, task);
        log.info("Generating javadocs was success for task {}", task.getId());
    }

    private void makeReport() {
        log.info("Start making report process");
        htmlProvider.generateHtml(results);
        log.info("Generating report was success");

    }


    private void cloneRepositories() {
        log.info("Cloning group repositories");
        this.results = RepositoryProvider.cloneRepository(group.getStudents());
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
                Boolean lessonResult = RepositoryProvider.checkDate(lesson, result.getStudentGit());
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
                    int total = 0;
                    for (Report report : reports) {
                        report.setScore(getScore(report));
                        total += report.getScore();
                    }
                    result.setTotal(total);
                }
        );
    }

    private int getScore(Report report) {
        return (report.isHasDocs() ? 1 : 0) * (report.isWasTested() ? 1 : 0) *
                (report.isWasBuilt() ? 1 : 0);
    }

    private void checkTask(Task task) {
        if (task.isGiven()) {
            buildProjects(task);
            checkTests(task);
            generateDocs(task);
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
