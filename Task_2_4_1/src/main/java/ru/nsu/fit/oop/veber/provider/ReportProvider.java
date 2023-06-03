package ru.nsu.fit.oop.veber.provider;

import ru.nsu.fit.oop.veber.model.StudentResults;

import java.util.List;

public interface ReportProvider {
    void generateReport(List<StudentResults> results);
}
