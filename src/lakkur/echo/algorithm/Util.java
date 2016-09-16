package lakkur.echo.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Prasanna Lakkur Subramanyam
 * Utility class for commonly used functions
 */
class Util {

    /**
     * finds and returns the intersection between the Collections passed in as parameters. NOTE : V must override the
     * equals method for this to work properly.
     * @param collectionA
     * @param collectionB
     * @return a set representing the intersection between the two parameters
     */
    static Collection intersection(Collection collectionA, Collection collectionB){
        Set inter = new HashSet(collectionA);
        inter.retainAll(collectionB);
        return inter;
    }

    /**
     * finds and returns the union between the Collections passed in as parameters. NOTE : V must override the
     * equals method for this to work properly.
     * @param collectionA
     * @param collectionB
     * @return a set representing the union between the two parameters
     */
    static Collection union(Collection collectionA, Collection collectionB){
        Set inter = new HashSet(collectionA);
        inter.addAll(collectionB);
        return inter;
    }


}
