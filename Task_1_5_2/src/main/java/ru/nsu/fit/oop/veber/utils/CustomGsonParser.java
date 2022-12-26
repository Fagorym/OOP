package ru.nsu.fit.oop.veber.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class CustomGsonParser {

    Gson gson;

    public CustomGsonParser() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    public Gson getGson() {
        return gson;
    }
}
