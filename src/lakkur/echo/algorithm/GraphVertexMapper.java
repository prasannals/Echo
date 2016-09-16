package lakkur.echo.algorithm;

import edu.uci.ics.jung.graph.Graph;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number.
 * @param <V> The type of the vertex
 * @param <E> The type of the edge
 */
public interface GraphVertexMapper<V, E> {
    /**
     * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number.
     * @param graph the graph(edu.uci.ics.jung.graph.Graph) in which the vertex exists
     * @param vertex the vertex in the graph which has to be mapped to a floating point number
     * @return the floating point number which the vertex is mapped to
     */
    float mapGraphVertex(Graph<V,E> graph, V vertex);
}
