package ru.nsu.fit.oop.veber;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private final int MAX_SIZE = 1000;
    private int capacity;
    private int topIdx;
    private Object[] arr;
    Stack(){
        this.arr = new Object[MAX_SIZE];
        this.capacity = MAX_SIZE;
        this.topIdx = -1;
    }
    Stack(int size){
        this.arr = new Object[size];
        this.capacity = size;
        this.topIdx = -1;
    }
    public boolean push(Object elem){
        if (topIdx >= capacity - 1){
            return false;
        }
        else {
            arr[++topIdx] = elem;
            return true;
        }
    }
    public boolean pushStack(Stack elems){
        if (topIdx >= capacity - (elems.topIdx + 1)){
            return false;
        }
        else {
            for (Object elem : elems.arr) {
                if (elem != null) push(elem);
            }
            return true;
        }
    }
    public Object pop() throws EmptyStackException {
      if (topIdx == -1){
          throw new EmptyStackException();
      }
      else {
          Object temp = arr[topIdx];
          arr[topIdx--] = null;
          return temp;
      }

    }
    public Stack popStack(int count) throws EmptyStackException {
        if (topIdx - count < -1){
            throw new EmptyStackException();
        }
        else {
          Stack result = new Stack();
          Object[] temp = Arrays.copyOfRange(this.arr, topIdx-count+1, this.topIdx +1);
            for (int i = 0; i < count; i++) {
                this.pop();
            }
          result.arr = temp;
          result.topIdx = temp.length - 1;
          return result;
        }
    }
    public int count(){
        return topIdx+1;

    }
    public void print(){
        for (Object elem: arr){
            if (elem == null) return;
            System.out.println(elem);
        }
    }
}
