package nsu.fit.veber.heapsort;

public class Main {
    public static void main(String[] args) {
        int[] result = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            result[i] = Integer.parseInt(args[i]);
        }
        Heap heap = new Heap(result);
        heap.sort();
        heap.print();
    }
}