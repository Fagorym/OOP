package ru.nsu.fit.oop.veber;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

import static java.lang.System.out;

public class RecordBook {
    private final File file;
    private TreeSet<Record> records;

    public RecordBook() throws FileNotFoundException {
        String filename = "./records.txt";
        file = new File(filename);
        loadRecords();
        if (records == null) {
            records = new TreeSet<>();
        }
    }

    public void addRecord(String name, String description) {
        String time = String.valueOf(LocalDateTime.now());
        Record value = new Record(time, name, description);
        records.add(value);

    }

    public void printBook() {
        out.println(this);
    }

    public void deleteRecord(String name) {
        records.removeIf((record -> Objects.equals(record.name(), name)));
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void saveBook() throws IOException {
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
            Type setType = new TypeToken<TreeSet<Record>>() {
            }.getType();
            records = gson.fromJson(reader, setType);
        } catch (IOException e) {
            records = new TreeSet<>();
        }

    }


}
