package ru.nsu.fit.oop.veber.Grade;

/**
 * Enum for grades, that can be quoted in five point system.
 */
public enum FivePointGrade implements Grade {
    FAILED(2),
    SATISFYING(3),
    GOOD(4),
    EXCELLENT(5);
    private final int grade;

    FivePointGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
