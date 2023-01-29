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
        this.chosenWord = word;
        this.pattern = "-".repeat(wordLength); //initializes pattern to be the same amount of dashes as wordLength
    }

    @Override
    public int makeGuess(char letter) { //returns the # occurrences of input letter in the secret word ('letter' is valid, lowercase, and novel)
        char[] patternPlaceholder = this.pattern.toCharArray();
        String placeholder = "";
        char[] wordArray = getWord().toCharArray();
        int counter = 0;
        for (Character i : wordArray) { //code that counts the occurrences of letter
            if (letter == i) {
                counter++;
            }
        }
        if (counter > 0) { //only runs the follow for loop and its nested loops if there is a valid guess (i.e., letter sets counter to a natural number)
            int counter2 = 0;
            for (Character letterCompare : patternPlaceholder) { //first we iterate over each letter or dash in the current pattern
                if (letterCompare != '-') { //if there's already a registered CORRECTLY GUESSED LETTER from prior attempts, then pattern's letter is left as is
                    placeholder += letterCompare; //this is just to track the already guessed letters in the placeholder
                    counter2++;
                }
                else { //if the current letterCompare is a dash, we check to see if the guess can replace the '-' or not
                    for (int l = counter2; l < wordArray.length; l++) { //second for loop checks each letter of the entire answer word to find position where guess can replace '-'
                        if (wordArray[l] == letter) { //letter is a valid guess
                            placeholder += letter; //replaces dash with the correct letter
                            counter2++;
                        }
                        else {
                            placeholder += '-'; //keeps '-' as a '-'
                            counter2++;
                        }
                    }
                }
                counter2++;
            }
            this.pattern = placeholder;
        }
        /**for (Character letterReal : wordArray) { //code that modifies pattern
            for ()
            if (letterReal == letter) {
                this.pattern = //modify pattern class variable to reveal the correctly guessed letter
            }
        }**/
        return counter;
    }

    @Override
    public String getPattern() { //return current game state pattern --> match correctly guessed letters to secret word letters, and unguessed letters to '-'; note: no leading or trailing spaces
        return this.pattern; //or this.pattern = _output_?d
    }

    @Override
    public String getWord() { //This method should return the secret word being considered by the RandomChooser
        return this.chosenWord;
    }
}
