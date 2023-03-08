package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.HashSet;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor;
    private int n;
    private int m;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOADFACTOR = 0.75;

    /** Constructors */
    public MyHashMap() {
        new MyHashMap(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    public MyHashMap(int initialCapacity) {
        new MyHashMap(initialCapacity, DEFAULT_LOADFACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.buckets = createTable(initialCapacity);
        this.loadFactor = loadFactor;
        this.n = 0;
        this.m = initialCapacity;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] temp = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            temp[i] = createBucket();
        }
        return temp;
    }

    // Map 61B Interface implementation below
    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        if (this.m != 0 && ((double) n / m) > this.loadFactor) {
            resize(m * 2);
        }
        if (!this.containsKey(key)) {
            this.n += 1;
        }
        Node putNode = createNode(key, value);
        buckets[hash(key)].add(putNode);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            throw new IllegalArgumentException("key not in map");
        }
        for (Node node : buckets[hash(key)]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (Node node : buckets[hash(key)]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.n;
    }

    @Override
    public void clear() {
        this.buckets = createTable(this.m);
        this.n = 0;
    }

    private int hash(K key) {
        if (this.m == 0) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), this.m);
    }

    private void resize(int newSize) {
        MyHashMap temp = new MyHashMap(newSize, this.loadFactor);
        for (int i = 0; i < this.m; i++) {
            for (Node node : buckets[i]) {
                temp.put(node.key, node.value);
            }
        }
        this.buckets = temp.buckets;
        this.n = temp.n;
        this.m = temp.m;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
