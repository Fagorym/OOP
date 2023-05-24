package ru.nsu.fit.oop.veber.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private String path;
    private Student student;

}
