package ru.nsu.fit.oop.veber.subject;

import ru.nsu.fit.oop.veber.grade.Grade;


import java.util.Objects;

public class Subject {
    private final String name;
    private final Grade grade;
    private final SubjectType type;

    public Subject(String name, Grade grade, SubjectType type) {
        this.name = name;
        this.grade = grade;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getGrade() {
        return grade.getGrade();
    }

    public SubjectType getType() {
        return type;
    }


    @Override
    public String toString() {
        return type + " | " + name + " - " + grade + '\n';

    }

}
