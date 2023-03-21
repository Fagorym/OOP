import java.util.Random;

public class Main {
    public static void main(String[] args) {
        OrderProvider pizzeria = new PizzeriaImpl();
        Random random = new Random();
        int customerCount = random.nextInt(3) + 3;
        Customer[] customers = new Customer[customerCount];
        for (int i = 0; i < customerCount; i++) {
            int pizzaCount = random.nextInt(3) + 1;
            customers[i] = new CustomerImpl();
            customers[i].orderPizza(pizzeria, pizzaCount);
        }
    }
}
