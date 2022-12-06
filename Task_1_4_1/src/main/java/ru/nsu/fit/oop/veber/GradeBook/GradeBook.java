package ru.nsu.fit.oop.veber.GradeBook;

import ru.nsu.fit.oop.veber.Grade.GradeEnum;
import ru.nsu.fit.oop.veber.Semester.Semester;
import ru.nsu.fit.oop.veber.Subject.Subject;
import ru.nsu.fit.oop.veber.Subject.SubjectType;

import java.util.ArrayList;

/**
 * Class that implements student`s grade book.
 */
public class GradeBook {
    int completedSemesters;
    String name;
    String surname;
    String faculty;

    int graduateWorkGrade;
    ArrayList<Semester> semesters;

    /**
     * Default constructor of grade book.
     *
     * @param name               - name of the student
     * @param surname            - surname of the student
     * @param faculty            - faculty of the student
     * @param completedSemesters - count of semesters, that student had already complete
     */
    public GradeBook(String name, String surname, String faculty, int completedSemesters) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.completedSemesters = completedSemesters;
        this.semesters = new ArrayList<>();
        for (int i = 0; i < completedSemesters; i++) {
            semesters.add(new Semester(i + 1));
        }
    }

    /**
     * Method that counts average grade for all semesters.
     *
     * @return - average grade for all semesters
     */
    public float getAvgGrade() {
        float avg = 0;
        for (Semester semester : semesters) {
            avg += semester.getAverageGrade();
        }
        avg /= completedSemesters;
        int scale = 10;
        return (float) (Math.ceil(avg * scale) / scale);
    }

    /**
     * Method that analyze grades and decide, will be scholarship in next semester or not.
     *
     * @return true - will be scholarship, false - won`t be.
     */
    public boolean willBeScholarship() {
        Semester prevSem = semesters.get(completedSemesters - 1);
        for (Object object : prevSem.getSubjectArray()) {
            Subject subject = (Subject) object;
            if (subject.getGrade() <= 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that sets mark for graduate work.
     *
     * @param grade - will be grade for graduate work
     */
    public void setGraduateWorkGrade(int grade) {
        if (completedSemesters < 8) throw
                new IllegalStateException("You cannot pass qualified work before 8 semester");
        if (grade < 2 || grade > 5) throw
                new IllegalArgumentException("Grade cannot be less than 1 and great than  5");
        this.graduateWorkGrade = grade;
    }

    /**
     * Method that analyze grades and decide, will be red diploma or not.
     * You cannot have red diploma, before you pass graduated work.
     * You cannot pass graduated work, before you end 8 semesters.
     * So you cannot have red diploma, before you end 8 semesters.
     *
     * @return true - will be red diploma, false - won`t be
     */
    public boolean willBeRedDiploma() {
        if (completedSemesters < 8 || graduateWorkGrade < 5) {
            return false;
        }
        int totalGradesCount = 0;
        int excellentGradesCount = 0;
        for (Semester semester : semesters) {
            for (Object obj : semester.getSubjectArray()) {
                Subject subject = (Subject) obj;
                if (subject.getGrade() <= 3) {
                    return false;
                }
                if (subject.getGrade() == 5) {
                    excellentGradesCount++;
                }
                totalGradesCount++;

            }
        }

        float excellentPercent = (float) excellentGradesCount / totalGradesCount * 100;
        return !(excellentPercent < 75);

    }


    /**
     * Method that adds grade in current semester.
     * You cannot add grades in semesters, that you had not complete.
     * If you want to add grade in (N+1) semester, but you completed only (N) semesters,
     * you first need to increase your semester by method increaseSemester().
     *
     * @param subject        - will be subject for this grade
     * @param grade          - will be grade for this subject
     * @param semesterNumber - in which semester we need to add pair (subject,grade)
     */
    public void addGrade(String subject, GradeEnum grade, int semesterNumber, SubjectType type) {
        if (semesterNumber - 1 > completedSemesters) {
            throw new IllegalArgumentException("You cannot add grades in semester, that you had not complete");
        }
        semesters.get(semesterNumber - 1).addGrade(subject, grade, type);
    }

    /**
     * Getter for subject count in current semester.
     *
     * @param semesterNumber - in what semester we need to know count of subjects
     * @return count of subjects in current semester
     */
    public int getSubjectsCount(int semesterNumber) {
        return semesters.get(semesterNumber - 1).getSubjectCount();
    }

    /**
     * Method, that increase completed semesters for this student.
     * That allows him to add grades for (i+1) semester, where i - current semester.
     */
    public void increaseSemester() {
        completedSemesters++;
        semesters.add(new Semester(completedSemesters));
    }

    /**
     * Represent grade book as a string.
     *
     * @return string representation of grade book
     */
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
