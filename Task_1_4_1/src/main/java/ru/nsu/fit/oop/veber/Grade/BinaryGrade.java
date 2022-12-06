package ru.nsu.fit.oop.veber.Grade;

public enum BinaryGrade implements Grade {
    NOT_PASSED(0),
    PASSED(5);
    private final int grade;

    BinaryGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public int getGrade() {
        return this.grade;
    }
}
