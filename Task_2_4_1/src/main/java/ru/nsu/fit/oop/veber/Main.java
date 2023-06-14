package ru.nsu.fit.oop.veber;


import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        String[] newArgs = {"fagorym=1", "rafi2002=0"};
        new CommandLine(new ReportApi()).execute(newArgs);
    }
}