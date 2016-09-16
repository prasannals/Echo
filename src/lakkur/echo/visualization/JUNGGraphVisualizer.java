package lakkur.echo.visualization;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This class provides visualization of JUNG Graphs(edu.uci.ics.jung.graph.Graph)
 *
 * @param <V> the type of the vertex of the graph
 * @param <E> the type of the edge of the graph
 */
public class JUNGGraphVisualizer<V, E> implements GraphVisualizer<V, E> {
    /**
     * The width of the visualization area
     */
    private int visWidth = 1200;
    /**
     * The height of the visualization area
     */
    private int visHeight = 680;
    /**
     * The title of the GUI/Visualization
     */
    private final String title = "Graph Visualization";
    private Layout<V, E> layout;
    private BasicVisualizationServer<V, E> visualizationServer;
    private InputAction inputAction;
    /**
     *
     * @param graph the Graph(edu.uci.ics.jung.graph.Graph) which has to be visualized
     * @param inputAction implementation of InputListener specifying what needs to be done with the number retrieved
     *                      from the user
     */
    public JUNGGraphVisualizer(Graph<V, E> graph, InputAction inputAction) {
        this.inputAction = inputAction;
        //layout = new KKLayout<>(graph);
        //layout = new CircleLayout<>(graph); //an alternative layout
        layout = new ISOMLayout<>(graph);
        layout.setSize(new Dimension(visWidth, visHeight));

        visualizationServer = new BasicVisualizationServer<V, E>(layout);
        visualizationServer.setPreferredSize(new Dimension(visWidth, visHeight));
        visualizationServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        //visualizationServer.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        visualizationServer.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
    }


    /**
     * @see GraphVisualizer#visualizeGraph()
     */
    @Override
    public void visualizeGraph() {
        //starting the swing JFrame

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphFrame(visualizationServer, inputAction);
            }
        });

    }

    /**
     *
     * @see GraphVisualizer#highlightVertex(Object)
     */
    @Override
    public void highlightVertex(V vertex) {
        if (vertex == null)
            throw new IllegalArgumentException("Vertex cannot be null");


        Transformer<V, Paint> paintTransformer = new Transformer<V, Paint>() {
            @Override
            public Paint transform(V v) {
                if (v.equals(vertex)) {
                    return Color.GREEN;
                } else {
                    return Color.RED;
                }
            }
        };

        visualizationServer.getRenderContext().setVertexFillPaintTransformer(paintTransformer);


    }

    /**
     * @see GraphVisualizer#reset()
     */
    @Override
    public void reset() {
        Transformer<V, Paint> paintTransformer = new Transformer<V, Paint>() {
            @Override
            public Paint transform(V v) {
                    return Color.RED;
            }
        };

        visualizationServer.getRenderContext().setVertexFillPaintTransformer(paintTransformer);

    }


}
