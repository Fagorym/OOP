package ru.nsu.fit.oop.veber.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Record;
import ru.nsu.fit.oop.veber.RecordBook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class BookTest {

    private RecordBook book;

    @BeforeEach
    public void initBook() throws FileNotFoundException {
        this.book = new RecordBook();
    }

    @Test
    @Order(1)
    public void testAddRecord() throws IOException {
        book.addRecord("I love Java!", "But sometimes I hate it! :)");
        Collection<Record> records = book.getRecords();
        Assertions.assertEquals(1, records.size());
        Assertions.assertEquals(records.stream().findFirst().get().name(), "I love Java!");
        book.saveBook();
        book.printBook();
    }

    @Test
    @Order(2)
    public void testDeleteRecord() throws IOException {
        Collection<Record> records = book.getRecords();
        Assertions.assertEquals(1, records.size());
        book.deleteRecord("I love Java!");
        Assertions.assertEquals(0, records.size());
        book.saveBook();
    }
}
