package ru.nsu.fit.oop.veber.GradeBook;

//TODO: javadocs

import ru.nsu.fit.oop.veber.Semester.Semester;

public class GradeBook {
    int completedSemestres;
    String name;
    String surname;
    String faculty;

    int qualifyWorkGrade;
    Semester[] semesters;

    public GradeBook(String name, String surname, String faculty, int currentSemester) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.completedSemestres = currentSemester;
        this.semesters = new Semester[completedSemestres];
        for (int i = 0; i < completedSemestres; i++) {
            semesters[i] = new Semester(i + 1);
        }
    }

    public float getAvgGrade() {
        float avg = 0;
        for (Semester semester : semesters) {
            avg += semester.getAverageGrade();
        }
        avg /= completedSemestres;
        int scale = 10;
        return (float) (Math.ceil(avg * scale) / scale);
    }

    public boolean willBeScolarship() {
        Semester prevSem = semesters[completedSemestres - 1];
        for (Object grade : prevSem.getGradesArray()) {
            if ((int) grade <= 3) {
                return false;
            }
        }
        return true;
    }

    public void setQualifyWorkGrade(int grade) {
        if (completedSemestres < 8) throw
                new IllegalStateException("You cannot pass qualified work before 8 semester");
        if (grade < 2 || grade > 5) throw
                new IllegalArgumentException("Grade cannot be less than 1 and great than  5");
        this.qualifyWorkGrade = grade;
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
        return !(excellentPercent < 75);

    }


    public void addGrade(String subject, int grade, int semesterNumber) {
        semesters[semesterNumber - 1].addGrade(subject, grade);
    }

    public int getSubjectsCount(int semesterNumber) {
        return semesters[semesterNumber - 1].getSubjectCount();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("Name: ").append(name).append('\n');
        resultString.append("Surname: ").append(surname).append('\n');
        resultString.append("Faculty: ").append(faculty).append('\n');
        for (Semester semester : semesters) {
            resultString.append(semester.toString());
        }
        return resultString.toString();
    }


}
