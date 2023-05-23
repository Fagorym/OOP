package ru.nsu.fit.oop.veber;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.fit.oop.veber.model.Student;

import java.io.File;

@Slf4j
public class RepositoryProvider {
    public static void cloneRepository(Student student) {
        try (
                Git git = Git.cloneRepository()
                        .setURI(student.getRepositoryUrl())
                        .setDirectory(new File("/students/"))
                        .call()
        ) {
            log.info("Repository for student {} cloned successfully", student.getNickname());

        } catch (GitAPIException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Cannot clone repository for student " + student.getNickname());
        }
    }
}
