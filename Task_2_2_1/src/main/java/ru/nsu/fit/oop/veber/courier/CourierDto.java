package ru.nsu.fit.oop.veber.courier;

/**
 * Dto class for parsing config of pizzeria.
 *
 * @param baggageCount - maximum pizza count that courier can get.
 */
public record CourierDto(int baggageCount, int deliveryTimeMs) {
}
