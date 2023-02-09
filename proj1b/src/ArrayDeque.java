import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    public ArrayDeque() {

    }

    /**
     * @source https://docs.google.com/presentation/d/1kjbO8X7-i63NwQ_9wIt4HXr6APp2qc9PkghD-GO7_is/edit#slide=id.g1094ff4355_0_466
     * @param args
     */
    public static void main(String[] args) { //starting size 8
        Deque<Integer> ad = new ArrayDeque<>();
        System.out.println(ad);
    }

    @Override
    public void addFirst(T x) { //constant time implementation

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
