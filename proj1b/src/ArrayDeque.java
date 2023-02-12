import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;

    public ArrayDeque() {
        T[] items = (T[]) new Object[8];
        size = 0;
    }

    /**
     * @source https://docs.google.com/presentation/d/1kjbO8X7-i63NwQ_9wIt4HXr6APp2qc9PkghD-GO7_is/edit#slide=id.g1094ff4355_0_466
     * @param args
     * NOTE: multiplicative resizing up for time efficiency; resize down when <1/2 || <1/4 boxes used?
     */
    public static void main(String[] args) { //starting size 8
        Deque<Integer> ad = new ArrayDeque<>();
        System.out.println(ad);
    }

    private void resize(int desiredSize) {
        T[] sized = (T[]) new Object[desiredSize];
        System.arraycopy(items, 0, sized, 0, size);
        items = sized;
    }

    @Override
    public void addFirst(T x) { //constant time implementation
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (items.length == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() { // NULL out deleted items to prevent memory loitering (set return item -> delete original pointer)

        size -= 1;
        return null;
    }

    @Override
    public T removeLast() {
        T removed = items[size - 1];
        items[size - 1] = null;
        size -= 1;
        return removed;
    }

    @Override
    public T get(int index) {
        return items[index];
    }
}
