package ru.nsu.fit.oop.veber.grade;

/**
 * Enum for binary grade.
 */
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
