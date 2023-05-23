package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

@Slf4j
public class GradleProvider {
    public static boolean buildProject(String path) {
        if (path == null) {
            log.warn("Path to project was null");
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            log.warn("File with project is not exist");
            return false;
        }
        try (
                ProjectConnection connection = GradleConnector.newConnector()
                        .forProjectDirectory(file)
                        .connect()
        ) {
            log.info("Build was success");
            BuildLauncher build = connection.newBuild();
            build.forTasks("assemble");
            build.run();
            return true;
        } catch (BuildException e) {
            log.warn("Build was failed");
            return false;

        }


    }
}
