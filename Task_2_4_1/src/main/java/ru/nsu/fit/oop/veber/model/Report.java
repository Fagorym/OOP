package ru.nsu.fit.oop.veber.model;

import lombok.Data;

@Data
public class Report {
    private String taskId;
    private boolean wasBuilt;
    private boolean wasTested;
    private boolean hasDocs;
}
