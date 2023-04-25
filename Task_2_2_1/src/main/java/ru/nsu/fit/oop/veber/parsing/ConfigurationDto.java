package ru.nsu.fit.oop.veber.parsing;

import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.warehouse.WarehouseDto;

import java.util.List;

public record ConfigurationDto(List<CourierDto> couriers, List<BackerDto> backers, WarehouseDto warehouse) {
}
