import edu.uci.ics.jung.graph.Graph;
import lakkur.echo.model.Edge;
import lakkur.echo.model.FileGraphProvider;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Here, we test the loading of "small_test_graph.txt" through FileGraphProvider. This is to confirm that FileGraphProvider
 * does in fact load the graph and loads the vertices and edges properly.
 */
public class ModelLoadingTester {
    private Graph<Integer, Edge<Integer>> graph;


    @Test
    public void testGraph(){
        graph = new FileGraphProvider("/data/small_test_graph.txt").provideGraph();

        assertNotEquals("Graph should not be null", graph, null);

        //refer data for the source of the numbers
        assertEquals("Number of vertices should be 14", graph.getVertices().size(), 14);

        assertEquals("Number of edges should be 17", graph.getEdges().size(), 17);
    }

    @Test
    public void testImproperLoad(){
        try {
            graph = new FileGraphProvider("wrongPathBlah.txt").provideGraph();
            fail("Wrong file paths should result in an exception");
        }catch (Exception e){

        }
    }

}
