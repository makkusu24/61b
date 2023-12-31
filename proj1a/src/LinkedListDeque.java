import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    /**
     * @source lec 5 github code uploaded by Hug
     * NOTE: maybe has to be static? initial implementation doesn't work if static
     * Nested Node class within LLD (not to be confused with LLD constructor)
     */
    private class Node { //each Node has its generic item, a next pointer, and a previous pointer
        private T item;
        private Node next;
        private Node prev;

        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * @source [insert links for whatever inspiration you use for constructor
     * LinkedListDeque() must take 0 args; keep as is
     */
    public LinkedListDeque() { // LLD constructor to add sentinel; points at itself topology
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(5);
        lld.addFirst(4);
        lld.addFirst(3);
        lld.addFirst(2);
        lld.addFirst(1);
        lld.removeFirst();
        /**
        lld.addLast(5);
        lld.addLast(4);
        lld.addLast(3);
        lld.addFirst(2);
        lld.addLast(1);
        lld.removeLast();
        lld.removeLast();
         */
        /**
        lld.addFirst("back");
        lld.addFirst("middle");
        lld.addFirst("front");
         */
        System.out.println(lld.toList());
    }
    
    @Override
    public void addFirst(T x) {
        Node first = new Node(x, sentinel.next, sentinel);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node last = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node temp = sentinel.next;
        while (temp != sentinel) {
            returnList.add(temp.item);
            temp = temp.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
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
        if (this.isEmpty()) {
            return null;
        } else {
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return temp;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T temp = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return temp;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node temp = sentinel.next;
        int count = 0;
        while (count != index) {
            count += 1;
            temp = temp.next;
        }
        return temp.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return getRecursiveHelper(index, sentinel.next);
        }
    }

    private T getRecursiveHelper(int index, Node s) {
        if (index == 0) {
            return s.item;
        }
        return getRecursiveHelper(index - 1, s.next);
    }

}
