package ru.nsu.fit.oop.veber.parser;

import ru.nsu.fit.oop.veber.model.Parcelable;

import java.lang.reflect.Constructor;

public class Parser {
    public Object parse(Class<? extends Parcelable> parsedClass) {
        try {
            Constructor<?> constructor = parsedClass.getConstructors()[0];
            Parcelable parcelable = (Parcelable) constructor.newInstance();
            return parcelable.parse(parcelable.getConfigPath());
        } catch (Exception ex) {
            throw new RuntimeException("Cannot parse class " + parsedClass.getName());
        }

    }
}
