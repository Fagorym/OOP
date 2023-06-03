package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.checker.TaskBuilder;
import ru.nsu.fit.oop.veber.checker.TaskDocsGenerator;
import ru.nsu.fit.oop.veber.checker.TaskTestChecker;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.provider.HtmlProvider;
import ru.nsu.fit.oop.veber.provider.RepositoryProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {

    private final Group group;

    private final List<Lesson> lessons;
    private final Map<String, Map<String, Report>> reports;

    private final Map<String, DayReport> dayReportMap;
    private final List<Task> tasks;
    private final TaskBuilder taskBuilder;
    private final TaskDocsGenerator taskDocsGenerator;
    private final TaskTestChecker taskTestChecker;
    private final HtmlProvider htmlProvider;
    private List<Project> projectList;

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
        reports = new HashMap<>(group.getStudents().size());
        for (Task task : tasks) {
            Map<String, Report> studentReports = new HashMap<>();
            for (Student student : group.getStudents()) {
                studentReports.put(student.getNickname(), new Report());
            }
            reports.put(task.getId(), studentReports);
        }
        dayReportMap = new HashMap<>();
        taskBuilder = new TaskBuilder();
        taskTestChecker = new TaskTestChecker();
        taskDocsGenerator = new TaskDocsGenerator();
        htmlProvider = new HtmlProvider();
    }

    private void buildProjects(Task task) {
        log.info("Start task {} building process", task.getId());
        taskBuilder.buildProject(projectList, task, reports);
        log.info("Building process for task {} was success", task.getId());
    }

    private void checkTests(Task task) {
        log.info("Start task {} test checking process", task.getId());
        taskTestChecker.checkTasks(projectList, task, reports);
        log.info("Test task {} checking was success", task.getId());
    }

    private void generateDocs(Task task) {
        log.info("Start generating javadocs process for task {}", task.getId());
        taskDocsGenerator.generateDocs(projectList, task, reports);
        log.info("Generating javadocs was success for task {}", task.getId());
    }

    private void makeReport() {
        log.info("Start making report process");
        htmlProvider.generateHtml(reports);
        log.info("Generating report was success");

    }


    private void cloneRepositories() {
        log.info("Cloning group repositories");
        this.projectList = RepositoryProvider.cloneRepository(
                group.getStudents(),
                lessons,
                dayReportMap
        );
        log.info("Repositories cloned successfully");
    }

    @Override
    public void run() {
        cloneRepositories();
        for (Task task : tasks) {
            if (task.isGiven()) {
                buildProjects(task);
                checkTests(task);
                generateDocs(task);
            } else {
                reports.get(task.getId()).values().forEach(
                        curTask -> {
                            curTask.setWasTested(true);
                            curTask.setWasBuilt(true);
                            curTask.setHasDocs(true);
                        }
                );

            }
        }
        reports.values().forEach(
                reports -> reports.values().forEach(
                        report -> report.setScore(
                                (report.isHasDocs() ? 1 : 0) * (report.isWasTested() ? 1 : 0) *
                                        (report.isWasBuilt() ? 1 : 0)
                        )
                )
        );
        makeReport();
    }
}
