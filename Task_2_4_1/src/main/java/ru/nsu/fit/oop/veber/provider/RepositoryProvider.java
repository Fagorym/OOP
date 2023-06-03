package ru.nsu.fit.oop.veber.provider;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import ru.nsu.fit.oop.veber.model.Lesson;
import ru.nsu.fit.oop.veber.model.Student;
import ru.nsu.fit.oop.veber.model.StudentResults;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
public class RepositoryProvider {
    private final static String DEFAULT_REPOSITORY_PATH_PREFIX = "./Task_2_4_1/build/students/";

    public static boolean checkDate(Lesson lesson, Git git) {
        try {
            Iterable<RevCommit> commits = git.log().call();
            for (RevCommit commit : commits) {
                PersonIdent authorIdent = commit.getAuthorIdent();
                Date authorDate = authorIdent.getWhen();
                LocalDate commitDate = LocalDate.ofInstant(authorDate.toInstant(), ZoneId.systemDefault());
                if (commitDate.equals(lesson.getDate())) {
                    return true;
                }

            }
            return false;
        } catch (GitAPIException ex) {
            log.error("Cannot check student attendance");
            throw new RuntimeException("Cannot check student attendance");
        }
    }

    public static StudentResults cloneRepository(Student student) {
        String studentPath = DEFAULT_REPOSITORY_PATH_PREFIX + student.getNickname();
        try (
                Git git = Git.cloneRepository()
                        .setURI(student.getRepositoryUrl())
                        .setDirectory(new File(studentPath))
                        .call()

        ) {
            log.info("Repository for student {} cloned successfully", student.getNickname());
            StudentResults results = new StudentResults(studentPath, student, git);
            log.info("Project instance created successfully");
            return results;

        } catch (GitAPIException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Cannot clone repository for student " + student.getNickname());
        } catch (JGitInternalException ex) {
            log.warn("Destination directory path already exists for {}", student.getNickname());
            log.warn("Skipping cloning for student {}", student.getNickname());
            try (Git git = Git.open(new File(studentPath))) {
                return new StudentResults(studentPath, student, git);
            } catch (IOException e) {
                log.error("Cannot open student repository");
                throw new RuntimeException("Cannot open student repository");
            }
        }
    }

    public static List<StudentResults> cloneRepository(Collection<Student> students) {
        return students
                .stream()
                .map(RepositoryProvider::cloneRepository)
                .toList();
    }

}
