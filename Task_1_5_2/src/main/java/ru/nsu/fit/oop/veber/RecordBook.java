package ru.nsu.fit.oop.veber;

import com.google.gson.*;
import picocli.CommandLine.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.exit;
import static java.lang.System.out;


/**
 * Class that represents record book.
 */
@Command(name = "notebook")
public class RecordBook {

    private Gson gson;
    @Option(names = {"-f", "-file"}, description = "Notebook file", defaultValue = "records.txt")
    private File file;
    private TreeSet<Record> records;

    /**
     * Default constructor of record book.
     */
    public RecordBook() {
        initGson();
    }

    /**
     * Function that inits GSON parser for LocalDateTime.
     */
    private void initGson() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    /**
     * Adds record to record book.
     *
     * @param header      - will be header of new record
     * @param description - will be description of new record
     */
    @Command(name = "-add")
    public void addRecord(@Option(names = "-h", description = "Header of the note") String header,
                          @Option(names = "-d", description = "Description of the note") String description) {
        loadRecords();
        LocalDateTime datetime = LocalDateTime.now();
        Record value = new Record(header, description, datetime);
        records.add(value);
        saveRecords();

    }

    /**
     * Function that removes records from book by provided header.
     *
     * @param header - with what header record will be removed
     */
    @Command(name = "-rm")
    public void deleteRecord(@Option(names = "-h", description = "Header of the note") String header) {
        loadRecords();
        records.removeIf((record -> Objects.equals(record.header(), header)));
        saveRecords();
    }

    /**
     * Function that printed records with provided parameters.
     *
     * @param from - not required.
     *             From what datetime we need to find records.
     *             by default - from LocalDateTime MIN value
     * @param to   - not required.
     *             To what datetime we need to find records.
     *             by default - to LocalDateTime MAX value
     * @param tags - not required.
     *             What tags header must contain.
     *             By default - no tags.
     */
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
                        fromDateTime.isBefore(record.createdAt()) &&
                                afterDateTime.isAfter(record.createdAt()) &&
                                Stream.of(tags).allMatch(record.header()::contains))
                .forEach(System.out::println);
    }

    public Collection<Record> getRecords() {
        return records;
    }

    /**
     * Function that writes records to file.
     */
    public void saveRecords() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(this.toString());
        } catch (IOException e) {
            out.println("Unable to save records");
            exit(1);
        }


    }

    /**
     * Function that returns json representation of records.
     *
     * @return JSON representation of records.
     */
    public String toString() {
        return gson.toJson(records);
    }

    /**
     * Function that read file with existed records, parse it and save into records.
     */
    private void loadRecords() {
        try (FileReader reader = new FileReader(file)) {
            Record[] array = gson.fromJson(reader, Record[].class);
            this.records = new TreeSet<>(Arrays.asList(array));
        } catch (Exception e) {
            records = records == null ? new TreeSet<>() : records;
        }

    }


}
