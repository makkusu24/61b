import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    /**
     * @source lec 5 github code uploaded by Hug
     * NOTE: maybe has to be static? initial implementation doesn't work if static
     * Nested Node class within LLD (not to be confused with LLD constructor)
     */
    private class Node { //each Node has its generic item, a next pointer, and a previous pointer
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n, Node p) { // Node constructor within the nested Node class; may not need args? [COME BACK TO]
            item = i;
            next = n; //shouldn't this default to null? [COME BACK TO]
            prev = p;
        }
    }

    private Node sentinel;
    private Node first;
    private Node next;
    private int size;

    /**
     * @source [insert links for whatever inspiration you use for constructor
     * LinkedListDeque() must take 0 args; keep as is
     */
    public LinkedListDeque() { // LLD constructor to add sentinel; points at itself topology
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        //addFirst calls
        //addLast calls
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
