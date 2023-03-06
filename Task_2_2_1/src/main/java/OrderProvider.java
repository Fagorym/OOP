import java.util.Queue;

public interface OrderProvider {
    void addOrder(Order order);

    Queue<Order> getOrders();
}
