package lakkur.echo.model;

import edu.uci.ics.jung.graph.Graph;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This interface is to be implemented by any class which provides the Graph(edu.uci.ics.jung.graph.Graph).
 *
 * @param <V> The type of the vertex of the graph.
 * @param <E> The type of the edge of the graph.
 */
public interface GraphProvider<V, E> {
    /**
     * Returns a Graph (edu.uci.ics.jung.graph.Graph) with the relevant edges and vertices configured.
     * @return a Graph (edu.uci.ics.jung.graph.Graph) with the relevant edges and vertices configured. null if something
     * went wrong while trying to populate/retrieve the graph
     */
    Graph<V, E> provideGraph();
}
