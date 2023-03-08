import java.util.*;

public class PizzeriaImpl implements OrderProvider {
    private final List<Backer> backers;
    private final List<Courier> couriers;
    private final Warehouse warehouse;
    private final Queue<Order> orders;


    public PizzeriaImpl() {
        backers = new ArrayList<>();
        couriers = new ArrayList<>();
        warehouse = new WarehouseImpl();
        orders = new ArrayDeque<>();
        generateBackers();
        generateCouriers();
    }

    private void generateCouriers() {
        Random random = new Random();
        int maxWorkers = random.nextInt(4) + 2;
        for (int i = 0; i < maxWorkers; i++) {
            Courier courier = new CourierImpl();
            couriers.add(courier);
        }
    }

    private void generateBackers() {
        Random random = new Random();
        int maxBackers = random.nextInt(4) + 2;
        for (int i = 0; i < maxBackers; i++) {
            Backer backer = new BackerImpl();
            backers.add(backer);
        }
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("В пиццерию поступил заказ с номером " + order.getId());
    }

    @Override
    public Queue<Order> getOrders() {
        return orders;
    }
}
