package aoa.guessers;

import aoa.utils.FileUtils;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    /** @Override **/
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character, Integer> freq = new TreeMap<>();
        for (String word : words) {
            for (Character ch : word.toCharArray()) {
                if (freq.containsKey(ch)) {
                    freq.put(ch, freq.get(ch)+1);
                }
                else {
                    freq.put(ch, 1);
                }
            }
        }
        return freq;
    }
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = getFrequencyMap();
        Character prevMax = 'h';
        for (Character key : freq.keySet()) {
            if (freq.size() == 0) {
                return '?';
            }
            else if (freq.get(key) > freq.get(prevMax) && guesses.contains(key) == false) && (pattern.contains(prevMax)){
                prevMax = key;
            }
        }
        return prevMax;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}