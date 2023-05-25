package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.checker.TaskBuilder;
import ru.nsu.fit.oop.veber.checker.TaskDocsGenerator;
import ru.nsu.fit.oop.veber.checker.TaskTestChecker;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.provider.RepositoryProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {

    private final Group group;
    private final Map<String, Map<String, Report>> reports;
    private final List<Task> tasks;

    private List<Project> projectList;

    public ReportApi() {
        log.info("Parsing group instance from config.");
        this.group = new Group().parse(Group.getConfigPath());
        log.info("Group {} was parsed", group.getNumber());
        log.info("Parsing tasks");
        this.tasks = new Task().parse(Task.getConfigPath());
        log.info("Tasks {} was parsed", tasks);
        reports = new HashMap<>(group.getStudents().size());
        for (Task task : tasks) {
            Map<String, Report> studentReports = new HashMap<>();
            for (Student student : group.getStudents()) {
                studentReports.put(student.getNickname(), new Report());
            }
            reports.put(task.getId(), studentReports);
        }
    }

    private void buildProjects() {
        for (Task task : tasks) {
            log.info("Start task {} building process", task.getId());
            TaskBuilder.buildProject(projectList, task, reports);
            log.info("Building process for task {} was success", task.getId());
        }
    }

    private void checkTests() {
        for (Task task : tasks) {
            log.info("Start task {} test checking process", task.getId());
            TaskTestChecker.checkTasks(projectList, task, reports);
            log.info("Test task {} checking was success", task.getId());
        }
    }

    private void generateDocs() {
        for (Task task : tasks) {
            log.info("Start generating javadocs process for task {}", task.getId());
            TaskDocsGenerator.generateDocs(projectList, task, reports);
            log.info("Generating javadocs was success for task {}", task.getId());
        }

    }

    private void makeReport() {
        log.info("Start making report process");
        // TODO: Making report
        log.info("Generating report was success");

    }


    private void cloneRepositories() {
        log.info("Cloning group repositories");
        this.projectList = RepositoryProvider.cloneRepository(group.getStudents());
        log.info("Repositories cloned successfully");
    }

    @Override
    public void run() {
        cloneRepositories();
        buildProjects();
        checkTests();
        generateDocs();
        makeReport();
    }
}
