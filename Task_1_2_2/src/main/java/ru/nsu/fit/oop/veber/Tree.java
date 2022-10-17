package ru.nsu.fit.oop.veber;

import java.util.*;

/**
 * Tree contains some ArrayList of nodes, in each Node we have element of custom type.
 *
 * @param <T> could be any parameter
 */
public class Tree<T> implements Collection<T> {
    private Node<T> root;

    /**
     * Constructor of Tree if element was not provided.
     */
    public Tree() {
        this.root = new Node<>();
        this.root.nodes = new ArrayList<>();
    }

    /**
     * Constructor of Tree if was provided some element as param.
     *
     * @param o - will be element of the tree root
     */

    @SuppressWarnings("unchecked")
    public Tree(Object o) {
        this.root = new Node<>();
        this.root.elem = (T) o;
        this.root.nodes = new ArrayList<>();

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
            return this.root.nodes.size() + 1;
        }
    }

    /**
     * Method to know how many sons in the current node.
     *
     * @param node - current node to count sons
     * @return Size of ArrayList of node
     */
    public int size(Node<T> node) {
        return node.nodes.size();
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
        if (this.root == null) {
            return false;
        }
        var elemToFind = (T) o;
        if (node.elem == elemToFind) return true;
        for (Node<T> exploringNode : node.nodes) {
            if (contains(exploringNode, o)) return true;
        }
        return false;
    }

    /**
     * Creates instance of default (BFS) iterator and return it.
     *
     * @return BFS Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new BFSIterator<>(this.root);
    }

    public Iterator<T> DFSIterator() {
        return new DFSIterator<>(this.root);
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
        for (int i = 0; i < node.nodes.size(); i++) {
            result[i] = node.nodes.get(i);
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
        var array = new Object[a.length + this.root.nodes.size()];
        System.arraycopy(a, 0, array, 0, a.length);
        for (int i = a.length; i < a.length + this.root.nodes.size(); i++) {
            array[i] = this.root.nodes.get(i - a.length);
        }
        return array;
    }

    /**
     * Adds new node to the root.
     *
     * @param o element whose presence in this collection is to be ensured
     * @return true - element was added, true - element was not added
     */

    @Override
    public boolean add(Object o) {
        addNode(root, o);
        return true;
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

    /**
     * Adds new node to the current node.
     *
     * @param node - will parent of the new node
     * @param o    - will be element of the new node
     * @return created node
     */
    @SuppressWarnings("unchecked")
    public Node<T> addNode(Node<T> node, Object o) {
        Node<T> newNode = new Node<>();
        newNode.elem = (T) o;
        newNode.nodes = new ArrayList<>();
        node.nodes.add(newNode);
        return newNode;
    }

    /**
     * Remove node from root.
     *
     * @param o element to be removed from this collection, if present
     * @return true - at least one node was deleted, false - no element was deleted
     */
    @Override
    public boolean remove(Object o) {
        return remove(root, o);

    }

    /**
     * Remove node from current node
     *
     * @param node - from where we need to delete node
     * @param o    - element to be removed from this collection, if present
     * @return - true - at least one node was deleted, false - no element was deleted
     */

    public boolean remove(Node<T> node, Object o) {
        node.nodes.removeIf((son) -> {
            if (son.elem == o) {
                for (Node<T> secondSon : son.nodes) {
                    addNode(node, secondSon.elem);
                }
                return true;
            }
            return false;

        });
        return true;
    }

    /**
     * Adds all elements to root from current collection.
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

    /**
     * Adds all elements to current node from current collection.
     *
     * @param node - to which node elements will be added
     * @param c    collection containing elements to be added to this collection
     * @return true - all elements were added, false - at least one element was not added
     */
    public boolean addAll(Node<T> node, Collection<T> c) {
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

    /**
     * Retains element from root that not containing in collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return always true
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        return retainAll(this.root, (Collection<Object>) c);
    }

    /**
     * Retains element from current node that not containing in collection.
     *
     * @param node - from which node retain elements
     * @param c    collection containing elements to be retained in this collection
     * @return always true
     */

    public boolean retainAll(Node<T> node, Collection<Object> c) {
        return node.nodes.removeIf((son) -> {
            retainAll(son, c);
            return !c.contains(son.elem);
        });
    }

    /**
     * Removes all elements from root that collection contains.
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

    /**
     * Checks if all elements from current collection contains in the tree.
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


    private static class DFSIterator<T> implements Iterator<T> {
        Deque<Node<T>> nodesToVisit;

        DFSIterator(Node<T> root) {
            this.nodesToVisit = new ArrayDeque<>();
            this.nodesToVisit.add(root);
        }


        private void addToQueue(Node<T> node) {
            if (node != null) {
                for (int i = node.nodes.size() - 1; i >= 0; i--) {
                    this.nodesToVisit.addFirst(node.nodes.get(i));
                }
            }
        }


        @Override
        public boolean hasNext() {
            return !this.nodesToVisit.isEmpty();
        }

        @Override
        public T next() {
            return this.nextNode().elem;
        }

        public Node<T> nextNode() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            var firstNode = nodesToVisit.removeFirst();
            addToQueue(firstNode);
            return firstNode;

        }
    }

    private static class BFSIterator<T> implements Iterator<T> {
        List<Node<T>> nodesToVisit;

        BFSIterator(Node<T> root) {
            this.nodesToVisit = new ArrayList<>();
            this.nodesToVisit.add(root);
        }


        private void addToQueue(Node<T> node) {
            if (node != null) {
                this.nodesToVisit.addAll(node.nodes);
            }
        }


        @Override
        public boolean hasNext() {
            return !this.nodesToVisit.isEmpty();
        }

        @Override
        public T next() {
            return this.nextNode().elem;
        }

        public Node<T> nextNode() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            var firstNode = nodesToVisit.remove(0);
            addToQueue(firstNode);
            return firstNode;

        }
    }

    private static class Node<T> {
        private T elem;
        private List<Node<T>> nodes;
    }
}
