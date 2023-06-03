package ru.nsu.fit.oop.veber.model;


import lombok.Data;
import org.eclipse.jgit.api.Git;

import java.util.List;

@Data
public class StudentInfo {
    private Student student;
    private Git studentGit;
    private List<Report> taskReports;
    private List<DayReport> dayReports;
}
