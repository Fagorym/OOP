import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.customer.CustomerImpl;
import ru.nsu.fit.oop.veber.exception.PizzeriaParsingException;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;
import ru.nsu.fit.oop.veber.service.BackerService;
import ru.nsu.fit.oop.veber.service.CourierService;
import ru.nsu.fit.oop.veber.service.CustomerGenerator;
import ru.nsu.fit.oop.veber.service.CustomerService;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.List;


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
        int i = 10000;
        for (BackerDto backerDto : configurationDto.backers()) {
            Assertions.assertEquals(backerDto.workingTimeMs(), i++);
        }
        Assertions.assertEquals(configurationDto.couriers().size(), 2);
        i = 1;
        for (CourierDto courierDto : configurationDto.couriers()) {
            Assertions.assertEquals(courierDto.baggageCount(), i++);
        }
        Assertions.assertEquals(configurationDto.warehouse().capacity(), 1);

        BackerDto firstBackerDto = configurationDto.backers().get(0);
        CourierDto firstCourierDto = configurationDto.couriers().get(0);
        Warehouse warehouse = ((PizzeriaImpl) pizzeria).getWarehouse();

        pizzeria.makeOrder(4);
        Backer backer = new BackerImpl(warehouse, pizzeria, firstBackerDto.workingTimeMs());
        Thread backerThread = new Thread(backer);
        backerThread.start();
        Assertions.assertFalse(warehouse.isFull());

        Thread.sleep(firstBackerDto.workingTimeMs() + 1000);
        Assertions.assertTrue(pizzeria.isNoOrders());
        Assertions.assertTrue(warehouse.isFull());

        Courier courier = new CourierImpl(warehouse, firstCourierDto.baggageCount(), firstCourierDto.deliveryTimeMs());
        Thread courierThread = new Thread(courier);
        courierThread.start();
        Thread.sleep(firstCourierDto.deliveryTimeMs());

        Assertions.assertTrue(backerThread.isAlive());
        Assertions.assertTrue(courierThread.isAlive());

        backerThread.interrupt();
        courierThread.interrupt();
        Thread.sleep(500);
        Assertions.assertFalse(backerThread.isAlive());
        Assertions.assertFalse(courierThread.isAlive());

    }

    @Test
    public void testCustomerRepository() {
        CustomerGenerator generator = new CustomerService(pizzeria);
        List<Runnable> customers = generator.generate();
        Assertions.assertTrue(customers.size() >= 3 && customers.size() <= 6);
    }

    @Test
    public void testCustomerServices() throws InterruptedException {
        Assertions.assertTrue(pizzeria.isNoOrders());
        CustomerService service = new CustomerService(pizzeria);
        Thread customerServiceThread = new Thread(service);
        customerServiceThread.start();
        Thread.sleep(1000);
        Assertions.assertFalse(pizzeria.isNoOrders());
        service.stopService();
        while (!pizzeria.isNoOrders()) {
            pizzeria.getOrder();
        }
        Thread.sleep(3000);
        Assertions.assertTrue(pizzeria.isNoOrders());
        System.out.println(customerServiceThread.isAlive());
    }

    @Test
    public void testWorkerService() throws InterruptedException {
        Thread customerThread = new Thread(new CustomerImpl(pizzeria, 1));
        customerThread.start();
        Thread.sleep(1000);
        Assertions.assertFalse(pizzeria.isNoOrders());
        customerThread.interrupt();

        BackerService backerService = ((PizzeriaImpl) pizzeria).getBackerService();
        Thread backerServiceThread = new Thread(backerService);
        backerServiceThread.start();
        Thread.sleep(11001);
        Assertions.assertTrue(pizzeria.isNoOrders());

        backerService.stopService();
        Thread.sleep(1000);
        Assertions.assertFalse(backerServiceThread.isAlive());


        CourierService courierService = ((PizzeriaImpl) pizzeria).getCourierService();
        Thread courierServiceThread = new Thread(courierService);
        courierServiceThread.start();
        Assertions.assertFalse(((PizzeriaImpl) pizzeria).getWarehouse().isEmpty());
        Thread.sleep(5000);
        Assertions.assertTrue(((PizzeriaImpl) pizzeria).getWarehouse().isEmpty());

        courierService.stopService();
        Thread.sleep(1000);
        Assertions.assertFalse(courierServiceThread.isAlive());

    }


}
