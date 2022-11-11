package ru.nsu.fit.pak.Semester;

import java.util.HashMap;

public class Semester {
    private final HashMap<String, Integer> grades;

    private final int gradeSystem;
    private float averageGrade;
    private int subjectCount;


    public Semester(int gradeSystem) {
        this.grades = new HashMap<>();
        averageGrade = 0;
        subjectCount = 0;
        this.gradeSystem = gradeSystem;
    }


    public void addGrade(String subject, Integer grade) {
        if (grade < 1 || grade > gradeSystem) {
            throw new IllegalArgumentException("Grade cannot be less than 1 and greater than your grade system");
        }
        if (grades.containsKey(subject)) {
            subjectCount++;
        }
        grades.put(subject, grade);
        recountAverage();
    }

    private void recountAverage() {
        averageGrade = 0;
        for (Integer grade : grades.values()) {
            averageGrade += grade;
        }
        averageGrade /= subjectCount;
    }


    public float getAverageGrade() {
        return averageGrade;
    }

    public int getSubjectCount() {
        return subjectCount;
    }
}