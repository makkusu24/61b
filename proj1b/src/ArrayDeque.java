import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items; // array of generics
    private int size; // cache with each operation
    private int front; // keeps track of start of elements
    private int back; // keeps track of end of elements
    private static final int DEFAULT_SIZE = 8; // back array = 8 by default

    /**
     * Constructor for every new instance of an ArrayDeque.
     * Items is type casted as a generic.
     * Size is 0 for an empty array.
     * Front is at the beginning of the array.
     * Back can be -1 because the indexing is circular.
     *
     * @return newly instantiated ArrayDeque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        front = 0;
        back = -1;
    }

    public static void main(String[] args) {
        Deque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            ad1.addLast(i);
        }
        for (int i = 0; i < 75; i++) {
            ad1.removeLast();
        }
        System.out.println(ad1.toList());
    }

    /**
     * Creates a new array with a larger/smaller size and identical elements.
     *
     * @param desiredSize input can be additive or geometric.
     * @return new ArrayDeque copy
     */
    private void resize(int desiredSize) {
        T[] sized = (T[]) new Object[desiredSize];
        for (int i = 0; i < size; i++) {
            int index = (front + i) % items.length;
            sized[i] = items[index];
        }
        items = sized;
        front = 0;
        back = size - 1;
    }

    /**
     * Calls resize if adding would exceed instantiated size.
     *
     * @param x item to add in front of previous front pointer.
     * @return altered ArrayDeque.
     */
    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = x;
        size += 1;
    }

    /**
     * Calls resize if adding would exceed instantiated size.
     *
     * @param x item to add behind the previous back pointer.
     * @return altered ArrayDeque.
     */
    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        back = (back + 1) % items.length;
        items[back] = x;
        size += 1;
    }

    /**
     * Starts adding from front pointer.
     *
     * @return ArrayList representation of non-null elements in ArrayDeque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = (front + i) % items.length;
            if (items[index] != null) {
                returnList.add(items[index]);
            }
        }
        return returnList;
    }

    /**
     * Regardless of length, returns empty if no elements.
     *
     * @return true or false.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * All add and remove operations cache size of every ArrayDeque.
     *
     * @return positive integer of non-null element count.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    /**
     * Removes element at whatever index front pointer is at.
     *
     * @return altered ArrayDeque.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[front];
        items[front] = null; // removes pointer to save memory
        front = (front + 1) % items.length;
        size -= 1;
        if (size == 0) {
            front = 0;
            back = -1;
        } else if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Removes element at whatever index back pointer is at.
     *
     * @return altered ArrayDeque.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[back + 1];
        items[back + 1] = null; // removes pointer to save memory
        back = (back - 1 + items.length) % items.length;
        size -= 1;
        if (size == 0) {
            front = 0;
            back = -1;
        } else if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Counts index starting from the front pointer.
     *
     * @param index non-null element index.
     * @return null if out of bounds or negative. Element at index otherwise.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return items[(front + index) % items.length];
    }
}
