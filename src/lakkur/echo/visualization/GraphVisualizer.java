package lakkur.echo.visualization;


/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This interface is to be implemented by any class which can visualize graphs.
 *
 * @param <V> the type of the vertex of the graph
 * @param <E> the type of the edge of the graph
 */
public interface GraphVisualizer<V, E> {
    /**
     * Calling this method stats the visualization of the graph
     */
    void visualizeGraph();

    /**
     * Highlights the passed in vertex in the graph
     * @param vertex the vertex to be highlighted
     */
    void highlightVertex(V vertex);

    /**
     * Resets the graph to how it was at the start of the visualization
     */
    void reset();
}
