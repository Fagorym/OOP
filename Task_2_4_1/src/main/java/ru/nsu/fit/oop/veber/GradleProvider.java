package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Task;

import java.io.File;
import java.util.Collection;
import java.util.List;

@Slf4j
public class GradleProvider {
    public static void buildProject(Project project, Task task) {
        makeTask(project, "build", task);
    }

    public static void buildProject(Collection<Project> projects, Task task) {
        projects.forEach(project -> buildProject(project, task));
    }

    public static void generateDocs(Collection<Project> projects, Task task) {
        projects.forEach(project -> generateDocs(project, task));
    }

    public static void generateDocs(Project project, Task task) {
        makeTask(project, "javadoc", task);

    }

    public static void checkTasks(List<Project> projects, Task task) {
        projects.forEach(project -> checkTasks(project, task));
    }

    public static void checkTasks(Project project, Task task) {
        makeTask(project, "test", task);
    }

    private static void makeTask(Project project, String taskName, Task task) {
        if (project.getPath() == null) {
            log.warn("Path to project was null");
            return;
        }
        File file = new File(project.getPath() + "/" + task.getId());
        if (!file.exists()) {
            log.warn(
                    "File with task {} is not exist for student {}",
                    task.getId(),
                    project.getStudent().getNickname()
            );
            return;
        }
        try (
                ProjectConnection connection = GradleConnector.newConnector()
                        .forProjectDirectory(file)
                        .connect()
        ) {
            log.info("Connection was success for {}", project.getStudent());
            BuildLauncher build = connection.newBuild();
            build.forTasks(taskName);
            build.run();
            log.info("Executing task {} was success for {}", taskName, project.getStudent().getFullName());
        } catch (BuildException e) {
            log.warn("Build was failed for {}", project.getStudent());
        }
    }
}
