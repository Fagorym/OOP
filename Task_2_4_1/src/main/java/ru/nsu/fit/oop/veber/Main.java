package ru.nsu.fit.oop.veber;


import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        new CommandLine(new ReportApi()).execute();
    }
}