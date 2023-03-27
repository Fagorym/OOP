package ru.nsu.fit.oop.veber.parsing;

import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.warehouse.WarehouseDto;

import java.util.List;

public class ConfigurationDto {
    private List<CourierDto> couriers;
    private List<BackerDto> backers;
    private WarehouseDto warehouse;

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
