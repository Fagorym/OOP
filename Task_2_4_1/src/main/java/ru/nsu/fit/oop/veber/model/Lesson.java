package ru.nsu.fit.oop.veber.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Lesson {
    private LocalDate date;
    private List<String> excludedStudents;

    public Lesson(String date, List<String> excludedStudents) {
        this.date = LocalDate.parse(date);
        this.excludedStudents = excludedStudents;
    }
}
