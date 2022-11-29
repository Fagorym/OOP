package ru.nsu.fit.oop.veber.TestGradeBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.GradeBook.GradeBook;

public class TestMyGradeBook {
    GradeBook testGradeBook;

    @BeforeEach
    public void initGradeBook() {
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                2);
    }

    // TODO: Tests/Javadocs
    @Test
    public void testMyGradeBook() {
        testGradeBook.addGrade("Math Analysis", 5, 1);
        testGradeBook.addGrade("History", 4, 1);
        testGradeBook.addGrade("Discrete math", 5, 1);
        testGradeBook.addGrade("Imperative programming", 5, 1);
        testGradeBook.addGrade("Declarative programming", 5, 1);
        testGradeBook.addGrade("Basics of language culture", 5, 1);
        testGradeBook.addGrade("Math Analysis", 5, 2);
        testGradeBook.addGrade("Discrete math", 5, 2);
        testGradeBook.addGrade("Imperative programming", 5, 2);
        testGradeBook.addGrade("Declarative programming", 5, 2);
        testGradeBook.addGrade("English language", 5, 2);
        testGradeBook.addGrade("Digital platforms", 5, 2);
        Assertions.assertEquals(5.0, testGradeBook.getAvgGrade());
        Assertions.assertEquals(6, testGradeBook.getSubjectsCount(1));
        Assertions.assertTrue(testGradeBook.willBeScolarship());
        testGradeBook.addGrade("Digital Platforms", 3, 2);
        Assertions.assertFalse(testGradeBook.willBeScolarship());
        System.out.println(testGradeBook.toString());
    }

    @Test
    public void testException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.addGrade("Useless subject",
                10, 2));
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.addGrade("Useless subject",
                0, 2));
        Assertions.assertThrows(IllegalStateException.class, ()
                -> testGradeBook.setQualifyWorkGrade(5));

    }

    @Test
    public void testRedDiploma() {
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                8);
        for (int i = 1; i <= 8; i++) {
            testGradeBook.addGrade("SomeSubject", 5, i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.setQualifyWorkGrade(1));
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> testGradeBook.setQualifyWorkGrade(10));
        testGradeBook.setQualifyWorkGrade(5);
        Assertions.assertTrue(testGradeBook.willBeRedDiploma());
        testGradeBook.addGrade("SomeSubject", 3, 1);
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());
        for (int i = 1; i <= 8; i++) {
            testGradeBook.addGrade("SomeSubject", 4, i);
        }
        Assertions.assertFalse(testGradeBook.willBeRedDiploma());
        testGradeBook.setQualifyWorkGrade(2);
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
                testGradeBook.setQualifyWorkGrade(5);
            });
            testGradeBook.addGrade("Math", 5, i + 1);
            testGradeBook.increaseSemester();
        }
        testGradeBook.setQualifyWorkGrade(5);
        Assertions.assertTrue(testGradeBook.willBeRedDiploma());


    }


}