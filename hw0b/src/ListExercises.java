import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        int sum_placeholder = 0;
        for (int i : L) {
            sum_placeholder = sum_placeholder + i;
        }
        return sum_placeholder;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> output = new ArrayList<Integer>();
        for (Integer i : L) {
            if (i % 2 == 0) {
                output.add(i);
            }
        }
        return output;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> common = new ArrayList<Integer>();
        if (L1.size() < L2.size()) {
            for (Integer i : L1) {
                if (L2.contains(i)) {
                    common.add(i);
                }
            }
        } else {
            for (Integer j : L2) {
                if (L1.contains(j)) {
                    common.add(j);
                }
            }
        }
        return common;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        Integer count = 0;
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (ch == c) {
                    count ++;
                }
            }
        }
        return count;
    }
}