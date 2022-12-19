package ru.nsu.fit.oop.veber;

import java.io.*;


public record Record(String time, String name, String description) implements Serializable, Comparable<Record> {

    @Override
    public int compareTo(Record o) {
        return o.name.compareTo(this.name);
    }
}
