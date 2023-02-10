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

    public void adLastTests() { // implement trigger resize tests (resize up and down cases)

        Deque<Integer> ad1 = new ArrayDeque<>();
        Deque<Integer> ad2 = new ArrayDeque<>();
        Deque<Integer> ad3 = new ArrayDeque<>();
        Deque<String> ad4 = new ArrayDeque<>();

        ad2.addLast(1); // [0] -> [1]

        ad3.addLast(10);
        ad3.addLast(11);
        ad3.addLast(12); // [] -> [10, 11, 12]

        ad4.addLast("Goodbye");
        ad4.addLast("to");
        ad4.addLast("a");
        ad4.addLast("world"); // [] -> ["Goodbye", "to", "a", "world"]

        assertWithMessage("should return [], or empty ArrayDeque")
                .that(ad1.toList()).containsExactly().inOrder();
        assertWithMessage("should return [1]")
                .that(ad2.toList()).containsExactly(1).inOrder();
        assertWithMessage("should return [10, 11, 12]")
                .that(ad3.toList()).containsExactly(10, 11, 12).inOrder();
        assertWithMessage("should return [Goodbye, to, a, world]")
                .that(ad4.toList()).containsExactly("Goodbye", "to", "a", "world").inOrder();

    }

    @Test
    @DisplayName("implementation of both addFirst() and addLast() in conjunction")

    public void addFirstLastTests() { // implement trigger resize tests (resize up and down cases)

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

        ad4.addLast("Goodbye");
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
                .that(ad4.toList()).containsExactly("Goodbye", "to", "a", "world").inOrder();

    }
}
