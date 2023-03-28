import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerRepository;
import ru.nsu.fit.oop.veber.exception.PizzeriaParsingException;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.List;

public class TestPizzeria {
    @Test
    public void TestWarehouse() throws InterruptedException {
        Warehouse warehouse = new WarehouseImpl(2);
        Assertions.assertFalse(warehouse.isFull());
        Assertions.assertTrue(warehouse.isEmpty());
        warehouse.addPizza(new PizzaOrder(1, 2));
        Assertions.assertFalse(warehouse.isEmpty());
        Assertions.assertFalse(warehouse.isFull());
        warehouse.addPizza(new PizzaOrder(2, 4));
        Assertions.assertTrue(warehouse.isFull());
        Assertions.assertFalse(warehouse.isEmpty());
    }

    @Test
    public void TestParser() throws InterruptedException {
        PizzeriaParser parser = new PizzeriaParser();
        ConfigurationDto configurationDto = parser.getConfigurationDtoFromFile("/testconfig.json");
        Assertions.assertThrows(PizzeriaParsingException.class,
                () ->
                        parser.getConfigurationDtoFromFile("/unexistedfile.json.txt.csv"));
        Assertions.assertEquals(configurationDto.backers().size(), 2);
        int i = 0;
        for (BackerDto backerDto : configurationDto.backers()) {
            Assertions.assertEquals(backerDto.workingTime(), i++);
        }
        Assertions.assertEquals(configurationDto.couriers().size(), 2);
        i = 0;
        for (CourierDto courierDto : configurationDto.couriers()) {
            Assertions.assertEquals(courierDto.baggageCount(), i++);
        }
        Assertions.assertEquals(configurationDto.warehouse().capacity(), 5);


        Pizzeria pizzeria = new PizzeriaImpl(configurationDto);
        pizzeria.makeOrder(4);
        PizzaOrder order = pizzeria.getOrder();
        Assertions.assertEquals(order.getId(), 0);
        Assertions.assertEquals(order.getCount(), 4);
        Assertions.assertNull(order.getPizza());

        pizzeria.makeOrder(4);
        Backer backer = new BackerImpl(pizzeria.getWarehouse(), pizzeria, configurationDto.backers().get(0).workingTime());
        backer.run();

        Courier courier = new CourierImpl(pizzeria.getWarehouse(), 1);
        courier.run();
    }

    @Test
    public void testCustomerRepository() {
        PizzeriaParser parser = new PizzeriaParser();
        ConfigurationDto configurationDto = parser.getConfigurationDtoFromFile("/testconfig.json");
        Pizzeria pizzeria = new PizzeriaImpl(configurationDto);
        CustomerRepository customerRepository = new CustomerRepository(1, pizzeria);
        List<Customer> customers = customerRepository.generateCustomers();
        Assertions.assertTrue(customers.size() >= 1 && customers.size() <= 4);
    }

}
