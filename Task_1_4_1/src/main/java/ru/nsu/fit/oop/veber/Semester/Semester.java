package ru.nsu.fit.oop.veber.Semester;

import java.util.HashMap;

/**
 * Class that implements current semester and its grades.
 */
public class Semester {
    private final HashMap<String, Integer> grades;

    private final int semesterNumber;
    private float averageGrade;
    private int subjectCount;


    /**
     * Default constructor of semester.
     *
     * @param semesterNumber - number of current semester
     */
    public Semester(int semesterNumber) {
        this.grades = new HashMap<>();
        averageGrade = 0;
        subjectCount = 0;
        this.semesterNumber = semesterNumber;
    }


    /**
     * Method that add new pair in hashmap, where subject is key, grade is value.
     *
     * @param subject - will be key
     * @param grade   - will be value
     */
    public void addGrade(String subject, Integer grade) {
        if (grade < 1 || grade > 5) {
            throw new IllegalArgumentException("Grade cannot be less than 1 and greater than 5");
        }
        if (!grades.containsKey(subject)) {
            subjectCount++;
        }
        grades.put(subject, grade);
        recountAverage();
    }

    /**
     * Method that refresh average grade. It used every time, after we add new mark to the semester.
     */
    private void recountAverage() {
        averageGrade = 0;
        for (Integer grade : grades.values()) {
            averageGrade += grade;
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
        return subjectCount;
    }

    /**
     * Method that return grades as array.
     *
     * @return array of grades in this semester
     */
    public Object[] getGradesArray() {
        return this.grades.values().toArray();
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
        for (String subject : grades.keySet()) {
            stringBuilder.append(subject).append(" - ").append(grades.get(subject)).append('\n');
        }
        stringBuilder.append("Average grade: ").append(getAverageGrade()).append("\n");
        stringBuilder.append("================================").append('\n');
        return stringBuilder.toString();
    }
}