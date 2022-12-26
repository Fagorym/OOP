package ru.nsu.fit.oop.veber.utils;

import picocli.CommandLine;

import java.time.LocalDateTime;

public class LocalDateTimeConverter implements CommandLine.ITypeConverter<LocalDateTime> {
    @Override
    public LocalDateTime convert(String value) throws Exception {
        return LocalDateTime.parse(value);
    }
}
