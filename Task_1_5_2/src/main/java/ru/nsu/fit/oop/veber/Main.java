package ru.nsu.fit.oop.veber;

import picocli.CommandLine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new CommandLine(new RecordBook()).execute(args);

    }
}