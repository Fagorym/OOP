package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskDeadline extends Parcelable {
    private String taskId;
    private String softDeadline;
    private String hardDeadline;

    @Override
    public String getConfigPath() {
        return "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/deadlines.groovy";
    }
}
