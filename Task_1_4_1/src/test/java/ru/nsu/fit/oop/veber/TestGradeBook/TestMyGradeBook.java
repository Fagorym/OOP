package ru.nsu.fit.oop.veber.TestGradeBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.GradeBook.GradeBook;

public class TestMyGradeBook {
    GradeBook testGradeBook;


    // TODO: Tests/Javadocs
    @Test
    public void testMyGradeBook(){
        testGradeBook = new GradeBook("Oleg",
                "Veber",
                "Faculty of Information Technologies",
                2);
        testGradeBook.addGrade("Math Analysis", 5, 1);
        testGradeBook.addGrade("History", 4, 1);
        testGradeBook.addGrade("Discrete math", 5, 1);
        testGradeBook.addGrade("Imperative programming", 5, 1);
        testGradeBook.addGrade("Declarative programming", 5,1);
        testGradeBook.addGrade("Basics of language culture", 5, 1);
        testGradeBook.addGrade("Math Analysis", 5, 2);
        testGradeBook.addGrade("Discrete math", 5, 2);
        testGradeBook.addGrade("Imperative programming", 5, 2);
        testGradeBook.addGrade("Declarative programming", 5,2);
        testGradeBook.addGrade("English language", 5,2);
        testGradeBook.addGrade("Digital platforms",5,2);
       Assertions.assertEquals(5.0, testGradeBook.getAvgGrade());
       Assertions.assertTrue(testGradeBook.willBeScolarship());
        System.out.println(testGradeBook.toString());
    }

    @Test
    public void testMyImagineGradeBook(){

    }


}