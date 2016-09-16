import edu.uci.ics.jung.graph.Graph;
import lakkur.echo.algorithm.GraphVertexMapper;
import lakkur.echo.algorithm.JaccardSimilarityMapper;
import lakkur.echo.audio.AudioMode;
import lakkur.echo.model.Edge;
import lakkur.echo.model.FileGraphProvider;
import lakkur.echo.visualization.GraphVisualizer;
import lakkur.echo.visualization.InputAction;
import lakkur.echo.visualization.JUNGGraphVisualizer;

/**
 * @author Prasanna Lakkur Subramanyam
 * Not the conventional test class. But I find this to be an easier verification method, for now, and for small graphs.
 */
public class JaccardSimilarityTester {
    private Graph<Integer, Edge<Integer>> graph;
    private GraphVertexMapper<Integer, Edge<Integer>> jaccardMapper;
    private int userVertex;


    public static void main(String[] agrs) {
        new JaccardSimilarityTester().go();
    }

    public void go(){
        graph = new FileGraphProvider("/data/small_test_graph.txt").provideGraph();

        userVertex = 9;
        jaccardMapper = new JaccardSimilarityMapper<>(userVertex);
        GraphVisualizer<Integer, Edge<Integer>> graphVisualizer = new JUNGGraphVisualizer<>(graph, new InputAction() {
            @Override
            public void processInput(int vertex, AudioMode audioMode) {
                float res = jaccardMapper.mapGraphVertex(graph, vertex);
                System.out.println("Jaccard similarity between " + userVertex + " and " + vertex + " is " + res);
            }
        });

        graphVisualizer.visualizeGraph();

    }


}
