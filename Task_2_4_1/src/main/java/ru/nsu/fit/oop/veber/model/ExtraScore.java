package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExtraScore extends Parcelable {
    private String studentNickname;
    private double extraScore;

    @Override
    public String getConfigPath() {
        return "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/extra.groovy";
    }
}
