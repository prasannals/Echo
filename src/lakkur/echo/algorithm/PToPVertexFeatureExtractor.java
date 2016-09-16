package lakkur.echo.algorithm;

import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prasanna Lakkur Subramanyam
 * PToPVertexFeatureExtractor assumes that the person requesting the features to be extracted is also a member of the
 * underlying network(hence, PToP, yeah.... not too good with naming....).
 */
public class PToPVertexFeatureExtractor<V, E> implements VertexFeatureExtractor<V, E> {

    /**
     * the graph of the network from which the features have to be extracted
     */
    private Graph<V, E> graph;

    /**
     * The vertex representing the person requesting the features
     */
    private V userVertex;
    /**
     * A list of GraphVertexMappers which will be used to extract the features
     */
    private List<GraphVertexMapper<V, E>> graphVertexMappers;
    /**
     * An instance of JaccardSimilarityMapper. I have this explicitly here because it can change if the "userVertex"
     * changes
     */
    private JaccardSimilarityMapper<V, E> similarityMapper;

    /**
     *
     * @param graph the graph of the network from which the features have to be extracted
     * @param userVertex The vertex representing the person requesting the features
     */
    public PToPVertexFeatureExtractor(Graph<V, E> graph, V userVertex){
        if(graph == null)
            throw new IllegalArgumentException("Graph cannot be null");
        if(userVertex == null)
            throw new IllegalArgumentException("User vertex cannot be null");
        if(!graph.containsVertex(userVertex))
            throw new IllegalArgumentException("The vertex passed in doesn't exist in the graph");

        this.graph = graph;
        this.userVertex = userVertex;

        graphVertexMappers = new ArrayList<>();

        addVertexMappers(graphVertexMappers);
    }

    /**
     * Adds the required GraphVertexMappers into the list passed in
     * @param graphVertexMappers the list into which the GraphVertexMappers have to be added
     */
    private void addVertexMappers(List<GraphVertexMapper<V, E>> graphVertexMappers) {
        float minFreq = 300;
        float maxFreq = 15000;
        int maxFriends = 5000;

        graphVertexMappers.add(new LinearFrequencyMapper<>(minFreq, maxFreq, maxFriends));
        graphVertexMappers.add(new ClusteringCoefficientMapper<>());
        similarityMapper = new JaccardSimilarityMapper<>(userVertex);
        graphVertexMappers.add(similarityMapper);

        //as we expand this project and extract more features, the new GraphVertexMappers can be added here
    }

    /**
     * Used to set/change the node of the person in the graph requesting the features
     * @param userVertex The vertex representing the person requesting the features
     */
    public void setUserVertex(V userVertex){
        if(!graph.containsVertex(userVertex))
            throw new IllegalArgumentException("The vertex passed in doesn't exist in the graph");

        this.userVertex = userVertex;
        int index = graphVertexMappers.indexOf(similarityMapper);
        graphVertexMappers.remove(similarityMapper);
        similarityMapper = new JaccardSimilarityMapper<>(userVertex);
        graphVertexMappers.add(index, similarityMapper);
    }


    /**
     * @see VertexFeatureExtractor#extractFeatures(Object)
     */
    @Override
    public float[] extractFeatures(V vertex) {
        if(vertex == null)
            throw new IllegalArgumentException("Vertex cannot be null");
        if(!graph.containsVertex(vertex))
            throw new IllegalArgumentException("The vertex passed in doesn't exist in the graph");


        float[] features = getFeaturesFromMappers(graphVertexMappers, graph, vertex);

        return features;
    }

    /**
     * I think it's a pretty descriptive method name....
     * @param graphVertexMappers a list containing the GraphVertexMappers which will be used to extract the features
     * @param graph the graph from which the features have to be extracted
     * @param vertex the vertex with respect to which the features have to be extracted
     * @return an array containing the required features
     */
    private float[] getFeaturesFromMappers(List<GraphVertexMapper<V, E>> graphVertexMappers, Graph<V, E> graph, V vertex) {
        float[] features = new float[graphVertexMappers.size()];

        for(int i = 0; i < graphVertexMappers.size(); i++){
            features[i] = graphVertexMappers.get(i).mapGraphVertex(graph, vertex);
        }

        return features;
    }


}
