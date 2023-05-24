package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Parcelable {
    private final static String CONFIG_PATH = "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/config.groovy";
    private List<Student> students;
    private List<Task> tasks;
    private Integer number;

    public static String getConfigPath() {
        return CONFIG_PATH;
    }
}