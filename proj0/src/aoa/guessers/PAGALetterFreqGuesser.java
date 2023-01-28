package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    //@Override
    public Map<Character, Integer> getPatternFrequencyMap(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = new TreeMap<>(); //the frequency map we want to return
        List<String> patternWords = new ArrayList<>(); //the list of words that match the length of the pattern
        List<String> toRemove = new ArrayList<>();
        List<String> patternWords2 = new ArrayList<>(); //the words from patternWords that match the content of the pattern string
        char[] patternArray = pattern.toCharArray(); //allows us to iterate over the pattern character by character (compatible types)
        /** the below 2 loops parses the viable words from the rest of the words list**/
        for (String element : words) { //ensures pattern length is the same as each word in the filtered list; also checks PAGA[2] where it removes words that are (1) in guesses AND (2) not in the pattern
            if (element.length() == pattern.length()) {
                patternWords.add(element);
            }
        }
        for (Character squish : guesses) { //REMOVE all words that are in guesses but not in the pattern || NEW FROM PA --> PAGA
            if (!pattern.contains(squish + "")) {  //if the letter is not in the pattern already
                for (String squishWord : patternWords) {
                    if (squishWord.contains(Character.toString(squish))) { //if the pattern word contains a guess character AND the guess character is not present in the pattern, remove it
                        toRemove.add(squishWord);
                    }
                }
            }
        }
        for (Character g : guesses) {   //all the guesses
            for (String word : patternWords) { //liable words
                for (int c = 0; c<word.length(); c++) {
                    if (word.charAt(c) == g && pattern.charAt(c) !=g) {
                        //remove
                        toRemove.add(word);
                    }
                }
            }
        }
        patternWords.removeAll(toRemove);
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
        freq.put('|', 0);
        return freq;
    }
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freq = getPatternFrequencyMap(pattern, guesses);
        /** List<Character> notGuesses = new ArrayList<Character>(); **/
        Character prevMax = '|'; // CHANGE CHANGE CHANGE
        for (Character key : freq.keySet()) { //iterates over every key in the getPatternFrequencyMap() TreeMap (all the character frequencies from the remaining filtered word bank
            if (freq.size() == 1) { //return '?' if there is no character array
                return '?';
            }
            else if (freq.get(key) > freq.get(prevMax) && guesses.contains(key) == false) { //if the currently checked letter is larger than the previous maximum and has not already been guessed, then it becomes the new maximum
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
