package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import ru.nsu.fit.oop.veber.model.Project;

import java.io.File;
import java.util.Collection;

@Slf4j
public class GradleProvider {
    public static void buildProject(Project project) {
        if (project.getPath() == null) {
            log.warn("Path to project was null");
            return;
        }
        File file = new File(project.getPath());
        if (!file.exists()) {
            log.warn("File with project is not exist");
            return;
        }
        try (
                ProjectConnection connection = GradleConnector.newConnector()
                        .forProjectDirectory(file)
                        .connect()
        ) {
            log.info("Connection was success for {}", project.getStudent());
            BuildLauncher build = connection.newBuild();
            build.forTasks("assemble");
            build.run();
        } catch (BuildException e) {
            log.warn("Build was failed for {}", project.getStudent());
        }
    }

    public static void buildProject(Collection<Project> projects) {
        projects.forEach(GradleProvider::buildProject);
    }
}
