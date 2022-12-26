package ru.nsu.fit.oop.veber.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Main;
import ru.nsu.fit.oop.veber.Record;
import ru.nsu.fit.oop.veber.RecordBook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class BookTest {

    private Main cli;


    @Test
    public void testAddRecord() throws IOException {

        Main main = new Main();
        RecordBook book = main.getBook();
        String[] args = {"-add",
                "-h=I love Java!",
                "-d=But sometimes I hate it! :)"};
        Main.main(args);
        Assertions.assertEquals(1, book.getRecords().size());
        Record firstRecord = book.getRecords().stream().toList().get(0);
        Assertions.assertEquals("I love Java!", firstRecord.header());
        Assertions.assertEquals("But sometimes I hate it! :)", firstRecord.description());
        args = new String[]{"-add",
                "-h=I dont love Java!",
                "-d=But sometimes I love it! :)"};
        Main.main(args);
        Assertions.assertEquals(2, book.getRecords().size());
        Record secondRecord = book.getRecords().stream().toList().get(1);
        Assertions.assertEquals("I dont love Java!", secondRecord.header());
        Assertions.assertEquals("But sometimes I love it! :)", secondRecord.description());
        args = new String[]{"-rm",
                "-h=I dont love Java!"};
        Main.main(args);
        args = new String[]{"-show"};
        Main.main(args);

    }
}
