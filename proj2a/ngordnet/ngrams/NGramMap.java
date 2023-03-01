package ngordnet.ngrams;

import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private HashMap<String, TimeSeries> wordRepo;
    private TimeSeries countRepo;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        this.wordRepo = new HashMap<>();
        this.countRepo = new TimeSeries();
        StringTokenizer wordsTokenizer = new StringTokenizer(wordsFilename, "\n");
        StringTokenizer countsTokenizer = new StringTokenizer(countsFilename, "\n");
        while (wordsTokenizer.hasMoreTokens() && !wordsTokenizer.nextToken().isEmpty()) { // parse words file
            String token = wordsTokenizer.nextToken();
            String[] subToken = token.split("[\t ]+");
            if (wordRepo.containsKey(subToken[0])) {
                wordRepo.get(subToken[0]).put(Integer.parseInt(subToken[1]), Double.parseDouble(subToken[2]));
            } else {
                TimeSeries tempEntry = new TimeSeries();
                wordRepo.put(subToken[0], tempEntry);
            }
        }
        while (countsTokenizer.hasMoreTokens() && !wordsTokenizer.nextToken().isEmpty()) { // parse counts file
            String token = countsTokenizer.nextToken();
            String[] subToken = token.split(",");
            countRepo.put(Integer.parseInt(subToken[0]), Double.parseDouble(subToken[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries defaultTS = wordRepo.get(word);
        TimeSeries returnTS = new TimeSeries(defaultTS, startYear, endYear);
        return returnTS;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        return wordRepo.get(word);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return countRepo;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }

}
