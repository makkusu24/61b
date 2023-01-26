package aoa.guessers;

import aoa.utils.FileUtils;

import java.sql.Array;
import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
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

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Map<Character, Integer> freq = getFrequencyMap();
        /** List<Character> notGuesses = new ArrayList<Character>(); **/
        Character prevMax = '|';
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
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
