package ru.nsu.fit.oop.veber.TreeTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Tree;
import org.junit.jupiter.api.Assertions;

public class TreeTests {
    private Tree<Integer> tree;

    private void pushElements(int count) {
        for (int i = 0; i < count; i++) {
            tree.add(i);
        }
    }

    @BeforeEach
    public void initTree() {
        tree = new Tree<>(0);
    }

    @Test
    public void testAddNodes() {
        pushElements(100);
        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(tree.contains(i));
        }
    }

    @Test
    public void testRemoveNodes(){
       pushElements(200);
        for (int i = 0; i < 200; i++) {
            Assertions.assertTrue(tree.remove(i));
        }
    }

    @Test
    public void testCheckSize() {
        pushElements(100);
        Assertions.assertEquals(tree.size(), 101);
    }
    @Test
    public void testAddNull(){
        Assertions.assertThrows(NullPointerException.class, () -> tree.add(null));
    }
    @Test
    public void testContainsNull(){
        Assertions.assertThrows(NullPointerException.class, () -> tree.contains(null));
    }
    @Test
    public void testRemoveNull(){
        Assertions.assertThrows(NullPointerException.class, () -> tree.remove(null));
    }

    @Test
    public void testClearTree(){
        tree.clear();
        Assertions.assertEquals(tree.size(),0);
        Assertions.assertTrue(tree.isEmpty());

    }   
}
