import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PizzeriaImpl implements OrderProvider {
    private List<Backer> backers;
    private List<Courier> couriers;
    private Warehouse warehouse;
    private Queue<Order> orders;


    public PizzeriaImpl() {
        backers = new ArrayList<>();
        couriers = new ArrayList<>();
        warehouse = new WarehouseImpl();
        orders = new ArrayDeque<>();
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
