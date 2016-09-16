package lakkur.echo.algorithm;

import java.util.function.Function;

/**
 * @author Prasanna Lakkur Subramanyam
 * Represents a function y = f(x) where the function is of the form y = (m * x) + c. x is the number of friends.
 * y is the frequency where, y = f(x)
 */
public class LinearFrequencyFunction implements Function<Integer, Float> {

    private float minFreq, maxFreq;
    private int maxPossibleFriends;
    private float slope;

    /**
     *
     * @param minFreq the minimum frequency that can be mapped to
     * @param maxFreq the maximum frequency that can be mapped to
     * @param maxPossibleFriends the maximum number of "friends" or edges that any vertex in the graph can have. This is
     *                           required to ensure the linear behavior of the algorithm
     */
    public LinearFrequencyFunction(float minFreq, float maxFreq, int maxPossibleFriends) {
        this.minFreq = minFreq;
        this.maxFreq = maxFreq;
        this.maxPossibleFriends = maxPossibleFriends;

        // Frequency is a function of number of friends/edges a vertex is connected to.
        // Hence, we get an equation similar to y = (m * x) + c where y is the frequency and x is the number of friends
        // a vertex is connected to. m is the slope. Here, we calculate the slope
        slope = (maxFreq - minFreq)/ maxPossibleFriends;
    }

    /**
     * Represents a function y = f(x) where the function is of the form y = (m * x) + c
     * @param numFriends The number of edges the given vertex is connected to
     * @return The frequency which corresponds to the numFriends value which is passed in
     */
    @Override
    public Float apply(Integer numFriends) {
        if(numFriends <= 0)
            return minFreq;
        if(numFriends > maxPossibleFriends)
            return maxFreq;

        return (slope * numFriends) + minFreq;
    }
}
