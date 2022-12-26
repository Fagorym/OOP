package ru.nsu.fit.oop.veber;

import com.google.gson.*;
import picocli.CommandLine.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.out;


@Command(name = "notebook")
public class RecordBook {

    @Option(names = {"-f", "-file"}, description = "Notebook file", defaultValue = "records.txt")
    private File file;
    private TreeSet<Record> records;

    @Command(name = "-add")
    public void addRecord(@Option(names = "-h", description = "Header of the note") String header,
                          @Option(names = "-d", description = "Description of the note") String description)
            throws IOException {
        loadRecords();
        String datetime = String.valueOf(LocalDateTime.now());
        Record value = new Record(header, description, datetime);
        records.add(value);
        saveRecords();

    }

    @Command(name = "-rm")
    public void deleteRecord(@Option(names = "-h", description = "Header of the note") String header)
            throws IOException {
        loadRecords();
        records.removeIf((record -> Objects.equals(record.header(), header)));
        saveRecords();
    }

    @Command(name = "-show")
    public void printBook(@Option(names = "-from", description = "From what date need to find records",
            defaultValue = Option.NULL_VALUE) String from,
                          @Option(names = "-to", description = "To what date need to find records",
                                  defaultValue = Option.NULL_VALUE) String to,
                          @Option(names = "-tags", description = "Tags that must be in the header",
                                  defaultValue = "") String[] tags) {
        loadRecords();

        LocalDateTime fromDateTime = from == null ? LocalDateTime.MIN : LocalDateTime.parse(from);
        LocalDateTime afterDateTime = to == null ? LocalDateTime.MAX : LocalDateTime.parse(to);

        records.stream()
                .filter(record ->
                        fromDateTime.isBefore(LocalDateTime.parse(record.createdAt())) &&
                                afterDateTime.isAfter(LocalDateTime.parse(record.createdAt())) &&
                                Stream.of(tags).allMatch(record.header()::contains))
                .forEach(System.out::println);
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void saveRecords() throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(this.toString());
        } catch (Exception e) {
            out.println("Something happened");
            System.exit(1);
        }


    }

    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(records);
    }

    private void loadRecords() {
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().create();
            List<Record> tempList = Arrays.asList(gson.fromJson(reader, Record[].class));
            this.records = new TreeSet<>(tempList);
        } catch (IOException | IllegalStateException e) {
            records = new TreeSet<>();
        }
        if (records == null) {
            records = new TreeSet<>();
        }

    }


}
