package ru.nsu.fit.oop.veber.checker;

import lombok.extern.slf4j.Slf4j;
import org.gradle.tooling.*;
import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.Task;

import java.io.File;

@Slf4j
public abstract class TaskExecutor {

    protected static void makeTask(Project project, String taskName, Task task, Report report) {
        if (project.getPath() == null) {
            log.error("Path to project was not specified. Check configuration file.");
            throw new RuntimeException();
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
            build.addProgressListener((ProgressListener) msg -> log.debug(msg.getDescription()));
            build.run();
            log.info("Executing task {} was success for {}", taskName, project.getStudent().getFullName());
        } catch (BuildException e) {
            log.warn("Build was failed for {}", project.getStudent());
        }
    }

}
