package ru.nsu.fit.oop.veber.recordbook;

import com.google.gson.Gson;
import picocli.CommandLine.*;
import ru.nsu.fit.oop.veber.utils.CustomGsonParser;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.exit;
import static java.lang.System.out;


/**
 * Class that represents record book.
 */
@Command(name = "notebook", subcommands = {AddRecord.class, RemoveRecord.class, ShowRecords.class})
public class RecordBook {

    private final Gson gson = new CustomGsonParser().getGson();
    @Option(names = {"-f", "-file"}, description = "Notebook file", defaultValue = "records.txt")
    private File file;
    private TreeSet<Record> records;

    public Collection<Record> getRecords() {
        return records;
    }

    /**
     * Function that writes records to file.
     */
    protected void saveRecords() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(this.toString());
        } catch (IOException e) {
            out.println("Unable to save records");
            exit(1);
        }


    }

    /**
     * Function that read file with existed records, parse it and save into records.
     */
    protected void loadRecords() {
        try (FileReader reader = new FileReader(file)) {
            Record[] array = gson.fromJson(reader, Record[].class);
            this.records = new TreeSet<>(Arrays.asList(array));
        } catch (Exception e) {
            records = records == null ? new TreeSet<>() : records;
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


}
