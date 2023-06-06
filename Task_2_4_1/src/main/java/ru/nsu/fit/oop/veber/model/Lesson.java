package ru.nsu.fit.oop.veber.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Lesson {
    private LocalDate date;

    public Lesson(String date) {
        this.date = LocalDate.parse(date);
    }
}
