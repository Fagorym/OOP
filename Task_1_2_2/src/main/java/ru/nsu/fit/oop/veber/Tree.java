package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class Tree<T> implements Collection<T> {
    private Node<T> root;

    @Override
    public int size() {
        return this.root.size;
    }

    @Override
    public boolean isEmpty() {
        return this.root.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        checkNotNull(o);
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o){
        add(root, o);
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean add(Node<T> node, Object o){
        checkNotNull(o);
        Node<T> newNode = new Node<>();
        newNode.elem = (T)o;
        node.Nodes.add(newNode);
        node.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        checkNotNull(o);
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        c.forEach(this::add);
        return true;
    }
    public boolean addAll(Node<T> node, Collection c) {
        c.forEach(o -> add(node,o));
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
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
       return this.root.Nodes.toArray();
    }

    private class Node<T> {
        T elem;
        private int size = 0;
        ArrayList<Node <T>> Nodes;
    }

    private void checkNotNull(Object o){
        if (o == null){
            throw new NullPointerException();
        }
    }
}
