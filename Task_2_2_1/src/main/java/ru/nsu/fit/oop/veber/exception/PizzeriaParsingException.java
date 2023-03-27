package ru.nsu.fit.oop.veber.exception;

public class PizzeriaParsingException extends RuntimeException {
    public PizzeriaParsingException(String filename) {
        super("Parser cannot find file with name " + filename + " for parsing your pizzeria.");
    }

}
