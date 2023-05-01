package ru.nsu.fit.oop.veber.utils;

public class GameConfiguration {
    private static GameConfiguration gameConfiguration;
    private final int graphicalScreenLength = 500;
    private final int graphicalScreenHeight = 300;
    private final int consoleScreenLength = 400;
    private final int consoleScreenHeight = 200;
    private final int foodCount = 1;
    private final int snakeInitialCoordinateX = 10;
    private final int snakeInitialCoordinateY = 10;
    private final int defaultBlockSize = 10;

    public static GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    public static void setGameConfiguration(GameConfiguration gameConfiguration) {
        GameConfiguration.gameConfiguration = gameConfiguration;
    }

    public static GameConfiguration getInstance() {
        return gameConfiguration;
    }

    public int getConsoleScreenHeight() {
        return consoleScreenHeight;
    }

    public int getConsoleScreenLength() {
        return consoleScreenLength;
    }

    public int getDefaultBlockSize() {
        return defaultBlockSize;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getGraphicalScreenHeight() {
        return graphicalScreenHeight;
    }

    public int getGraphicalScreenLength() {
        return graphicalScreenLength;
    }

    public int getSnakeInitialCoordinateX() {
        return snakeInitialCoordinateX;
    }

    public int getSnakeInitialCoordinateY() {
        return snakeInitialCoordinateY;
    }
}
