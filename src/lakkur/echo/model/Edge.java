package lakkur.echo.model;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This class represents an Edge in a Graph. It keeps track of the source and destination vertex of the edge.
 * This may be modified/extended if more data about the edge needs to be stored.
 *
 * @param <V> The type of the vertex which the edge connects
 */
public class Edge<V> {
    /**
     * The source(start) vertex of the Edge.
     */
    private V source;
    /**
     * Destination(end) vertex of the Edge.
     */
    private V destination;

    /**
     *
     * @param source The source(start) vertex of the Edge.
     * @param destination Destination(end) vertex of the Edge.
     */
    public Edge(V source, V destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     *
     * @return The source(start) vertex of the Edge.
     */
    public V getSource() {
        return source;
    }

    /**
     *
     * @param source The source(start) vertex of the Edge.
     */
    public void setSource(V source) {
        this.source = source;
    }

    /**
     *
     * @return Destination(end) vertex of the Edge.
     */
    public V getDestination() {
        return destination;
    }

    /**
     *
     * @param destination Destination(end) vertex of the Edge.
     */
    public void setDestination(V destination) {
        this.destination = destination;
    }


}
