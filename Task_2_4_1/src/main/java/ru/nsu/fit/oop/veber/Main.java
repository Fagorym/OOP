package ru.nsu.fit.oop.veber;


import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        String[] newArgs = {"mihailCCfit=15", "fagorym=3", "rafi2002=23"};
        new CommandLine(new ReportApi()).execute(newArgs);
    }
}