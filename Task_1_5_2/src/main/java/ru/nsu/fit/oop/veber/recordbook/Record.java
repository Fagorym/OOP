package ru.nsu.fit.oop.veber.recordbook;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Record-class that represent record in record book.
 *
 * @param header      - header of the record
 * @param description - description of the record
 * @param createdAt   - at what datetime record was created
 */
public record Record(String header, String description,
                     LocalDateTime createdAt) implements Serializable, Comparable<Record> {

    /**
     * Compares equality of two records.
     *
     * @param o the object to be compared.
     * @return true - if headers equals
     * false - if headers not equals
     */
    @Override
    public int compareTo(Record o) {
        return o.header.compareTo(this.header);
    }
}
