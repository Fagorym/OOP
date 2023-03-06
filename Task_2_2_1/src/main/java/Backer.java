public interface Backer {
    Pizza makePizza() throws InterruptedException;

    void getOrder(Order order);
}
