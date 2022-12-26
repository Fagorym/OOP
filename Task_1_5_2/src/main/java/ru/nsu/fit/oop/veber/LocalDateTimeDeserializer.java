package ru.nsu.fit.oop.veber;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class that represent deserializer for GSON.
 */
class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    /**
     * Method that deserializes LocalDateTime at JSON.
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context Context of JSON
     * @return LocalDateTime representation of provided json.
     * @throws JsonParseException
     */
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss.nnn").withLocale(Locale.ENGLISH));
    }
}
