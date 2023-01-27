package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

import static aoa.utils.FileUtils.readWordsOfLength;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        List<String> words = readWordsOfLength(dictionaryFile, wordLength);
        if (wordLength < 1) { //wordLength has to be a natural number
            throw new IllegalArgumentException("wordLength cannot be less than 1");
        }
        if (readWordsOfLength(dictionaryFile, wordLength).size() == 0) { //no words in dictionary that match wordLength
            throw new IllegalStateException("no words found of wordLength");
        }
        int numWords = words.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        String word = words.get(randomlyChosenWordNumber);
        chosenWord = word;
    }

    @Override
    public int makeGuess(char letter) { //returns the # occurrences of input letter in the secret word ('letter' is valid, lowercase, and novel)
        // TODO: Fill in this method.
        return 0;
    }

    @Override
    public String getPattern() { //return current game state pattern --> match correctly guessed letters to secret word letters, and unguessed letters to '-'; note: no leading or trailing spaces
        // TODO: Fill in this method.
        return "";
    }

    @Override
    public String getWord() { //This method should return the secret word being considered by the RandomChooser [14-26]
        return "";
    }
}
