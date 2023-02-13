import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int front;
    private int back;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = -1; // specify size - 1 instead?
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(1);
        ad.addFirst(0);
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
        front = (front - 1 + items.length) % items.length; // add conditional for if it's empty?
        items[front] = x;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        back = (back + 1) % items.length;
        items[back] = x;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) { // shouldn't start at 0?
            returnList.add(items[i]);
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
        return items[index];
    }
}
