package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    /** @Override **/
    public Map<Character, Integer> getPatternFrequencyMap(String pattern) {
        Map<Character, Integer> freq = new TreeMap<>();
        List<String> patternWords = new ArrayList<>();
        List<String> patternWords2 = new ArrayList<>();
        char[] patternArray = pattern.toCharArray();
        /** the below 2 loops parses the viable words from the rest of the words list**/
        for (String element : words) {
            if (element.length() == pattern.length()) {
                patternWords.add(element);
            }
        }
        for (String element2 : patternWords) {
            Integer i = 0;
            for (Character element3 : element2.toCharArray()) {
                if (patternArray[i] == '-' && i == patternArray.length-1) {
                    patternWords2.add(element2);
                }
                else if (patternArray[i] == '-') {
                    i++;
                }
                else if (i == patternArray.length-1 && patternArray[i] == element3){
                    patternWords2.add(element2);
                }
                else if (patternArray[i] != element3) {
                    break;
                }
                else {
                    i++;
                }
            }
        }
        /** loop below finds the most frequent letter from the list of words remaining that match the pattern**/
        for (String word : patternWords2) {
            for (Character ch : word.toCharArray()) {
                if (freq.containsKey(ch)) {
                    freq.put(ch, freq.get(ch)+1);
                }
                else {
                    freq.put(ch, 1);
                }
            }
        }
        freq.put('z', 0);
        return freq;
    }
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = getPatternFrequencyMap(pattern);
        /** List<Character> notGuesses = new ArrayList<Character>(); **/
        Character prevMax = 'z';
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