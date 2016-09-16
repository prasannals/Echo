package lakkur.echo.examples;

import edu.uci.ics.jung.graph.Graph;
import lakkur.echo.algorithm.PToPVertexFeatureExtractor;
import lakkur.echo.algorithm.VertexFeatureExtractor;
import lakkur.echo.audio.AudioPlayer;
import lakkur.echo.audio.SimilarityAudioPlayer;
import lakkur.echo.model.Edge;
import lakkur.echo.model.FileGraphProvider;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * This class simulates a radio which a user might listen to, to find new friends
 */
public class SimilarityRadioSimulator {

    private Graph<Integer, Edge<Integer>> graph;
    private AudioPlayer audioPlayer;
    private VertexFeatureExtractor<Integer, Edge<Integer>> featureExtractor;
    private int userVertex = 3;

    public SimilarityRadioSimulator(){
        graph = new FileGraphProvider("/data/small_test_graph.txt").provideGraph();
        audioPlayer = new SimilarityAudioPlayer();
        featureExtractor = new PToPVertexFeatureExtractor<>(graph, userVertex);
    }

    public void start(){

        for(int vertex : graph.getVertices()){
            float[] features = featureExtractor.extractFeatures(vertex);

            System.out.println("\n\nThe node currently playing is " + vertex + "\n");

            if(audioPlayer.isPlaying()) {
                audioPlayer.stop();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            audioPlayer.play(features[2]);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        audioPlayer.stop();

    }


    public static void main(String[] args){
        new SimilarityRadioSimulator().start();
    }


}
