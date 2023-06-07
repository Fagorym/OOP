package ru.nsu.fit.oop.veber.model;


import lombok.Data;
import org.eclipse.jgit.api.Git;

import java.util.HashMap;
import java.util.Map;

@Data
public class StudentResults {
    private String path;
    private Student student;
    private Git studentGit;
    private Map<String, Report> taskReports = new HashMap<>();
    private Map<String, Boolean> dayReports = new HashMap<>();
    private Map<String, String> testCoverage = new HashMap<>();
    private Map<String, String> checkStyleReport = new HashMap<>();
    private Integer total;

    public StudentResults(String path, Student student, Git git) {
        this.student = student;
        this.path = path;
        this.studentGit = git;
    }
}
