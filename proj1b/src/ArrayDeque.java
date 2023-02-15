import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int front;
    private int back;
    private static final int DEFAULT_SIZE = 8;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        front = 0;
        back = -1; // specify size - 1 instead?
    }

    public static void main(String[] args) { // comment explanations for everything once done
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(5);
        ad.addFirst(4);
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addFirst(111);
        ad.addLast(1);
        ad.addFirst(1);
        ad.addFirst(1);
        ad.addLast(77);
        System.out.println(ad.toList());
    }

    private void resize(int desiredSize) {
        T[] sized = (T[]) new Object[desiredSize];
        System.arraycopy(items, 0, sized, 0, size);
        items = sized;
        front = 0;
        back = size - 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        /**
        if (front == 0) { // new
            front = items.length - 1;
        } else {
            front -= 1;
        }
        if (size == 0) { // new new new
            front = 0;
            back = 0;
            items[0] = x;
            size += 1;
            return;
        }
         */
        front = (front - 1 + items.length) % items.length; // add conditional for if it's empty?
        items[front] = x;
        size += 1;
        if (front == -1) {
            front = back;// this is new new 7:39 PM 2/14 NEW
        }
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        /**
        if (back == items.length - 1) {
            back = 0;
        } else {
            back += 1;
        }
         */
        back = (back + 1) % items.length;
        items[back] = x;
        size += 1;
        if (front == 0) {
            front = back;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        /**
        for (int i = front; i < items.length; i++) { // items.length OR size?
            returnList.add(items[i % items.length]);
        }
         */
        for (int i = 0; i < size; i++) {
            int index = (front + i) % items.length;
            if (items[index] != null) {
                returnList.add(items[index]);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty() == true) {
            return null;
        }
        T removed = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size -= 1;
        if (size < 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty() == true) {
            return null;
        }
        T removed = items[back];
        items[back] = null;
        back = (back - 1 + items.length) % items.length;
        size -= 1;
        if (size < 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return removed;
    }

    @Override
    public T get(int index) {
        return items[(front + index) % items.length];
    }
}
