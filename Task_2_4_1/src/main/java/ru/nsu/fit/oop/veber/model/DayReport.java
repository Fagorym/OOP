package ru.nsu.fit.oop.veber.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DayReport {
    private Map<Lesson, Boolean> wasOnLesson;

    public DayReport() {
        wasOnLesson = new HashMap<>();
    }

}
