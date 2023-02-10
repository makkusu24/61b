import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    public ArrayDeque() {

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

    @Override
    public void addFirst(T x) { //constant time implementation

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
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
    public T removeFirst() { // NULL out deleted items to prevent memory loitering (set return item -> delete original pointer)
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
