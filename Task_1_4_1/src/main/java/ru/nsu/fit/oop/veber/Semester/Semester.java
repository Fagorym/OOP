package ru.nsu.fit.oop.veber.Semester;

import ru.nsu.fit.oop.veber.Grade.GradeEnum;
import ru.nsu.fit.oop.veber.Subject.Subject;
import ru.nsu.fit.oop.veber.Subject.SubjectType;


import java.util.HashSet;
import java.util.Set;

/**
 * Class that implements current semester and its grades.
 */
public class Semester {
    private final Set<Subject> subjects;

    private final int semesterNumber;
    private float averageGrade;


    /**
     * Default constructor of semester.
     *
     * @param semesterNumber - number of current semester
     */
    public Semester(int semesterNumber) {
        this.subjects = new HashSet<>();
        averageGrade = 0;
        this.semesterNumber = semesterNumber;
    }


    /**
     * Method that add new pair in hashmap, where subject is key, grade is value.
     *
     * @param subject - will be key
     * @param grade   - will be value
     */
    public void addGrade(String subject, GradeEnum grade, SubjectType type) {
        if (type == SubjectType.CREDIT && (grade != GradeEnum.PASSED && grade != GradeEnum.FAILED)) {
            throw new IllegalArgumentException("Credit cannot be passed for anything other than passed or failed");
        }
        Subject newSubject = new Subject(subject, grade, type);
        subjects.add(newSubject);
        recountAverage();
    }

    /**
     * Method that refresh average grade. It used every time, after we add new mark to the semester.
     */
    private void recountAverage() {
        averageGrade = 0;
        int subjectCount = getSubjectCount();
        for (Subject subject : subjects) {
            if (subject.getType() == SubjectType.CREDIT) {
                subjectCount--;
            } else {
                averageGrade += subject.getGrade();
            }
        }
        averageGrade /= subjectCount;
    }


    /**
     * Getter for average grade.
     *
     * @return average grade in this semester
     */
    public float getAverageGrade() {
        return averageGrade;
    }

    /**
     * Getter for count of subject.
     *
     * @return count of subjects in this semester
     */
    public int getSubjectCount() {
        return subjects.size();
    }

    public Object[] getSubjectArray() {
        return subjects.toArray();
    }

    public long getCountBySubjectType(SubjectType type) {
        return subjects.stream().filter((subject -> subject.getType() == type)).count();
    }

    /**
     * Method that convert this semester to string.
     *
     * @return string representation of this semester
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Semester number ").append(semesterNumber).append('\n');
        stringBuilder.append("================================").append('\n');
        for (Subject subject : subjects) {
            stringBuilder.append(subject.toString());
        }
        stringBuilder.append("Average grade: ").append(getAverageGrade()).append("\n");
        for (SubjectType type : SubjectType.values()) {
            stringBuilder.append("Count of ").append(type).append(" - ").append(getCountBySubjectType(type)).append('\n');
        }
        stringBuilder.append("================================").append('\n');
        return stringBuilder.toString();
    }
}