package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LessonDto extends Parcelable {
    private final static String CONFIG_PATH = "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/lessons.groovy";

    private String date;
    private List<String> excludedStudentsNicknames;

    public String getConfigPath() {
        return CONFIG_PATH;
    }
}
