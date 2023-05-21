package ru.nsu.fit.oop.veber;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import ru.nsu.fit.oop.veber.model.Group;
import ru.nsu.fit.oop.veber.model.Student;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        try {
            File scriptFile = new File("Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config.groovy");
            Object parsedConfig = shell.evaluate(scriptFile);

            if (parsedConfig instanceof Group group) {
                System.out.println("Group number: " + group.getNumber());
                System.out.println("Student's nickname: " + group.getStudents());
                ArrayList<Student> students = group.getStudents();
                Student first = students.get(0);
                System.out.println(first.getFullName());
                System.out.println(first.getNickname());
                System.out.println(first.getRepositoryUrl());
            } else {
                System.out.println("config.groovy didn't return a Group object");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}