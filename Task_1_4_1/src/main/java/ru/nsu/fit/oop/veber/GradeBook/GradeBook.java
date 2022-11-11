package ru.nsu.fit.oop.veber.GradeBook;

//TODO: javadocs

import ru.nsu.fit.oop.veber.Semester.Semester;

public class GradeBook {
    int currentSemester;
    String name;
    String surname;
    String faculty;

    int qualifyWorkGrade;
    Semester[] semesters;

    public GradeBook(String name, String surname, String faculty, int currentSemester) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.currentSemester = currentSemester;
        this.semesters = new Semester[currentSemester];
        for (int i = 0; i < currentSemester; i++) {
            semesters[i] = new Semester(i + 1);
        }
    }

    public float getAvgGrade() {
        float avg = 0;
        for (Semester semester : semesters) {
            avg += semester.getAverageGrade();
        }
        avg /= currentSemester - 1;
        return avg;
    }

    public boolean willBeScolarship() {
        Semester prevSem = semesters[currentSemester - 1];
        for (Object grade : prevSem.getGradesArray()) {
            if ((int) grade < 3) {
                return false;
            }
        }
        return true;
    }

    public boolean willBeRedDiploma() {
        if (qualifyWorkGrade < 5) return false;
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
        if (excellentPercent < 75) return false;
        return true;

    }


    public void addGrade(String subject, int grade, int semesterNumber) {
        semesters[semesterNumber - 1].addGrade(subject, grade);
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("Name: ").append(name).append('\n');
        resultString.append("Surname: ").append(surname).append('\n');
        resultString.append("Faculty: ").append(faculty).append('\n');
        resultString.append("Marks for each semester").append('\n');
        for (Semester semester : semesters) {
            resultString.append(semester.toString());
        }
        return resultString.toString();
    }


}
