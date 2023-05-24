package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.model.Group;
import ru.nsu.fit.oop.veber.model.Project;

import java.util.List;

@Command(name = "-make")
@Slf4j
public class ReportApi implements Runnable {

    private final Group group;
    private List<Project> projectList;

    public ReportApi() {
        log.info("Parsing group instance from config.");
        this.group = new Group().parse(Group.getConfigPath());
        log.info("Group {} was parsed", group.getNumber());
    }

    private void checkTests() {
        log.info("Start test checking process");
        GradleProvider.buildProject(projectList);
        log.info("Test checking was success");
    }

    private void generateDocs() {
        log.info("Start generating javadocs process");
        // TODO: Generate docs
        log.info("Generating javadocs was success");

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
        checkTests();
        generateDocs();
        makeReport();
    }
}
