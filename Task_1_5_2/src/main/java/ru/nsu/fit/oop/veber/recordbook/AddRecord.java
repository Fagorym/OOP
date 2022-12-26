package ru.nsu.fit.oop.veber.recordbook;

import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDateTime;

@Command(name = "add", description = "Function that adds record to record book")
public class AddRecord implements Runnable {

    @Option(names = {"-h", "-header"}, description = "Header of the record")
    String header;
    @Option(names = {"-d", "-description"}, description = "Description of the record")
    String description;
    @ParentCommand
    RecordBook book;

    @Override
    public void run() {
        book.loadRecords();
        LocalDateTime datetime = LocalDateTime.now();
        Record value = new Record(header, description, datetime);
        book.getRecords().add(value);
        book.saveRecords();

    }
}
