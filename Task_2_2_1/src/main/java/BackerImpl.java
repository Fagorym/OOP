public class BackerImpl implements Backer {
    private final int WORK_SPEED = 2000;
    private Order currentOrder;

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
}
