package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DirectoryConfig extends Parcelable {
    private String studentName;

    private List<DirectoryDto> directoryDto;

    @Override
    public String getConfigPath() {
        return "Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config/directories.groovy";
    }
}
