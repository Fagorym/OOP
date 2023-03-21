public class CustomerImpl implements Customer {
    @Override
    public void orderPizza(OrderProvider provider, int pizzaCount) {
        provider.makeOrder(pizzaCount);
    }
}
