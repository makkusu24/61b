import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    @DisplayName("addFirst() implementation, edge cases, time efficiency")

    public void addFirstTests() { // implement trigger resize tests (resize up and down cases)

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<Integer> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad2.addFirst(1); // [] -> [1]

        ad3.addFirst(3);
        ad3.addFirst(2);
        ad3.addFirst(1); // [] -> [1, 2, 3]

        ad4.addFirst("end");
        ad4.addFirst("step 2");
        ad4.addFirst("step 1");
        ad4.addFirst("start"); // [] -> ["start", "step 1", "step 2", "end"]

        assertWithMessage("should return [], or empty ArrayDeque")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("should return [1]")
                .that(ad2.toList()).containsExactly(1).inOrder();
        assertWithMessage("should return [1, 2, 3]")
                .that(ad3.toList()).containsExactly(1, 2, 3).inOrder();
        assertWithMessage("should return [start, step 1, step 2, end]")
                .that(ad4.toList()).containsExactly("start", "step 1", "step 2", "end").inOrder();

    }

    @Test
    @DisplayName("addLast() implementation, edge cases, time efficiency")

    public void addLastTests() { // implement trigger resize tests (resize up and down cases)

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<Integer> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad2.addLast(1); // [0] -> [1]

        ad3.addLast(10);
        ad3.addLast(11);
        ad3.addLast(12); // [] -> [10, 11, 12]

        ad4.addLast("goodbye");
        ad4.addLast("to");
        ad4.addLast("a");
        ad4.addLast("world"); // [] -> ["goodbye", "to", "a", "world"]

        assertWithMessage("should return [], or empty ArrayDeque")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("should return [1]")
                .that(ad2.toList()).containsExactly(1).inOrder();
        assertWithMessage("should return [10, 11, 12]")
                .that(ad3.toList()).containsExactly(10, 11, 12).inOrder();
        assertWithMessage("should return [Goodbye, to, a, world]")
                .that(ad4.toList()).containsExactly("goodbye", "to", "a", "world").inOrder();

    }

    @Test
    @DisplayName("both addFirst() and addLast() in conjunction")

    public void addFirstLastTests() {

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<String> ad2 = new ArrayDeque<>();

        ad1.addFirst(150); // [] -> [150]
        ad1.addLast(150); // [150] -> [150, 150]
        ad1.addFirst(44); // [150, 150] -> [44, 150, 150]
        ad1.addLast(44); // [44, 150, 150] -> [44, 150, 150, 44]
        ad1.addFirst(12); // [44, 150, 150, 44] -> [12, 44, 150, 150, 44]
        ad1.addLast(12); // [12, 44, 150, 150, 44] -> [12, 44, 150, 150, 44, 12]

        ad2.addLast("seven lions"); // [] -> ["seven lions"]
        ad2.addLast("porter robinson"); // ["seven lions"] -> ["seven lions", "porter robinson"]
        ad2.addFirst("illenium"); // ["seven lions", "porter robinson"] -> ["illenium", "seven lions", "porter robinson"]
        ad2.addLast("rezz"); // ["illenium", "seven lions", "porter robinson"] -> ["illenium", "seven lions", "porter robinson", "rezz"]
        ad2.addFirst("tiesto"); // ["illenium", "seven lions", "porter robinson", "rezz"] -> ["tiesto", "illenium", "seven lions", "porter robinson", "rezz"]
        ad2.addFirst("lsdream"); // ["tiesto", "illenium", "seven lions", "porter robinson", "rezz"] -> ["lsdream", "tiesto", "illenium", "seven lions", "porter robinson", "rezz"]
        ad2.addFirst("armin van buuren"); // ["lsdream", "tiesto", "illenium", "seven lions", "porter robinson", "rezz"] -> ["armin van buuren", "lsdream", "tiesto", "illenium", "seven lions", "porter robinson", "rezz"]

        assertWithMessage("should return [12, 44, 150, 150, 44, 12]")
                .that(ad1.toList()).containsExactly(12, 44, 150, 150, 44, 12).inOrder();
        assertWithMessage("should return")
                .that(ad2.toList()).containsExactly("armin van buuren", "lsdream", "tiesto", "illenium", "seven lions", "porter robinson", "rezz").inOrder();

    }

    @Test
    @DisplayName("toList() non-destructive implementation")

    public void toListTests() {

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<Integer> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad2.addFirst(1); // [] -> [1]

        ad3.addFirst(3);
        ad3.addFirst(2);
        ad3.addFirst(1); // [] -> [1, 2, 3]

        ad4.addLast("goodbye");
        ad4.addLast("to");
        ad4.addLast("a");
        ad4.addLast("world"); // [] -> ["Goodbye", "to", "a", "world"]

        assertWithMessage("should return empty ArrayList object")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("should return [1]")
                .that(ad2.toList()).containsExactly(1).inOrder();
        assertWithMessage("should return [1, 2, 3]")
                .that(ad3.toList()).containsExactly(1, 2, 3).inOrder();
        assertWithMessage("should return [Goodbye, to, a, World]")
                .that(ad4.toList()).containsExactly("goodbye", "to", "a", "world").inOrder();

    }

    @Test
    @DisplayName("isEmpty() non-destructive implementation")

    public void isEmptyTests() {

        Deque<String> ad1 = new ArrayDeque<>(); // [] -> true
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<String> ad3 = new ArrayDeque<>();

        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3); // [10, 11, 12] -> false

        ad3.addLast("goodbye");
        ad3.addLast("to");
        ad3.addLast("a");
        ad3.addLast("world"); // ["goodbye", "to", "a", "world"] -> false

        assertWithMessage("empty AD should be true")
                .that(ad1.isEmpty()).isEqualTo(true);
        assertWithMessage("nonempty AD should be false")
                .that(ad2.isEmpty()).isEqualTo(false);
        assertWithMessage("nonempty AD should be false")
                .that(ad3.isEmpty()).isEqualTo(false);

    }

    @Test
    @DisplayName("size() non-destructive caching with each operation")

    public void sizeTests() {

        Deque<Integer> ad1 = new ArrayDeque<>(); // [] -> 0
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<String> ad3 = new ArrayDeque<>();
        Deque<Integer> ad4 = new ArrayDeque<>();
        Deque<String> ad5 = new ArrayDeque<>();
        Deque<String> ad6 = new ArrayDeque<>();
        Deque<Integer> ad7 = new ArrayDeque<>();

        ad2.addFirst(1); // [1] -> 1

        ad3.addFirst("come");
        ad3.addLast("around");
        ad3.addLast("again"); // ["come", "around", "again"] -> 3

        for (int i = 0; i <= 100; i++) {
            ad4.addFirst(i);
        } // [1, ..., 100] -> 100

        ad5.addLast("removed");
        ad5.removeLast(); // [] -> 0

        ad6.addFirst("removed");
        ad6.removeFirst();
        ad6.addFirst("returned"); // ["returned"] -> 1

        ad7.removeLast(); // [] -> []

        assertWithMessage("empty AD should be size 0")
                .that(ad1.size()).isEqualTo(0);
        assertWithMessage("failed small AD size")
                .that(ad2.size()).isEqualTo(1);
        assertWithMessage("failed medium AD size")
                .that(ad3.size()).isEqualTo(3);
        assertWithMessage("failed large AD size")
                .that(ad4.size()).isEqualTo(4);
        assertWithMessage("failed removed to empty size")
                .that(ad5.size()).isEqualTo(0);
        assertWithMessage("failed removed to empty then added size")
                .that(ad6.size()).isEqualTo(1);
        assertWithMessage("failed remove from already empty AD").that(ad7.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("removeFirst() implementation, edge cases, time efficiency")

    public void removeFirstTests() { // trigger resize

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<String> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad1.addFirst(0);
        ad1.removeFirst(); // [0] -> []

        ad2.addLast(0);
        ad2.removeFirst(); // [0] -> []

        ad3.addFirst("come");
        ad3.addLast("around");
        ad3.addLast("again");
        ad3.removeFirst();
        ad3.removeFirst(); // ["come", "around", "again"] -> ["again"]

        ad4.addFirst("removed");
        ad4.removeFirst();
        ad4.addFirst("returned"); // ["removed"] -> [] -> ["returned"]

        assertWithMessage("failed remove to empty")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("failed remove to empty")
                .that(ad2.toList()).containsExactly().inOrder();
        assertWithMessage("failed remove to 1")
                .that(ad3.toList()).containsExactly("again").inOrder();
        assertWithMessage("failed remove to empty then add again")
                .that(ad4.toList()).containsExactly("returned").inOrder();

    }

    @Test
    @DisplayName("removeLast() implementation, edge cases, time efficiency")

    public void removeLastTests() { // trigger resize

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<String> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad1.addFirst(0);
        ad1.removeLast(); // [0] -> []

        ad2.addLast(0);
        ad2.removeLast(); // [0] -> []

        ad3.addFirst("come");
        ad3.addLast("around");
        ad3.addLast("again");
        ad3.removeLast();
        ad3.removeLast(); // ["come", "around", "again"] -> ["come"]

        ad4.addLast("removed");
        ad4.removeLast();
        ad4.addLast("returned"); // ["removed"] -> [] -> ["returned"]

        assertWithMessage("failed remove to empty")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("failed remove to empty")
                .that(ad2.toList()).containsExactly().inOrder();
        assertWithMessage("failed remove to 1")
                .that(ad3.toList()).containsExactly("come").inOrder();
        assertWithMessage("failed remove to empty then add again")
                .that(ad4.toList()).containsExactly("returned").inOrder();

    }

    @Test
    @DisplayName("get() non-destructive retrieval of item in AD")

    public void getTests() {

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<String> ad3 = new ArrayDeque<>();

        ad2.addLast(1);


        ad3.addLast("goodbye");
        ad3.addLast("to");
        ad3.addLast("a");
        ad3.addLast("world");

        assertWithMessage("can't get() from empty AD")
                .that(ad1.get(0)).isEqualTo(null);
        assertWithMessage("can't get() from empty AD")
                .that(ad1.get(1)).isEqualTo(null);
        assertWithMessage("negative index should return null")
                .that(ad2.get(-2)).isEqualTo(null);
        assertWithMessage("out of bounds should return null")
                .that(ad2.get(5)).isEqualTo(null);
        assertWithMessage("get(0) should return first item")
                .that(ad3.get(0)).isEqualTo("goodbye");
        assertWithMessage("get(1) should return the second item")
                .that(ad3.get(1)).isEqualTo("to");
        assertWithMessage("get(4) should return the last item")
                .that(ad3.get(3)).isEqualTo("world");

    }

}
