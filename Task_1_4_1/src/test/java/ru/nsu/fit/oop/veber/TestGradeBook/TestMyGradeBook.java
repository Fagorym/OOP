package ru.nsu.fit.oop.veber.TestGradeBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Grade.GradeEnum;
import ru.nsu.fit.oop.veber.GradeBook.GradeBook;
import ru.nsu.fit.oop.veber.Subject.Subject;
import ru.nsu.fit.oop.veber.Subject.SubjectType;

public class TestMyGradeBook {
    GradeBook testGradeBook;

    @BeforeEach
    public void initGradeBook() {
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                2);
    }

    @Test
    public void testMyGradeBook() {
        testGradeBook.addGrade("Math Analysis", GradeEnum.EXCELLENT, 1, SubjectType.EXAM);
        testGradeBook.addGrade("History", GradeEnum.GOOD, 1, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("English language", GradeEnum.PASSED, 1, SubjectType.CREDIT);
        testGradeBook.addGrade("Discrete math", GradeEnum.EXCELLENT, 1, SubjectType.EXAM);
        testGradeBook.addGrade("Imperative programming", GradeEnum.EXCELLENT, 1, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("Declarative programming", GradeEnum.EXCELLENT, 1, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("Basics of language culture", GradeEnum.EXCELLENT, 1, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("Math Analysis", GradeEnum.EXCELLENT, 2, SubjectType.EXAM);
        testGradeBook.addGrade("Discrete math", GradeEnum.EXCELLENT, 2, SubjectType.EXAM);
        testGradeBook.addGrade("Imperative programming", GradeEnum.EXCELLENT, 2, SubjectType.EXAM);
        testGradeBook.addGrade("Declarative programming", GradeEnum.EXCELLENT, 2, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("English language", GradeEnum.EXCELLENT, 2, SubjectType.DIF_CREDIT);
        testGradeBook.addGrade("Digital platforms", GradeEnum.EXCELLENT, 2, SubjectType.DIF_CREDIT);
        Assertions.assertEquals(5.0, testGradeBook.getAvgGrade());
        Assertions.assertEquals(7, testGradeBook.getSubjectsCount(1));
        Assertions.assertTrue(testGradeBook.willBeScholarship());
        testGradeBook.addGrade("Digital Platforms", GradeEnum.SATISFYING, 2, SubjectType.DIF_CREDIT);
        Assertions.assertFalse(testGradeBook.willBeScholarship());
        System.out.println(testGradeBook.toString());
    }

    @Test
    public void testException() {
        Assertions.assertThrows(IllegalStateException.class, ()
                -> testGradeBook.setGraduateWorkGrade(5));

    }

    @Test
    public void testRedDiploma() {
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                8);
        for (int i = 1; i <= 8; i++) {
            testGradeBook.addGrade("SomeSubject", GradeEnum.EXCELLENT, i, SubjectType.EXAM);
        }
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.setGraduateWorkGrade(1));
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.setGraduateWorkGrade(10));
        testGradeBook.setGraduateWorkGrade(5);
        Assertions.assertTrue(testGradeBook.willBeRedDiploma());
        testGradeBook.addGrade("SomeSubject", GradeEnum.SATISFYING, 1, SubjectType.EXAM);
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());
        for (int i = 1; i <= 8; i++) {
            testGradeBook.addGrade("SomeSubject", GradeEnum.GOOD, i, SubjectType.EXAM);
        }
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());
        testGradeBook.setGraduateWorkGrade(2);
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());

    }

    @Test
    public void testIncrementSemester() {
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                1);
        for (int i = 0; i < 7; i++) {
            Assertions.assertThrows(IllegalStateException.class, () -> {
                testGradeBook.setGraduateWorkGrade(5);
            });
            testGradeBook.addGrade("Math", GradeEnum.EXCELLENT, i + 1, SubjectType.EXAM);
            testGradeBook.increaseSemester();
        }
        testGradeBook.setGraduateWorkGrade(5);
        Assertions.assertTrue(testGradeBook.willBeRedDiploma());
        testGradeBook.addGrade("Some stupid subject", GradeEnum.PASSED, 1, SubjectType.CREDIT);
        Assertions.assertTrue(testGradeBook.willBeRedDiploma());
        testGradeBook.addGrade("Some stupid subject", GradeEnum.FAILED, 1, SubjectType.CREDIT);
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());


    }


}