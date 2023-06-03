package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Task extends Parcelable {

    private static final String CONFIG_PATH = "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/tasks.groovy";
    private String id;
    private String name;
    private int score;
    private boolean isGiven;
    private boolean hasTests;

    public String getConfigPath() {
        return CONFIG_PATH;
    }
}
