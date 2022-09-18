package org.example;

public class Heap {
    private int[] heap;
    private int length;

    private void swap(int firstIdx, int sndIdx) {

        int value = this.heap[firstIdx];
        this.heap[firstIdx] = this.heap[sndIdx];
        this.heap[sndIdx] = value;

    }

    Heap(int[] arr) {
        this.length = arr.length;
        this.heap = arr;
        for (int i = 0; i < this.length - 1; i++) {
            siftUp(i);
        }
    }

    public void sort() {
        for (int i = 0; i < this.heap.length; i++) {
            extractMax();
        }
    }

    private void siftUp(int elemIdx) {
        int fatherNum = (elemIdx - 1) / 2;
        if (elemIdx == 0) return;
        else if (this.heap[fatherNum] < this.heap[elemIdx]) {
            swap(fatherNum, elemIdx);
            siftUp(fatherNum);
        }
    }

    private void siftDown(int elemIdx) {
        int leftSon = 2 * elemIdx + 1;
        int rightSon = 2 * elemIdx + 2;
        if (this.length <= leftSon) return;

        else if (this.length > leftSon && this.length == rightSon) {
            if (this.heap[leftSon] > this.heap[elemIdx]) {
                swap(leftSon, elemIdx);
                siftDown(leftSon);
            } else return;
        } else if (this.heap[leftSon] >= this.heap[rightSon] && this.heap[leftSon] > this.heap[elemIdx]) {
            swap(leftSon, elemIdx);
            siftDown(leftSon);
        } else if (this.heap[rightSon] >= this.heap[leftSon] && this.heap[rightSon] > this.heap[elemIdx]) {
            swap(rightSon, elemIdx);
            siftDown(rightSon);
        }


    }


    private int extractMax() {

        int max = heap[0];
        this.length -= 1;
        swap(0, this.length);
        siftDown(0);
        return max;
    }

    public void print() {
        for (int i = 0; i < this.heap.length; i++) {
            System.out.println(this.heap[i]);
        }
    }
}


