package ru.nsu.fit.oop.veber;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Wrong arguments count, try again");
        }
        RecordBook book = new RecordBook();
        switch (args[0]) {
            case "-add" -> {
                if (args.length < 3) {
                    throw new IllegalArgumentException("Wrong arguments count, try again");
                }
                book.addRecord(args[1], args[2]);
            }

            case "-rm" -> {
                if (args.length < 2) {
                    throw new IllegalArgumentException("Wrong arguments count, try again");
                }
                book.deleteRecord(args[1]);
            }

            case "-show" -> {
                if (args.length == 1) {
                    book.printBook();

                }
            }
            default -> throw new IllegalArgumentException("Wrong argument, check documentation");

        }
        book.saveBook();
    }
}