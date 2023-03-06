public class Order {
    private final int id;
    private final int count;

    public Order(int id, int count) {
        this.id = id;
        this.count = count;
    }

    int getId() {
        return id;
    }

    int getCount() {
        return count;
    }
}
