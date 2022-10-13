package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Tree<T> implements Collection<T> {
    private Node<T> root;

    public Tree(Object o) {
        checkNotNull(o);
        initRoot(o);

    }

    @Override
    public int size() {
        if (this.root == null) {
            return 0;
        } else {
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
        return toArray(root);

    }

    public Object[] toArray(Node<T> node) {
        Object[] result = new Object[this.size()];
        for (int i = 0; i < this.root.Nodes.size(); i++) {
            result[i] = this.root.Nodes.get(i);
        }
        return result;
    }

    @Override
    public boolean add(Object o) {
        addNode(root, o);
        return true;
    }

    @SuppressWarnings("unchecked")
    public void initRoot(Object o) {
        this.root = new Node<>();
        this.root.elem = (T) o;
        this.root.Nodes = new ArrayList<>();
    }

    public Node<T> addNode(Object o) {
        return addNode(this.root, o);
    }

    @SuppressWarnings("unchecked")
    public Node<T> addNode(Node<T> node, Object o) {
        checkNotNull(o);
        Node<T> newNode = new Node<>();
        newNode.elem = (T) o;
        newNode.Nodes = new ArrayList<>();
        node.Nodes.add(newNode);
        return newNode;
    }

    @Override
    public boolean remove(Object o) {
        checkNotNull(o);
        return remove(root, o);

    }

    @SuppressWarnings("unchecked")
    public boolean remove(Node<T> node, Object o) {
        checkNotNull(o);
        node.Nodes.removeIf((son) -> {
            return son.elem == (T) o;
        });
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (var val : c) {
            add(val);
        }
        return true;
    }

    public boolean addAll(Node<T> node, Collection c) {
        for (var val : c) {
            addNode(node, val);
        }
        return true;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return retainAll(this.root, c);
    }

    public boolean retainAll(Node<T> node, Collection<T> c) {
        return node.Nodes.removeIf((son) -> {
            return !c.contains(son.elem);
        });
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean deleteAllElements = true;
        for (var o : c) {
            if (!remove(o)) {
                deleteAllElements = false;
            }
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
        var array = new Object[a.length + this.root.Nodes.size()];
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }
        for (int i = a.length; i < a.length + this.root.Nodes.size(); i++) {
            array[i] = this.root.Nodes.get(i - a.length);
        }
        return array;
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
