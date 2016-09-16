package lakkur.echo.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * @author Prasanna Lakkur Subramanyam
 *         <p>
 *         This class is used to read the graph data from a file following the rule that each integer in the file represents a
 *         vertex in the graph and each consequitive integers are joined by a directed edge in the graph.
 *         <p>
 *         The file name(fully qualified path from the root directory of the project) of such a data file should be passing in
 *         as a parameter into the constructor.
 */
public class FileGraphProvider implements GraphProvider<Integer, Edge<Integer>> {

    /**
     * Holds the file name(fully qualified path from the root directory of the project) of the data file
     */
    private String fileName;

    /**
     * @param fileName the file name(fully qualified path from the root directory of the project) of the data file
     */
    public FileGraphProvider(String fileName) {
        this.fileName = fileName;
    }


    /**
     * The graph to be returned
     */
    private Graph<Integer, Edge<Integer>> graph;

    /**
     * A set containing all the nodes that are encountered
     */
    private Set<Integer> visitedSet;

    /**
     * @see GraphProvider#provideGraph()
     */
    @Override
    public Graph<Integer, Edge<Integer>> provideGraph() {

        graph = new UndirectedSparseGraph<>();
        visitedSet = new HashSet<>();

        InputStream inputStream = FileGraphProvider.class.getResourceAsStream(fileName);
        Scanner fileScanner = new Scanner(inputStream);

        //while the file has integers left in it
        while (fileScanner.hasNextInt()) {
            //get the next integer and let it be source
            int source = fileScanner.nextInt();
            //the integer after that is destination
            int destination = fileScanner.nextInt();

            //if the source hasn't been visited yet, add it as a vertex. This is required because the vertices have
            //to be present before an edge can be formed between them(and that is exactly what we're trying to do here)
            if (!visitedSet.contains(source)) {
                addToGraphAndVisited(source);
            }

            //same as above
            if (!visitedSet.contains(destination)) {
                addToGraphAndVisited(destination);
            }

            //form a new Edge between the two integers
            Edge<Integer> edge = new Edge<>(source, destination);
            //add the edge to the graph
            graph.addEdge(edge, source, destination);
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    /**
     * adds the give node into the graph and into the visitedSet set
     * @param node the node to be inserted into the graph and visitedSet
     */
    private void addToGraphAndVisited(int node){
        graph.addVertex(node);
        visitedSet.add(node);
    }
}
