public class BackerImpl implements Backer {
    private final int WORK_SPEED = 2000;
    private Order currentOrder;
    private final Warehouse warehouse;

    public BackerImpl(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public Pizza makePizza() throws InterruptedException {
        Thread.currentThread().wait(WORK_SPEED);
        Pizza pizza = new Pizza();
        currentOrder = null;
        return pizza;
    }

    @Override
    public void getOrder(Order order) {
        currentOrder = order;
    }

    @Override
    public void run() {
        try {
            Pizza pizza = makePizza();
            synchronized (warehouse) {
                while (warehouse.isFull()) {
                    wait();
                }
                warehouse.setPizzaCount(warehouse.getPizzaCount() + 1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
