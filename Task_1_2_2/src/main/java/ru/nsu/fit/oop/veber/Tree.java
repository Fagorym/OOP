package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Tree contains some ArrayList of Nodes, in each Node we have element of custom type.
 *
 * @param <T> could be any parameter
 */
public class Tree<T> implements Collection<T> {
    private Node<T> root;

    /**
     * Constructor of Tree if was provided some element as param
     *
     * @param o - will be element of the tree root
     */
    public Tree(Object o) {
        checkNotNull(o);
        initRoot(o);

    }

    /**
     * Method to know how many sons in the root.
     *
     * @return Size of ArrayList of root
     */
    @Override
    public int size() {
        if (this.root == null) {
            return 0;
        } else {
            return this.root.Nodes.size() + 1;
        }
    }

    /**
     * Method to know how many sons in the current node.
     *
     * @param node - current node to count sons
     * @return Size of ArrayList of node
     */
    public int size(Node<T> node) {
        checkNotNull(node);
        return node.Nodes.size();
    }

    /**
     * Boolean function to know, is that empty tree or not.
     *
     * @return if empty - true, not empty - false
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Function to find element in the tree.
     *
     * @param o element whose presence in this collection is to be tested
     * @return true - collection contains element, false - doesnt contain
     */
    @Override
    public boolean contains(Object o) {
        return contains(this.root, o);
    }

    /**
     * Function to find element in the subtree.
     *
     * @param node - root of the subtree
     * @param o    - element whose presence in this collection is to be tested
     * @return true - subtree contains element, false - doesnt contain
     */
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
    public Iterator<T> iterator() {
        return null;
    }

    /**
     * Function that need to get root sons as array.
     *
     * @return array of root sons
     */
    @Override
    public Object[] toArray() {
        return toArray(root);

    }

    /**
     * Function that need to get current node sons as array.
     *
     * @param node - root of subtree
     * @return array of current node sons
     */
    public Object[] toArray(Node<T> node) {
        Object[] result = new Object[this.size()];
        for (int i = 0; i < this.root.Nodes.size(); i++) {
            result[i] = this.root.Nodes.get(i);
        }
        return result;
    }

    /**
     * Function that need to get root sons as array.
     *
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return Array of root sons
     */
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

    /**
     * Adds new node to the root.
     *
     * @param o element whose presence in this collection is to be ensured
     * @return true - element was added, true - element wasn`t added
     */

    @Override
    public boolean add(Object o) {
        addNode(root, o);
        return true;
    }

    /**
     * If there is no root in the tree, that function creates a new one
     *
     * @param o - will be element of the root
     */
    @SuppressWarnings("unchecked")
    public void initRoot(Object o) {
        this.root = new Node<>();
        this.root.elem = (T) o;
        this.root.Nodes = new ArrayList<>();
    }

    /**
     * Adds new node to the root.
     *
     * @param o - will be element of the new node
     * @return created node
     */
    public Node<T> addNode(Object o) {
        return addNode(this.root, o);
    }

    /** Adds new node to the current node.
     *
     * @param node - will parent of the new node
     * @param o - will be element of the new node
     * @return created node
     */
    @SuppressWarnings("unchecked")
    public Node<T> addNode(Node<T> node, Object o) {
        checkNotNull(o);
        Node<T> newNode = new Node<>();
        newNode.elem = (T) o;
        newNode.Nodes = new ArrayList<>();
        node.Nodes.add(newNode);
        return newNode;
    }

    /**
     * Remove node from root.
     * @param o element to be removed from this collection, if present
     * @return true - at least one node was deleted, false - no element was deleted
     */
    @Override
    public boolean remove(Object o) {
        checkNotNull(o);
        return remove(root, o);

    }

    /**
     * Remove node from current node
     * @param node - from where we need to delete node
     * @param o - element to be removed from this collection, if present
     * @return - true - at least one node was deleted, false - no element was deleted
     */
    @SuppressWarnings("unchecked")
    public boolean remove(Node<T> node, Object o) {
        checkNotNull(o);
        node.Nodes.removeIf((son) -> {
            if (son.elem == (T) o) {
                for (Node<T> secondSon : son.Nodes) {
                    addNode(node, secondSon.elem);
                }
                return true;
            }
            return false;

        });
        return true;
    }

    /** Adds all elements to root from current collection.
     *
     * @param c collection containing elements to be added to this collection
     * @return true - all elements were added, false - at least one element was not added
     */
    @Override
    public boolean addAll(Collection c) {
        for (var val : c) {
            add(val);
        }
        return true;
    }

    /** Adds all elements to current node from current collection.
     *
     * @param node - to which node elements will be added
     * @param c collection containing elements to be added to this collection
     * @return true - all elements were added, false - at least one element was not added
     */
    public boolean addAll(Node<T> node, Collection c) {
        for (var val : c) {
            addNode(node, val);
        }
        return true;
    }

    /**
     * Clears the tree.
     */
    @Override
    public void clear() {
        this.root = null;
    }

    /** Retains element from root that not containing in collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return always true
     */
    @Override
    public boolean retainAll(Collection c) {
        return retainAll(this.root, c);
    }

    /** Retains element from current node that not containing in collection.
     *
     * @param node - from which node retain elements
     * @param c collection containing elements to be retained in this collection
     * @return always true
     */

    public boolean retainAll(Node<T> node, Collection<T> c) {
        return node.Nodes.removeIf((son) -> !c.contains(son.elem));
    }

    /** Removes all elements from root that collection contains.
     *
     * @param c collection containing elements to be removed from this collection
     * @return true - all elements was deleted at least one time, false - at least one element was not deleted
     */
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

    /** Checks if all elements from current collection contains in the tree.
     *
     * @param c collection to be checked for containment in this collection
     * @return true - all elements contains, false - at least one element doesnt contains in the tree
     */
    @Override
    public boolean containsAll(Collection c) {
        for (var val : c) {
            if (!contains(val)) return false;
        }
        return true;
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
