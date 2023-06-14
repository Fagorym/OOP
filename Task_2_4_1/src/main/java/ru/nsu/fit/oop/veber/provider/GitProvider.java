package ru.nsu.fit.oop.veber.provider;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
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
import java.util.Map;
import java.util.function.BiPredicate;

@Slf4j
public class GitProvider implements VersionControlProvider {
    private final static String DEFAULT_REPOSITORY_PATH_PREFIX = "./Task_2_4_1/build/students/";

    public boolean checkLessonAttendance(Lesson lesson, Git git) {
        try {
            Collection<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            boolean accumulator = false;
            for (Ref branch : branches) {
                accumulator |= checkBranchForCommitDate(branch, git, lesson);
            }
            checkoutToDefault(git);
            return accumulator;
        } catch (GitAPIException ex) {
            log.error("Cannot check student attendance");
            checkoutToDefault(git);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Map<String, LocalDate> getBorderCommitsDate(String branchName, StudentResults results) {
        try {

            Git git = results.getStudentGit();
            Collection<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            var checkoutBranch = branches.stream().filter(branch -> branch.getName().contains(branchName)).findFirst().orElse(null);
            RevCommit firstCommit, lastCommit;
            if (checkoutBranch == null) {
                String directory = branchName.replace("t", "T").replace("-", "_");
                lastCommit = getByPredicate(git.log().addPath(directory).call(), LocalDate::isAfter);
                firstCommit = getByPredicate(git.log().addPath(directory).call(), LocalDate::isBefore);
            } else {
                git.checkout().setName(checkoutBranch.getName()).call();
                lastCommit = getByPredicate(git.log().call(), LocalDate::isAfter);
                firstCommit = getByPredicate(git.log().call(), LocalDate::isBefore);
            }
            checkoutToDefault(git);
            log.info("Dates for student {}", results.getStudent().getNickname());
            log.info(getCommitDate(firstCommit).toString());
            log.info(getCommitDate(lastCommit).toString());
            return Map.of("first", getCommitDate(firstCommit),
                    "last", getCommitDate(lastCommit));

        } catch (Exception e) {
            log.warn("Exception while parsing branch");
            log.warn(e.getMessage());
            return Map.of("first", LocalDate.MAX,
                    "last", LocalDate.MAX);
        }
    }

    private RevCommit getByPredicate(Iterable<RevCommit> commits, BiPredicate<LocalDate, LocalDate> datePredicate) {
        RevCommit lastCommit = commits.iterator().next();
        for (RevCommit commit : commits) {
            LocalDate lastCommitDate = getCommitDate(lastCommit);
            LocalDate currentCommitDate = getCommitDate(commit);
            if (datePredicate.test(lastCommitDate, currentCommitDate)) {
                lastCommit = commit;
            }
        }
        return lastCommit;
    }

    private LocalDate getCommitDate(RevCommit commit) {
        PersonIdent authorIdent = commit.getAuthorIdent();
        Date authorDate = authorIdent.getWhen();
        return LocalDate.ofInstant(authorDate.toInstant(), ZoneId.systemDefault());
    }

    private void checkoutToDefault(Git git) {
        try {
            git.checkout().setName("main").call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkBranchForCommitDate(Ref branch, Git git, Lesson lesson) {
        try {
            git.checkout().setName(branch.getName()).call();
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
        } catch (Exception e) {
            return false;
        }
    }

    public StudentResults cloneRepository(Student student) {
        String studentPath = DEFAULT_REPOSITORY_PATH_PREFIX + student.getNickname();
        try (
                Git git = Git.cloneRepository()
                        .setCloneAllBranches(true)
                        .setURI(student.getRepositoryUrl())
                        .setDirectory(new File(studentPath))
                        .call()

        ) {
            log.info("Repository for student {} cloned successfully", student.getNickname());
            StudentResults results = new StudentResults(studentPath, student, git);
            log.info("Result instance created successfully");
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

    public List<StudentResults> cloneRepository(Collection<Student> students) {
        return students
                .stream()
                .map(this::cloneRepository)
                .toList();
    }

}
