package ru.nsu.fit.oop.veber;

import picocli.CommandLine;
import ru.nsu.fit.oop.veber.recordbook.RecordBook;

import java.io.IOException;

public class Main {

    private static RecordBook book = new RecordBook();

    public static void main(String[] args) {
        new CommandLine(book).execute(args);

    }

    /**
     * Function that implemented for tests.
     * @return actual record book
     */
    public RecordBook getBook() {
        return Main.book;
    }

    /**
     * Function that implemented for tests.
     * @param book will be next record book.
     */
    public void setBook(RecordBook book) {
        Main.book = book;
    }
}