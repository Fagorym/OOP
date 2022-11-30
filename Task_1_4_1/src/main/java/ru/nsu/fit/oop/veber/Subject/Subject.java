package ru.nsu.fit.oop.veber.Subject;

import ru.nsu.fit.oop.veber.Grade.GradeEnum;

import java.lang.reflect.Type;
import java.util.Objects;

public class Subject {
    private final String name;
    private final GradeEnum grade;
    private final SubjectType type;

    public Subject(String name, GradeEnum grade, SubjectType type) {
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

    public SubjectType getType(){
        return type;
    }


    @Override
    public String toString() {
        return type + " | " + name + " - " + grade + '\n';

    }

}
