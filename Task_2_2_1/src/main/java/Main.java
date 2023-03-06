public class Main {
    public static void main(String[] args) {
        OrderProvider pizzeria = new PizzeriaImpl();
        Customer customer = new CustomerImpl();
        customer.orderPizza(pizzeria);
    }
}
