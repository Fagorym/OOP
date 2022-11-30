package ru.nsu.fit.oop.veber.Grade;

public enum GradeEnum {
    FAILED(0),
    PASSED(5),
    SATISFYING(3),
    GOOD(4),
    EXCELLENT(5);

    final int grade;

    GradeEnum(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
