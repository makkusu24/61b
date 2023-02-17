package deque;

import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class LinkedListDequeTest {

    @Test
    @DisplayName("iterator() implementation and edge cases")

    public void iteratorTests() {
        Deque<String> lld0 = new ArrayDeque<>();
        String placeholderCheck = "";

        lld0.addLast("good");
        lld0.addLast("things");
        lld0.addLast("fall");
        lld0.addLast("apart");

        for (String i : lld0) {
            placeholderCheck += i;
            placeholderCheck += " "; // whitespace
        }

        assertWithMessage("iterable failed").that(placeholderCheck).containsMatch("good things fall apart ");

    }

    @Test
    @DisplayName("equals() implementation and edge cases")

    public void equalsTests() {

    }

    @Test
    @DisplayName("toString() implementation and edge cases")

    public void toStringTests() {

    }

}
