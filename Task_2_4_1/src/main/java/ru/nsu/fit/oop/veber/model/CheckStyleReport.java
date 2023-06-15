package ru.nsu.fit.oop.veber.model;

import lombok.Data;

@Data
public class CheckStyleReport {
    private String filePath;
    private Integer warningCount;

    public CheckStyleReport(String path) {
        this.filePath = path;
    }
}
