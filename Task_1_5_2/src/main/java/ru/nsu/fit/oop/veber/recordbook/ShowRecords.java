package ru.nsu.fit.oop.veber.recordbook;

import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import ru.nsu.fit.oop.veber.utils.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Class that represent subcommand "show" for RecordBook.
 */
@Command(name = "show", description = "Function that shows record from record book")
public class ShowRecords implements Runnable {
    @Option(names = "-from",
            description = "From what date need to find records",
            converter = LocalDateTimeConverter.class)
    LocalDateTime from = LocalDateTime.MIN;
    @Option(names = "-to",
            description = "To what date need to find records",
            converter = LocalDateTimeConverter.class)
    LocalDateTime to = LocalDateTime.MAX;
    @Option(names = "-tags", description = "Tags that must be in the header",
            defaultValue = "")
    String[] tags;
    @ParentCommand
    RecordBook book;

    /**
     * Function that print records, that matches provided parameters.
     */
    @Override
    public void run() {
        book.loadRecords();
        book.getRecords().stream().filter(record ->
                        from.isBefore(record.createdAt()) &&
                                to.isAfter(record.createdAt()) &&
                                Stream.of(tags).allMatch(record.header()::contains))
                .forEach(System.out::println);
    }
}
