package ru.nsu.fit.oop.veber.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Main;
import ru.nsu.fit.oop.veber.recordbook.Record;
import ru.nsu.fit.oop.veber.recordbook.RecordBook;

import java.io.IOException;

public class BookTest {


    @Test
    public void testAddRecord() {

        Main main = new Main();
        RecordBook book = main.getBook();
        String[] args = {"add",
                "-h=I love Java!",
                "-d=But sometimes I hate it! :)"};
        Main.main(args);
        Assertions.assertEquals(1, book.getRecords().size());
        Record firstRecord = book.getRecords().stream().toList().get(0);
        Assertions.assertEquals("I love Java!", firstRecord.header());
        Assertions.assertEquals("But sometimes I hate it! :)", firstRecord.description());
        args = new String[]{"add",
                "-h=I dont love Java!",
                "-d=But sometimes I love it! :)"};
        Main.main(args);
        Assertions.assertEquals(2, book.getRecords().size());
        Record secondRecord = book.getRecords().stream().toList().get(1);
        Assertions.assertEquals("I dont love Java!", secondRecord.header());
        Assertions.assertEquals("But sometimes I love it! :)", secondRecord.description());
        args = new String[]{"rm",
                "-h=I dont love Java!"};
        Main.main(args);
        args = new String[]{"show"};
        Main.main(args);

    }

    @Test
    public void testAddAnotherFileRecords() {
        Main main = new Main();
        RecordBook book = main.getBook();
        String[] args = {"-f=notebook.txt",
                "add",
                "-h=I love Java!",
                "-d=But sometimes I hate it! :)"};
        Main.main(args);
        Record firstRecord = book.getRecords().stream().toList().get(0);
        Assertions.assertEquals("I love Java!", firstRecord.header());
        Assertions.assertEquals("But sometimes I hate it! :)", firstRecord.description());

    }
}
