package nsu.fit.veber.heapsortTest;

import nsu.fit.veber.heapsort.Heap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class HeapSortTest {

    @Test
    void sortEmptyArray() {
        var arr = new int[]{};
        var heap = new Heap(arr);
        heap.sort();
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void sortNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new Heap(null);
                });
    }
    @Test
    void sortingSameElemArray(){
        var arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 1;
        }
        var arr2 = Arrays.copyOf(arr,1000);
        Arrays.sort(arr2);
        var heap = new Heap(arr);
        heap.sort();
        Assertions.assertArrayEquals(arr2,arr);
    }

    @Test
    void sortingLargeArray(){
        Random rnd = new Random(333);
        var arr = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            arr[i] = rnd.nextInt(10)+7;
        }
        var arr2 = Arrays.copyOf(arr,1000000);
        Arrays.sort(arr2);
        var heap = new Heap(arr);
        heap.sort();
        Assertions.assertArrayEquals(arr2,arr);
    }

}
