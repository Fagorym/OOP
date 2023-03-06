public class CustomerImpl implements Customer {
    @Override
    public void orderPizza(OrderProvider provider) {
        Order order = new Order(1, 1);
        provider.addOrder(order);
    }
}
