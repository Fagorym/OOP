package ru.nsu.fit.oop.veber.exception;

/**
 * Runtime exception for marking that parsing pizzeria configuration was broken.
 */
public class PizzeriaParsingException extends RuntimeException {
    public PizzeriaParsingException(String filename) {
        super("Parser cannot find file with name " + filename + " for parsing your pizzeria.");
    }

}
