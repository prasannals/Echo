package lakkur.echo.algorithm;

import edu.uci.ics.jung.graph.Graph;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number which represents frequency.
 * The mapping is done in a linear fashion.
 * @param <V> The type of the vertex
 * @param <E> The type of the edge
 */
public class LinearFrequencyMapper<V,E> implements GraphVertexMapper<V,E>{
    private LinearFrequencyFunction linearFrequencyFunction;

    /**
     *
     * @param minFreq the minimum frequency that can be mapped to
     * @param maxFreq the maximum frequency that can be mapped to
     * @param maxPossibleFriends the maximum number of "friends" or edges that any vertex in the graph can have. This is
     *                           required to ensure the linear behavior of the algorithm
     */
    public LinearFrequencyMapper(float minFreq, float maxFreq, int maxPossibleFriends) {
        linearFrequencyFunction = new LinearFrequencyFunction(minFreq, maxFreq, maxPossibleFriends);
    }

    /**
     * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number which represents frequency.
     * It mapping is done in a linear fashion.
     * @param graph the graph(edu.uci.ics.jung.graph.Graph) in which the vertex exists
     * @param vertex the vertex in the graph which has to be mapped to a floating point number
     * @return the frequency which the vertex passed in maps to
     */
    public float mapGraphVertex(Graph<V, E> graph, V vertex) {
        if(graph == null)
            throw new IllegalArgumentException("Graph cannot be null");

        if(vertex == null)
            throw new IllegalArgumentException("Vertex cannot be null for this algorithm");

        if(!graph.containsVertex(vertex))
            throw new IllegalArgumentException("The given vertex is not present in the given graph");

        int numFriends = graph.getOutEdges(vertex).size();

        return linearFrequencyFunction.apply(numFriends);
    }

}
