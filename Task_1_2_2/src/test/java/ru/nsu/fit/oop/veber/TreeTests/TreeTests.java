package ru.nsu.fit.oop.veber.TreeTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import ru.nsu.fit.oop.veber.Tree;

/**
 * Class that need to test methods from Tree.
 */
public class TreeTests {
    private Tree<Integer> tree;

    private void pushElements(int count) {
        for (int i = 0; i < count; i++) {
            tree.add(i);
        }
    }

    private ArrayList<Integer> makeList(int count) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        return list;
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
    public void testRemoveNodes() {
        pushElements(100);
        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(tree.remove(i));
        }
        Assertions.assertEquals(tree.size(), 1);
    }

    @Test
    public void testCheckSize() {
        pushElements(100);
        Assertions.assertEquals(tree.size(), 101);
    }

    @Test
    public void testAddNull() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.add(null));
    }

    @Test
    public void testContainsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.contains(null));
    }

    @Test
    public void testRemoveNull() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.remove(null));
    }

    @Test
    public void testClearTree() {
        tree.clear();
        Assertions.assertEquals(tree.size(), 0);
        Assertions.assertTrue(tree.isEmpty());
        Assertions.assertFalse(tree.contains(10));

    }

    @Test
    public void testAddSon() {
        var firstSon = tree.addNode(1);
        var secondSon = tree.addNode(firstSon, 2);
        Assertions.assertEquals(tree.size(firstSon), 1);
        Assertions.assertEquals(tree.size(secondSon), 0);
    }

    @Test
    public void testToArray() {
        pushElements(100);
        var arr = tree.toArray();
        Assertions.assertEquals(arr.length, 100 + 1);
    }

    @Test
    public void additionalTestToArray() {
        pushElements(100);
        var customArr = new Object[]{1, 2, 3, 4, 5};
        var arr = tree.toArray(customArr);
        Assertions.assertEquals(arr.length, 105);
    }

    @Test
    public void testAddAll() {
        ArrayList<Integer> list = makeList(100);
        tree.addAll(list);
        Assertions.assertTrue(tree.containsAll(list));
        list.add(191);
        Assertions.assertFalse(tree.containsAll(list));
    }

    @Test
    public void testDeleteAll() {
        pushElements(100);
        ArrayList<Integer> list = makeList(100);
        tree.removeAll(list);
        Assertions.assertFalse(tree.containsAll(list));
    }

    @Test
    public void testRetainAll() {
        pushElements(140);
        ArrayList<Integer> list = makeList(100);
        tree.retainAll(list);
        Assertions.assertEquals(101, tree.size());
    }

    @Test
    public void testSonAddAll() {
        var son = tree.addNode(2);
        ArrayList<Integer> list = makeList(100);
        tree.addAll(son, list);
        Assertions.assertEquals(100, tree.size(son));
    }

    @Test
    public void testIterator() {
        pushElements(100);
        var iter = tree.iterator();
        Assertions.assertEquals(0, iter.next());
        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, iter.next());
        }
    }

    @Test
    public void testNullIterator() {
        var iter = tree.iterator();
        iter.next();
        Assertions.assertThrows(IllegalStateException.class, iter::next);
    }


}
