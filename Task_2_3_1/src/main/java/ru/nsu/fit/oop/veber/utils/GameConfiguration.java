package ru.nsu.fit.oop.veber.utils;

public class GameConfiguration {
    private static GameConfiguration gameConfiguration;
    private int graphicalScreenLength;
    private int graphicalScreenHeight;
    private int consoleScreenLength;
    private int consoleScreenHeight;
    private int foodCount;
    private int snakeInitialCoordinateX;
    private int snakeInitialCoordinateY;
    private int defaultBlockSize;

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
