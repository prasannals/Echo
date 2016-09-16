package lakkur.echo.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This class is used to generate a small scale graph with integer vertices and string edges. Can use useful when
 * testing/developing new algorithms.
 */
public class SimpleGraphProvider implements GraphProvider<Integer, String> {
    private Graph<Integer, String> graph;

    @Override
    public Graph<Integer, String> provideGraph() {
        graph = new SparseMultigraph<>();
        populateGraphWithElements();

        return graph;
    }

    private void populateGraphWithElements(){
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);

        graph.addEdge("E1", 1, 2);
        graph.addEdge("E2", 3, 4);
        graph.addEdge("E3", 4, 5);
        graph.addEdge("E4", 6, 7);
        graph.addEdge("E5", 2, 4);
        graph.addEdge("E6", 8, 2);
        graph.addEdge("E7", 9, 1);
        graph.addEdge("E8", 5, 6);
        graph.addEdge("E9", 3, 8);
        graph.addEdge("E10", 3, 1);
        graph.addEdge("E11", 3, 2);
        graph.addEdge("E12", 3, 4);
        graph.addEdge("E13", 3, 5);
        graph.addEdge("E14", 3, 6);

    }
}
