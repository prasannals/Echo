package lakkur.echo.examples;

import edu.uci.ics.jung.graph.Graph;
import lakkur.echo.algorithm.*;
import lakkur.echo.audio.AudioMode;
import lakkur.echo.audio.AudioPlayer;
import lakkur.echo.audio.FriendClusterAudio;
import lakkur.echo.audio.SimilarityAudioPlayer;
import lakkur.echo.model.Edge;
import lakkur.echo.model.FileGraphProvider;
import lakkur.echo.model.GraphProvider;
import lakkur.echo.visualization.GraphVisualizer;
import lakkur.echo.visualization.InputAction;
import lakkur.echo.visualization.JUNGGraphVisualizer;


/**
 * Created by Prasanna Lakkur Subramanyam
 *
 * Provides a simple example involving generation, visualization and audio creation of a graph
 */
public class SimpleGraphAudio {
    /**
     * Represents the graph that we're working with
     */
    private Graph<Integer, Edge<Integer>> graph;
    /**
     * two different audio players for friendcluster audio and similarity audio respectively
     */
    private AudioPlayer friendClusterAudioPlayer, similarityAudioPlayer;
    /**
     * Helps visualize the graph
     */
    private GraphVisualizer<Integer, Edge<Integer>> visualizer;
    /**
     * helps us extract the required features from the graph for a given vertex
     */
    private VertexFeatureExtractor<Integer, Edge<Integer>> featureExtractor;


    ///////////// PAY ATTENTION HERE. YOU ARE 3 NOW! CHANGE IT LATER IF IT IS REQUIRED ///////////////
    /**
     * Represents the person requesting all the data in the graph.
     */
    private int myNodeNumber = 3;



    public SimpleGraphAudio(){

        //load the data into the graph provider

        // loading small graph because it'll be easier to conduct the similarity test(I don't have to type some 100 names
        //to find a person who has a JaccardSimilarity > 0


        GraphProvider<Integer, Edge<Integer>> graphProvider = new FileGraphProvider("/data/small_test_graph.txt");

        //GraphProvider<Integer, Edge<Integer>> graphProvider = new FileGraphProvider("/data/facebook_1000.txt");
        //uncomment the above like and remove the line above that to load the data with 1000 nodes

        //get the graph for the data
        graph = graphProvider.provideGraph();

        ////////////////////// SETTING SOME NODE AS THE OBSERVER NOW   /////////////////////////
        //////////////////// IMPORTANT TO CHANGE LATER IN REAL WORLD  //////////////////////////
        /////////////////////////////////  SITUATIONS ////////////////////////////////////////
        featureExtractor = new PToPVertexFeatureExtractor<>(graph, myNodeNumber);


        //get the audio player for the graph
        friendClusterAudioPlayer = new FriendClusterAudio();

        similarityAudioPlayer = new SimilarityAudioPlayer();


        //telling the views what to do when the button is pressed and the input is received
        InputAction inputAction = new InputAction() {
            @Override
            public void processInput(int vertex, AudioMode audioMode) {
                //if there is already an audio playing, stop it
                if (friendClusterAudioPlayer.isPlaying()) {
                    friendClusterAudioPlayer.stop();
                    try {
                        Thread.sleep(1000);// allow some time for the audio context to stop before starting it again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(similarityAudioPlayer.isPlaying()){
                    similarityAudioPlayer.stop();
                    try {
                        Thread.sleep(1000);// allow some time for the audio context to stop before starting it again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    //getting the required parameters
                    float[] features = featureExtractor.extractFeatures(vertex);
                    //start playing the audio
                    if(audioMode == AudioMode.FRIEND_CLUSTER)
                        friendClusterAudioPlayer.play(features[0], features[1]);
                    else
                        similarityAudioPlayer.play(features[2]);
                } catch (IllegalArgumentException ex) {
                    //IllegalArgumentException can be thrown by "extractFeatures"
                    System.out.println("The given vertex isn't in the graph. Please try again.");
                }
            }
        };


        //initialize the graph visualizer
        visualizer = new JUNGGraphVisualizer<>(graph, inputAction);
        visualizer.highlightVertex(myNodeNumber);
    }


    public void start(){
        //start the visualization
        visualizer.visualizeGraph();
    }


    public static void main(String[] args) {
        new SimpleGraphAudio().start();
    }

}
