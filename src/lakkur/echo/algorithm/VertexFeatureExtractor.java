package lakkur.echo.algorithm;

/**
 * @author Prasanna Lakkur Subramanyam
 * Exacts features for the the vertex passed into the parameter of "extractFeatures"
 */
public interface VertexFeatureExtractor<V, E> {
    /**
     * Exacts features for the the vertex passed into the parameter of "extractFeatures"
     * @param vertex - the vertex whose features have to be extracted
     * @return an array of features
     */
    float[] extractFeatures(V vertex);
}
