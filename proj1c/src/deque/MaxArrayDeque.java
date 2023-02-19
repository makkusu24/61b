package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private T[] items;
    private Comparator<T> comparator;
    private int size;
    private int front;
    private int back;
    private static final int DEFAULT_SIZE = 8;

    public MaxArrayDeque(Comparator<T> c) {
        items = (T[]) new Object[DEFAULT_SIZE];
        this.comparator = c;
        size = 0;
        front = 0;
        back = (-1 + items.length) % items.length;
    }

    public static void main(String[] args) {
        intComparator ic = new intComparator();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(ic);
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        mad.addLast(4);
        System.out.println(mad.max());
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (T element : this) {
            if (comparator.compare(element, max) == 1) {
                max = element;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (T element : this) {
            if (c.compare(element, max) == 1) {
                max = element;
            }
        }
        return max;
    }

}
