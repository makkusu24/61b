import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     //Below, you'll write your own tests for LinkedListDeque.
    @Test
    @DisplayName("An empty LLD should return true and any other sort of structure should be false.")
    public void isEmptyTests() {
    Deque<Integer> lld1 = new LinkedListDeque<>();
    Deque <String> lld2 = new LinkedListDeque<>();
    Deque<Integer> lld3 = new LinkedListDeque<>();

    lld1.addFirst(5);
    lld1.addFirst(4);
    lld1.addFirst(3);
    lld1.addFirst(2);
    lld1.addFirst(1);

    lld2.addLast("EDM");
    lld2.addLast("will");
    lld2.addLast("save");
    lld2.addLast("the");
    lld2.addLast("world");

    assertWithMessage("integer LLD registers as empty").that(lld1.isEmpty()).isEqualTo(false);
    assertWithMessage("string LLD registers as empty").that(lld2.isEmpty()).isEqualTo(false);
    assertWithMessage("empty string does not return true").that(lld3.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("A properly implemented LLD should cache the size with each operation.")
    public void sizeTests() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
        Deque<Integer> lld2 = new LinkedListDeque<>();
        Deque <String> lld3 = new LinkedListDeque<>();
        Deque<Integer> lld4 = new LinkedListDeque<>();

        lld2.addFirst(1);

        lld3.addLast("EDM");
        lld3.addLast("will");
        lld3.addLast("save");
        lld3.addLast("the");
        lld3.addLast("world");

        for (int i = 1; i <= 100; i++) {
            lld4.addLast(i);
        }

        assertWithMessage("empty LLD size isn't 0").that(lld1.size()).isEqualTo(0);
        assertWithMessage("wrong size for small length").that(lld2.size()).isEqualTo(1);
        assertWithMessage("wrong size for medium length").that(lld3.size()).isEqualTo(5);
        assertWithMessage("wrong size for long length").that(lld4.size()).isEqualTo(100);
    }

    @Test
    @DisplayName("Tests 0-indexing iterative get function, returning null for negative and OOB inputs.")
    public void getTests() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
        Deque<Integer> lld2 = new LinkedListDeque<>();
        Deque <String> lld3 = new LinkedListDeque<>();

        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(3);

        lld3.addLast("EDM");
        lld3.addLast("will");
        lld3.addLast("save");
        lld3.addLast("the");
        lld3.addLast("world");

        assertWithMessage("can't get from empty LLD").that(lld1.get(0)).isEqualTo(null);
        assertWithMessage("can't get from empty LLD").that(lld1.get(1)).isEqualTo(null);
        assertWithMessage("negative index should return null").that(lld2.get(-2)).isEqualTo(null);
        assertWithMessage("out of bounds should return null").that(lld2.get(5)).isEqualTo(null);
        assertWithMessage("get(0) should return first item").that(lld3.get(0)).isEqualTo("EDM");
        assertWithMessage("get(1) should return the second item").that(lld3.get(1)).isEqualTo("will");
        assertWithMessage("get(4) should return the last item").that(lld3.get(4)).isEqualTo("world");
    }

}