package lakkur.echo.algorithm;

import edu.uci.ics.jung.graph.Graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number which is it's clustering
 * coefficient
 * @param <V> The type of the vertex
 * @param <E> The type of the edge
 */
public class ClusteringCoefficientMapper<V,E> implements GraphVertexMapper<V,E> {


        /*
        Clustering coefficient = number of friends who are friends with each other / total number of friends
        This is with respect to any given vertex.

        Algorithm to find clustering coefficient

        neighbors = getNeighbors(vertex);
        commonNeighbors = {} //empty set

        for each neighbor in neighbors{
            neighborsOfNeighbor = getNeighbors(neighbor) // get the neighbors of the neighbor
            common = intersection(neighborsOfNeighbor, neighbors) //an intersection between the "vertex" neighbors and
                                                                // the "neighbor" variable neighbors. Gives the friends
                                                                // of "vertex" who are also friends with "neighbor".
            commonNeighbors.addAllEntriesIn(common);   // add all such common friends into a set
        }

        clusteringCoEfficient = commonNeighbors.size() / neighbors.size();



         clustering coefficient will always be between 0.0 and 1.0
          Proof : any neighbor in "commonNeighbors" would also have to be in "neighbors" since anything that goes into
          it will be an intersection involving "neighbors". Therefore, it's size cannot exceed the maximum size of
          "neighbors". Therefore, clustering co efficient will always be between 0.0 and 1.0

         */





    /**
     * Maps a given vertex in a graph(edu.uci.ics.jung.graph.Graph) to a floating point number which is it's clustering
     * coefficient
     * @param graph the graph(edu.uci.ics.jung.graph.Graph) in which the vertex exists
     * @param vertex the vertex in the graph which has to be mapped to a floating point number
     * @return the clustering coefficient of the vertex passed in
     */
    @Override
    public float mapGraphVertex(Graph<V, E> graph, V vertex) {
        if(graph == null)
            throw new IllegalArgumentException("Graph cannot be null");

        if(vertex == null)
            throw new IllegalArgumentException("Vertex cannot be null for this algorithm");

        if(!graph.containsVertex(vertex))
            throw new IllegalArgumentException("The given vertex is not present in the given graph");

        //collection of all the neighbors of v
        Collection<V> neighbors = graph.getNeighbors(vertex);


        Set<V> neighborsWhoAreNeighbors = new HashSet<>();

        for(V neighborVertex : neighbors){
            Collection<V> nOfN = graph.getNeighbors(neighborVertex);
            neighborsWhoAreNeighbors.addAll(Util.intersection(neighbors, nOfN));
        }

        return neighborsWhoAreNeighbors.size() / (float)neighbors.size();
    }

    /**
     * finds and returns an intersection between the Collections passed in as parameters
     * @param collectionA
     * @param collectionB
     * @return a set representing the intersection between the two parameters
     */
    private Set<V> intersection(Collection<V> collectionA, Collection<V> collectionB){
        Set<V> inter = new HashSet<>(collectionA);
        inter.retainAll(collectionB);
        return inter;
    }
}
