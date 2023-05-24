package ru.nsu.fit.oop.veber.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Parcelable {
    private ArrayList<Student> students;
    private Integer number;
}
