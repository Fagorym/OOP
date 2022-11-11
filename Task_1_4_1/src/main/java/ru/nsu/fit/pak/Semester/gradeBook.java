package ru.nsu.fit.pak.Semester;

import java.util.ArrayList;
import java.util.List;

public class gradeBook {
    int currentSemester;
    String name;
    String surname;
    String faculty;
    Semester[] semesters;

    public gradeBook(String name, String surname, String faculty, int gradeSystem, int currentSemester) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.currentSemester = currentSemester;
        this.semesters = new Semester[currentSemester];
        for (int i = 0; i < currentSemester; i++) {
            semesters[i] = new Semester(gradeSystem);
        }
    }
    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();
        resultString.append("Name: ").append(name).append('\n');
        resultString.append("Surname: ").append(surname).append('\n');
        resultString.append("Faculty: ").append(faculty).append('\n');
    }

}
