package ru.nsu.fit.oop.veber.model;


import lombok.Data;
import org.eclipse.jgit.api.Git;

import java.util.Map;

@Data
public class StudentResults {
    private String path;
    private Student student;
    private Git studentGit;
    private Map<String, Report> taskReports;
    private Map<String, Boolean> dayReports;
    private Integer total;

    public StudentResults(String path, Student student, Git git) {
        this.student = student;
        this.path = path;
        this.studentGit = git;
    }
}
