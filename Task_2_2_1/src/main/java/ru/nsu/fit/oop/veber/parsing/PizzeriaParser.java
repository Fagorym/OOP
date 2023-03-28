package ru.nsu.fit.oop.veber.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.nsu.fit.oop.veber.exception.PizzeriaParsingException;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;

import java.io.InputStream;
import java.io.InputStreamReader;

public class PizzeriaParser {

    private final Gson gson;


    public PizzeriaParser() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public Pizzeria parsePizzeriaFromFile(String filepath) {
        JsonReader reader;
        InputStream inputStream = this.getClass().getResourceAsStream(filepath);
        if (inputStream == null) {
            throw new PizzeriaParsingException(filepath);
        }
        reader = new JsonReader(new InputStreamReader(inputStream));
        ConfigurationDto configurationDto = gson.fromJson(reader, ConfigurationDto.class);
        return new PizzeriaImpl(configurationDto);

    }

}
