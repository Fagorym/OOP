public class Main {
    public static void main(String[] args) {
        int [] myArray = new int[]{-1,2,3,51,23,45,124,2334,575,22,34,1,-100,-2303532};
        Heap heap = new Heap(myArray);
        heap.sort();
        heap.print();
    }
}