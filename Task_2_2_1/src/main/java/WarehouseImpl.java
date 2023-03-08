public class WarehouseImpl implements Warehouse {
    private int pizzaCount = 0;
    private final int capacity;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
    }

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

    @Override
    public boolean isFull() {
        return false;
    }
}
