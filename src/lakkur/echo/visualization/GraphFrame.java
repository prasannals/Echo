package lakkur.echo.visualization;

import javax.swing.*;
import java.awt.*;

/**
 * The GUI of the graph visualization
 * @author Prasanna Lakkur Subramanyam
 */
class GraphFrame extends JFrame{
    /**
     * The width of the visualization area
     */
    private int mWidth = 1200;
    /**
     * The height of the visualization area
     */
    private int mHeight = 680;
    /**
     * The title of the GUI/Visualization
     */
    private static final String title = "Graph Visualization";

    /**
     *
     * @param component the component representing the JUNG graph visualization
     * @param inputListener implementation of InputListener specifying what needs to be done with the number retrieved
     *                      from the user
     */
    GraphFrame(Component component, InputAction inputListener){
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(mWidth, mHeight);
        setLayout(new BorderLayout());
        add(component, BorderLayout.CENTER);
        InputPanel inputPanel = new InputPanel(inputListener);
        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }



}
