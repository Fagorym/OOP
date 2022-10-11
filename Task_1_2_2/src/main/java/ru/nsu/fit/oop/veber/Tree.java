package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Tree<T> implements Collection<T> {
    private Node<T> root;

    public Tree(Object o) {
        if (o != null) {
            initRoot(o);
        }

    }

    @Override
    public int size() {
        if (this.root == null){
            return 0;
        }
        else {
            return this.root.Nodes.size() + 1;
        }
    }

    public int size(Node<T> node) {
        checkNotNull(node);
        return node.Nodes.size();
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean contains(Object o) {
        return contains(this.root, o);
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Node<T> node, Object o) {
        checkNotNull(o);
        if (this.root == null) {
            return false;
        }
        var elemToFind = (T) o;
        if (node.elem == elemToFind) return true;
        for (Node<T> exploringNode : node.Nodes) {
            if (contains(exploringNode, o)) return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        for (int i = 0; i < this.root.Nodes.size(); i++) {
            result[i] = this.root.Nodes.get(i);
        }
        return result;
    }

    @Override
    public boolean add(Object o) {
        add(root, o);
        return true;
    }

    @SuppressWarnings("unchecked")
    public void initRoot(Object o) {
        this.root = new Node<>();
        this.root.elem = (T) o;
        this.root.Nodes = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public boolean add(Node<T> node, Object o) {
        checkNotNull(o);
        Node<T> newNode = new Node<>();
        newNode.elem = (T) o;
        newNode.Nodes = new ArrayList<>();
        node.Nodes.add(newNode);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        checkNotNull(o);
        return remove(root, o);

    }

    public boolean remove(Node<T> node, Object o) {
        for (Node<T> exploringNode : node.Nodes) {
            if (exploringNode.elem == o) {
                node.Nodes.remove(exploringNode.elem);
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (var val : c) {
            add(c);
        }
        return true;
    }

    public boolean addAll(Node<T> node, Collection<T> c) {
        for (var val : c) {
            add(node, c);
        }
        return true;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean deleteAllElements = true;
        for (var o : c) {
            if (!remove(o)) {
                deleteAllElements = false;
            }
            ;
        }
        return deleteAllElements;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (var val : c) {
            if (!contains(val)) return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray(Object[] a) {
        return this.root.Nodes.toArray();
    }

    private void checkNotNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    private static class Node<T> {
        private T elem;
        private ArrayList<Node<T>> Nodes;
    }
}
