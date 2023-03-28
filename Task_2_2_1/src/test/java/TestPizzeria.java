import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.customer.CustomerRepository;
import ru.nsu.fit.oop.veber.exception.PizzeriaParsingException;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;
import ru.nsu.fit.oop.veber.service.CustomerService;
import ru.nsu.fit.oop.veber.service.Service;
import ru.nsu.fit.oop.veber.service.WorkersService;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestPizzeria {
    private Pizzeria pizzeria;

    @BeforeEach
    public void init() {
        PizzeriaParser parser = new PizzeriaParser();
        ConfigurationDto configurationDto = parser.getConfigurationDtoFromFile("/testconfig.json");
        pizzeria = new PizzeriaImpl(configurationDto);
    }

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


        pizzeria.makeOrder(4);
        PizzaOrder order = pizzeria.getOrder();
        Assertions.assertEquals(order.getId(), 0);
        Assertions.assertEquals(order.getCount(), 4);
        Assertions.assertNull(order.getPizza());

        pizzeria.makeOrder(4);
        Backer backer = new BackerImpl(((PizzeriaImpl) pizzeria).getWarehouse(), pizzeria, configurationDto.backers().get(0).workingTime());
        backer.run();

        Courier courier = new CourierImpl(((PizzeriaImpl) pizzeria).getWarehouse(), 1);
        courier.run();
    }

    @Test
    public void testCustomerRepository() throws InterruptedException {
        CustomerRepository customerRepository = new CustomerRepository(1, pizzeria);
        List<Runnable> customers = customerRepository.generateCustomers();
        Assertions.assertTrue(customers.size() >= 1 && customers.size() <= 4);
        customers.get(0).run();

        CustomerService customerService = new CustomerService(pizzeria);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Void> future = executorService.submit(customerService);

        Thread.sleep(1000);
        Assertions.assertFalse(future.isDone());

        customerService.closeService();
        Thread.sleep(1000);

        Assertions.assertTrue(future.isDone());
    }

    @Test
    public void testServices() throws ExecutionException, InterruptedException {
        PizzeriaImpl pizzeria1 = (PizzeriaImpl) pizzeria;
        Service service = new WorkersService(pizzeria1.getBackers(), pizzeria1.getCouriers(), pizzeria);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Void> future = executorService.submit(service);
        Assertions.assertFalse(future.isDone());
        service.closeService();
        Assertions.assertNull(future.get());
    }

}
