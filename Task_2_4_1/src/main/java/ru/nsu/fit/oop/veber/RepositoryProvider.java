package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Student;

import java.io.File;
import java.util.Collection;
import java.util.List;

@Slf4j
public class RepositoryProvider {
    private final static String REPOSITORY_PATH_PREFIX = "./students/";

    public static Project cloneRepository(Student student) {
        String studentPath = REPOSITORY_PATH_PREFIX + student.getNickname();

        try (
                Git ignored = Git.cloneRepository()
                        .setURI(student.getRepositoryUrl())
                        .setDirectory(new File(studentPath))
                        .call()

        ) {
            log.info("Repository for student {} cloned successfully", student.getNickname());
            Project project = new Project(studentPath, student);
            log.info("Project instance created successfully");
            return project;

        } catch (GitAPIException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Cannot clone repository for student " + student.getNickname());
        } catch (JGitInternalException ex) {
            log.warn("Destination directory path already exists for {}", student.getNickname());
            log.warn("Skipping cloning for student {}", student.getNickname());
            return new Project(studentPath, student);
        }
    }

    public static List<Project> cloneRepository(Collection<Student> students) {
        return students
                .stream()
                .map(RepositoryProvider::cloneRepository)
                .toList();
    }
}
