package aoa.guessers;

import aoa.utils.FileUtils;
import java.util.List;
import java.util.Map;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = NaiveLetterFreqGuesser.getFrequencyMap();
        /** List<Character> notGuesses = new ArrayList<Character>(); **/
        Character prevMax = 'h';
        for (Character key : freq.keySet()) {
            if (freq.size() == 0) {
                return '?';
            }
            else if (freq.get(key) > freq.get(prevMax) && guesses.contains(key) == false) {
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