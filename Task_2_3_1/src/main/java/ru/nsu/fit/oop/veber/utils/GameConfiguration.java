package ru.nsu.fit.oop.veber.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class GameConfiguration {
    private static final GameConfiguration INSTANCE = new GameConfiguration();
    private final int GRAPHIC_SCREEN_LENGTH = 500;
    private final int GRAPHIC_SCREEN_HEIGHT = 300;
    private final int CONSOLE_SCREEN_LENGTH = 400;
    private final int CONSOLE_SCREEN_HEIGHT = 200;
    private final int FOOD_COUNT = 1;
    private final int FOOD_INITIAL_COORDINATE_X = 5;
    private final int FOOD_INITIAL_COORDINATE_Y = 5;
    private final int SNAKE_INITIAL_COORDINATE_X = 10;
    private final int SNAKE_INITIAL_COORDINATE_Y = 10;

    private final int DEFAULT_BLOCK_SIZE = 10;

    private GameConfiguration() {
        String propertyFileName = "config.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String propertyString = properties.getProperty(field.getName());
                if (propertyString != null) {
                    int propertyInteger = Integer.parseInt(propertyString);
                    field.setInt(this, propertyInteger);
                }
            }
        } catch (IOException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static GameConfiguration getINSTANCE() {
        return INSTANCE;
    }

    public int getCONSOLE_SCREEN_HEIGHT() {
        return CONSOLE_SCREEN_HEIGHT;
    }

    public int getCONSOLE_SCREEN_LENGTH() {
        return CONSOLE_SCREEN_LENGTH;
    }

    public int getFOOD_COUNT() {
        return FOOD_COUNT;
    }

    public int getFOOD_INITIAL_COORDINATE_X() {
        return FOOD_INITIAL_COORDINATE_X;
    }

    public int getFOOD_INITIAL_COORDINATE_Y() {
        return FOOD_INITIAL_COORDINATE_Y;
    }

    public int getGRAPHIC_SCREEN_HEIGHT() {
        return GRAPHIC_SCREEN_HEIGHT;
    }

    public int getGRAPHIC_SCREEN_LENGTH() {
        return GRAPHIC_SCREEN_LENGTH;
    }

    public int getSNAKE_INITIAL_COORDINATE_X() {
        return SNAKE_INITIAL_COORDINATE_X;
    }

    public int getSNAKE_INITIAL_COORDINATE_Y() {
        return SNAKE_INITIAL_COORDINATE_Y;
    }
}
