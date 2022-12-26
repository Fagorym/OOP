package ru.nsu.fit.oop.veber;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represent serializer class for GSON.
 */
class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss.nnn");

    /**
     * Function that serializes LocalDateTime object
     *
     * @param localDateTime the object that needs to be converted to Json.
     * @param srcType       the actual type (fully genericized version) of the source object.
     * @param context       context of JSON
     * @return String representation of LocalDateTime object
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}