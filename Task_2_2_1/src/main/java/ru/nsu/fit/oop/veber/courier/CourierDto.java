package ru.nsu.fit.oop.veber.courier;

public class CourierDto {
    private final int baggageCount;

    public CourierDto(int baggageCount) {
        this.baggageCount = baggageCount;
    }

    public int getBaggageCount() {
        return baggageCount;
    }
}
