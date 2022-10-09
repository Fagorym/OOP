package ru.nsu.fit.oop.veber.TreeTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Tree;
import org.junit.jupiter.api.Assertions;

public class TreeTests {
    private Tree<Integer> tree;
    private int size;

    private void pushElements(int count){
        for (int i = 0; i < count; i++) {
            tree.add(i);
        }
    }

    @BeforeEach
    public void initTree(){
           tree = new Tree<>(0);
    }

    @Test
    public void addNodes(){
        pushElements(100);
        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(tree.contains(i));
        }
    }
    @Test
    public void checkSize(){
        pushElements(100);
        Assertions.assertEquals(tree.size(), 101);
    }
}
