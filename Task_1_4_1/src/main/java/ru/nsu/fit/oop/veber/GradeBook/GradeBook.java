package ru.nsu.fit.oop.veber.GradeBook;

//TODO: javadocs

import ru.nsu.fit.oop.veber.Semester.Semester;

import java.util.Arrays;

public class GradeBook {
    int completedSemesters;
    String name;
    String surname;
    String faculty;

    int graduateWorkGrade;
    Semester[] semesters;

    public GradeBook(String name, String surname, String faculty, int currentSemester) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.completedSemesters = currentSemester;
        this.semesters = new Semester[completedSemesters];
        for (int i = 0; i < completedSemesters; i++) {
            semesters[i] = new Semester(i + 1);
        }
    }

    public float getAvgGrade() {
        float avg = 0;
        for (Semester semester : semesters) {
            avg += semester.getAverageGrade();
        }
        avg /= completedSemesters;
        int scale = 10;
        return (float) (Math.ceil(avg * scale) / scale);
    }

    public boolean willBeScolarship() {
        Semester prevSem = semesters[completedSemesters - 1];
        for (Object grade : prevSem.getGradesArray()) {
            if ((int) grade <= 3) {
                return false;
            }
        }
        return true;
    }

    public void setQualifyWorkGrade(int grade) {
        if (completedSemesters < 8) throw
                new IllegalStateException("You cannot pass qualified work before 8 semester");
        if (grade < 2 || grade > 5) throw
                new IllegalArgumentException("Grade cannot be less than 1 and great than  5");
        this.graduateWorkGrade = grade;
    }

    public boolean willBeRedDiploma() {
        if (graduateWorkGrade < 5) return false;
        int totalGradesCount = 0;
        int excellentGradesCount = 0;
        for (Semester semester : semesters) {
            for (Object obj : semester.getGradesArray()) {
                int grade = (int) obj;
                if (grade <= 3) {
                    return false;
                }
                if (grade == 5) {
                    excellentGradesCount++;
                }
                totalGradesCount++;

            }
        }

        float excellentPercent = (float) excellentGradesCount / totalGradesCount * 100;
        return !(excellentPercent < 75);

    }


    public void addGrade(String subject, int grade, int semesterNumber) {
        semesters[semesterNumber - 1].addGrade(subject, grade);
    }

    public int getSubjectsCount(int semesterNumber) {
        return semesters[semesterNumber - 1].getSubjectCount();
    }

    public boolean increaseSemester() {
            completedSemesters++;
            Semester[] newArray = new Semester[completedSemesters];
            newArray[completedSemesters - 1] = new Semester(completedSemesters);
            System.arraycopy(semesters, 0, newArray, 0, completedSemesters - 1);
            this.semesters = newArray;
            return true;
    }
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("Name: ").append(name).append('\n');
        resultString.append("Surname: ").append(surname).append('\n');
        resultString.append("Faculty: ").append(faculty).append('\n');
        resultString.append("Total average grade: ").append(getAvgGrade()).append("\n");
        resultString.append("Red diploma: ");
        if (willBeRedDiploma()) {
            resultString.append("Yes");
        } else {
            resultString.append("No");
        }
        resultString.append("\n");
        for (Semester semester : semesters) {
            resultString.append(semester.toString());
        }
        return resultString.toString();
    }


}
