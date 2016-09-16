package lakkur.echo.algorithm;

import edu.uci.ics.jung.graph.Graph;

import java.util.Collection;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Calculates the Jaccard Similarity between two vertices. Jaccard Similarity of set v1 and v2 will be the intersection
 * of v1 and v2 divided by the union of v1 and v2
 */
public class JaccardSimilarityMapper<V, E> implements GraphVertexMapper<V, E>{

    private V userVertex;

    /**
     *
     * @param userVertex - one of the vertices which will be used in calculating the similarity
     */
    public JaccardSimilarityMapper(V userVertex){
        this.userVertex = userVertex;
    }


    public void setUserVertex(V userVertex) {
        this.userVertex = userVertex;
    }

    /**
     * Maps the vertex and the "userVertex" of the instance to it's Jaccard Similarity value.
     * @param graph the graph(edu.uci.ics.jung.graph.Graph) in which the vertex exists
     * @param vertex the vertex in the graph which has to be mapped to a floating point number
     * @return
     */
    @Override
    public float mapGraphVertex(Graph<V, E> graph, V vertex) {
        if(!graph.containsVertex(userVertex) || !graph.containsVertex(vertex))
            throw new IllegalArgumentException("The given graph doesn't contain the required vertices");


        Collection<V> userVertexNeighbors = graph.getNeighbors(userVertex);
        Collection<V> vertexNeighbors = graph.getNeighbors(vertex);

        System.out.println(vertexNeighbors);
        System.out.println(userVertexNeighbors);


        int numCommonFriends = Util.intersection(userVertexNeighbors , vertexNeighbors ).size();
        System.out.println("Number of common friends is " + numCommonFriends);
        int totalFriends = Util.union(userVertexNeighbors, vertexNeighbors  ).size();


        if(userVertexNeighbors.contains(vertex))
            totalFriends--;

        if(vertexNeighbors.contains(userVertex))
            totalFriends--;


        System.out.println("Number of total friends is " + totalFriends);


        return numCommonFriends/ (float)totalFriends;
    }

    //overriding equals and hashCode as this will help in identifying an instance of this class in collections

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof JaccardSimilarityMapper){
            if(((JaccardSimilarityMapper) obj).userVertex.equals( this.userVertex))
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return userVertex.hashCode();
    }
}
