package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    //@Override
    public Map<Character, Integer> getPatternFrequencyMap(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = new TreeMap<>(); //the frequency map we want to return
        List<String> patternWords = new ArrayList<>(); //the list of words that match the length of the pattern
        List<String> patternWords2 = new ArrayList<>(); //the words from patternWords that match the content of the pattern string
        char[] patternArray = pattern.toCharArray(); //allows us to iterate over the pattern character by character (compatible types)
        /** the below 2 loops parses the viable words from the rest of the words list**/
        for (String element : words) { //ensures pattern length is the same as each word in the filtered list
            if (element.length() == pattern.length()) {
                patternWords.add(element);
            }
        }
        for (String element2 : patternWords) { //each same-length word checked
            Integer i = 0;
            for (Character element3 : element2.toCharArray()) { //each character in the same-length word checked against INDEXED PATTERN
                if (patternArray[i] == '-' && i == patternArray.length-1) { //end of pattern; last character is blank and a green light for a match
                    patternWords2.add(element2);
                }
                else if (patternArray[i] == '-') { //blank; continue with the loop
                    i++;
                }
                else if (i == patternArray.length-1 && patternArray[i] == element3){ //end of pattern; last character is the same and a green light for a match
                    patternWords2.add(element2);
                }
                else if (patternArray[i] != element3) { //pattern is definitely not a match
                    break;
                }
                else { //continue with the loop
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
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = getPatternFrequencyMap(pattern, guesses);
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
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
