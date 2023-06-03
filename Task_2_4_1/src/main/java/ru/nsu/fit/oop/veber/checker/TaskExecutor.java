package ru.nsu.fit.oop.veber.checker;

import lombok.extern.slf4j.Slf4j;
import org.gradle.tooling.*;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import java.io.File;

@Slf4j
public abstract class TaskExecutor implements Executor {

    protected boolean makeTask(StudentResults project, String taskName, Task task) {
        if (project.getPath() == null) {
            log.error("Path to project was not specified. Check configuration file.");
            return false;
        }
        File file = new File(project.getPath() + "/" + task.getId());

        if (!file.exists()) {
            log.warn(
                    "File with task {} is not exist for student {}",
                    task.getId(),
                    project.getStudent().getNickname()
            );
            return false;
        }

        try (
                ProjectConnection connection = GradleConnector.newConnector()
                        .forProjectDirectory(file)
                        .connect()
        ) {
            log.info("Connection was success for {}", file.getPath());
            BuildLauncher build = connection.newBuild();
            build.forTasks(taskName);
            build.addProgressListener((ProgressListener) msg -> log.debug(msg.getDescription()));
            build.run();
            log.info("Executing task {} was success for {}", taskName, project.getStudent().getFullName());
            return true;
        } catch (BuildException e) {
            log.warn("Build was failed for {}", project.getStudent());
            return false;
        }
    }

}
