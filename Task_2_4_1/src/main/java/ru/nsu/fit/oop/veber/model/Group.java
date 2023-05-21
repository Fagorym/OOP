package ru.nsu.fit.oop.veber.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Group {
    private ArrayList<Student> students;
    private Integer number;
}
