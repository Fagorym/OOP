package ru.nsu.fit.oop.veber;

import java.io.*;


public record Record(String header, String description, String createdAt) implements Serializable, Comparable<Record> {

    @Override
    public int compareTo(Record o) {
        return o.header.compareTo(this.header);
    }
}
