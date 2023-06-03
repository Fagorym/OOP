package ru.nsu.fit.oop.veber.provider;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import ru.nsu.fit.oop.veber.model.DayReport;
import ru.nsu.fit.oop.veber.model.Lesson;
import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Student;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class RepositoryProvider {
    private final static String DEFAULT_REPOSITORY_PATH_PREFIX = "./Task_2_4_1/build/students/";

    public static boolean checkDate(Lesson lesson, Git git) throws GitAPIException {

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
    }

    public static Project cloneRepository(
            Student student,
            List<Lesson> lessons,
            Map<String, DayReport> dayReportMap
    ) {
        String studentPath = DEFAULT_REPOSITORY_PATH_PREFIX + student.getNickname();
        try (
                Git git = Git.cloneRepository()
                        .setURI(student.getRepositoryUrl())
                        .setDirectory(new File(studentPath))
                        .call()

        ) {
            DayReport dayReport = new DayReport();
            for (Lesson lesson : lessons) {
                dayReport.getWasOnLesson().put(lesson, checkDate(lesson, git));
            }
            dayReportMap.put(student.getNickname(), dayReport);
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

    public static List<Project> cloneRepository(Collection<Student> students,
                                                List<Lesson> lessons,
                                                Map<String, DayReport> dayReportMap) {
        return students
                .stream()
                .map(student -> cloneRepository(student, lessons, dayReportMap))
                .toList();
    }

}
