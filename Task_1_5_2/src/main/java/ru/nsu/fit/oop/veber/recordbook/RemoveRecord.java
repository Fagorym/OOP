package ru.nsu.fit.oop.veber.recordbook;

import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.Objects;

/**
 * Class that represent subcommand "rm" for RecordBook.
 */
@Command(name = "rm", description = "Function that removes record from record book")
public class RemoveRecord implements Runnable {
    @Option(names = "-h", description = "Header of the note")
    String header;

    @ParentCommand
    RecordBook book;

    /**
     * Function that remove record with provided header.
     */
    @Override
    public void run() {
        book.loadRecords();
        book.getRecords().removeIf((record -> Objects.equals(record.header(), header)));
        book.saveRecords();
    }
}
