package ru.nsu.fit.oop.veber.Semester;

import java.util.HashMap;

public class Semester {
    private final HashMap<String, Integer> grades;

    private final int semesterNumber;
    private float averageGrade;
    private int subjectCount;


    public Semester(int semesterNumber) {
        this.grades = new HashMap<>();
        averageGrade = 0;
        subjectCount = 0;
        this.semesterNumber = semesterNumber;
    }


    public void addGrade(String subject, Integer grade) {
        if (grade < 1 || grade > 5) {
            throw new IllegalArgumentException("Grade cannot be less than 1 and greater than 5");
        }
        if (!grades.containsKey(subject)) {
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

    public Object[] getGradesArray(){
        return this.grades.values().toArray();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Semester number ").append(semesterNumber).append('\n');
        stringBuilder.append("================================").append('\n');
        for (String subject: grades.keySet()){
            stringBuilder.append(subject).append(" - ").append(grades.get(subject)).append('\n');
        }
        stringBuilder.append("================================").append('\n');
        return stringBuilder.toString();
    }
}