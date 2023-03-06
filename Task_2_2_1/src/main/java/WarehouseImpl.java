public class WarehouseImpl implements Warehouse {
    private int pizzaCount = 0;

    @Override
    public int getPizzaCount() {
        return pizzaCount;
    }

    @Override
    public void setPizzaCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Pizza count cannot be less than zero.");
        }
        pizzaCount = count;
    }
}
