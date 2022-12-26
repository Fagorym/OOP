package ru.nsu.fit.oop.veber;

import picocli.CommandLine;

import java.io.IOException;

public class Main {

    private static RecordBook book = new RecordBook();

    public static void main(String[] args) throws IOException {
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