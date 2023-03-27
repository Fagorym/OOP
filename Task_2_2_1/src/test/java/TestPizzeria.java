import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

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
}
