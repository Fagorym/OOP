package ru.nsu.fit.oop.veber.utils;


import javafx.scene.paint.Color;

public class GameConfiguration {
    private static GameConfiguration gameConfiguration;
    private int consoleScreenLength;
    private int consoleScreenHeight;
    private int foodCount;
    private int boxHeight;
    private int boxLength;

    private double gameSpeed;

    private transient Color snakeColor = Color.GREEN;

    public static GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }


    public static void setGameConfiguration(GameConfiguration gameConfiguration) {
        GameConfiguration.gameConfiguration = gameConfiguration;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
    }

    public int getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(int boxLength) {
        this.boxLength = boxLength;
    }

    public int getConsoleScreenHeight() {
        return consoleScreenHeight;
    }

    public void setConsoleScreenHeight(int consoleScreenHeight) {
        this.consoleScreenHeight = consoleScreenHeight;
    }

    public int getConsoleScreenLength() {
        return consoleScreenLength;
    }

    public void setConsoleScreenLength(int consoleScreenLength) {
        this.consoleScreenLength = consoleScreenLength;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public double getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public void setSnakeColor(Color snakeColor) {
        this.snakeColor = snakeColor;
    }
}
