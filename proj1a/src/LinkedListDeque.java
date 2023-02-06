import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    /**
     * IMPLEMENT NODE CLASS WITHIN LinkedListDeque (5%)
     * @param args
     */
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
    }

    /**
     * @source [insert links for whatever inspiration you use for constructor
     * LinkedListDeque() must take 0 args; keep as iss
     */
    public LinkedListDeque() { // 5% Proj 1A weight for implementing this constructor ALONGSIDE valid node class
    T node = null;

    }

    @Override
    public void addFirst(T x) {
    //T.next = new IntNode(x, T.next);
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

    @Override
    public T getRecursive(int index) {
        return null;
    }

}
