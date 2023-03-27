package ru.nsu.fit.oop.veber.parsing;

import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.warehouse.WarehouseDto;

import java.util.List;

public class ConfigurationDto {
    private final List<CourierDto> couriers;
    private final List<BackerDto> backers;
    private final WarehouseDto warehouse;

    public ConfigurationDto(List<CourierDto> couriers, List<BackerDto> backers, WarehouseDto warehouse) {
        this.couriers = couriers;
        this.backers = backers;
        this.warehouse = warehouse;
    }

    public List<BackerDto> getBackers() {
        return backers;
    }

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public WarehouseDto getWarehouse() {
        return warehouse;
    }
}