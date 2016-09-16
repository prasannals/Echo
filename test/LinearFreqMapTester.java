import edu.uci.ics.jung.graph.Graph;
import lakkur.echo.algorithm.LinearFrequencyFunction;
import lakkur.echo.model.Edge;
import lakkur.echo.model.FileGraphProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Tests the algorithms used in this project.
 */
public class LinearFreqMapTester {
    private Graph<Integer, Edge<Integer>> graph;

    @Before
    public void setUp(){
        graph = new FileGraphProvider("/data/small_test_graph.txt").provideGraph();
    }

    @Test
    public void testFrequencyFunction(){
        Function<Integer, Float> linearFreqFunction = new LinearFrequencyFunction(300, 15000, 5000);

        assertEquals((float)linearFreqFunction.apply(0), (float)300);
        assertEquals((float) linearFreqFunction.apply(5000),(float)15000 );
    }

}
