import deque.Deque;
import deque.ArrayDeque;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    @DisplayName("iterator() implementation and edge cases")

    public void iteratorTests() {
        Deque<String> ad0 = new ArrayDeque<>();
        String placeholderCheck = "";

        ad0.addLast("good");
        ad0.addLast("things");
        ad0.addLast("fall");
        ad0.addLast("apart");

        for (String i : ad0) {
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
