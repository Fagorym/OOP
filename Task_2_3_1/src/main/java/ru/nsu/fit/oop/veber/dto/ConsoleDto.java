package ru.nsu.fit.oop.veber.dto;

public class ConsoleDto extends BaseDto {
    private final Character symbol;

    public ConsoleDto(int x, int y, Character symbol) {
        super(x, y);
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }

}
