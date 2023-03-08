public interface Backer extends Runnable {
    Pizza makePizza() throws InterruptedException;

    void getOrder(Order order);
}
