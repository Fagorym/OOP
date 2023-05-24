package ru.nsu.fit.oop.veber;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.model.Group;
import ru.nsu.fit.oop.veber.model.Project;

import java.util.List;

@Command(name = "-make")
@NoArgsConstructor
@Slf4j
public class ReportApi implements Runnable {

    public void makeReport() {
        log.info("Parsing group instance from config.");
        Group group = new Group().parse();
        log.info("Group {} was parsed", group.getNumber());
        log.info("Cloning group repositories");
        List<Project> projectList = RepositoryProvider.cloneRepository(group.getStudents());
        log.info("Repositories cloned successfully");
        GradleProvider.buildProject(projectList);
        log.info("Projects was built");
    }

    @Override
    public void run() {
        makeReport();
    }
}
