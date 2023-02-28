package ngordnet.ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        this.putAll(ts.subMap(startYear, true,  endYear, true));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<>(this.keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        return new ArrayList<>(this.values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries summedMap = new TimeSeries();
        Integer localMin = this.firstKey();
        Integer localMax = this.lastKey();
        if (this.keySet().size() == 0 && ts.keySet().size() == 0) {
            return summedMap;
        }
        if (this.firstKey() > ts.firstKey()) {
            localMin = ts.firstKey();
        }
        if (this.lastKey() < ts.lastKey()) {
            localMax = ts.lastKey();
        }
        for (int i = localMin; i <= localMax; i++) {
            if (this.containsKey(i) && !ts.containsKey(i)) {
                summedMap.put(i, this.get(i));
            } else if (!this.containsKey(i) && ts.containsKey(i)) {
                summedMap.put(i, ts.get(i));
            } else if (this.containsKey(i) && ts.containsKey(i)) {
                summedMap.put(i, this.get(i) + ts.get(i));
            }
        }
        return summedMap;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries summedMap = new TimeSeries();
        for (Integer key : this.keySet()) {
            if (!ts.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            summedMap.put(key, this.get(key) / ts.get(key));
        }
        return summedMap;
    }

}
