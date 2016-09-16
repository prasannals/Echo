package lakkur.echo.examples;

import lakkur.echo.algorithm.LinearFrequencyFunction;
import lakkur.echo.audio.AudioPlayer;
import lakkur.echo.audio.FriendClusterAudio;

import java.util.Scanner;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 *
 *
 * ////////////////////////////// WARNING!!!!!!!!!!!!!! /////////////////////////////////////
 * //////////// DO NOT USE THIS CLASS UNLESS YOU KNOW WHAT YOU'RE DOING(means that you're a part of this project's
 * development team most of the time).  IF YOU'RE NOT SURE, DON'T USE IT!!!! THIS CAN GENERATE HIGH FREQUENCY
 * SOUND WAVES. I AM NOT YET AWARE OF IT'S EFFECTS COMPLETELY ///////////////////////////////////////////////
 *
 *
 *
 *
 * //////////////////////          AGAIN            ///////////////////////////////////
 * /////////////////  IF NOT SURE,  DONT USE THIS CLASS         ///////////////////////////
 *
 *
 *
 *
 *
 *
 *
 *
 * This class helps you simulate hypothetical scenarios with n number of friends(0 <= n <= 5000 i is an whole number)
 * and c clustering coefficient (0 <= c <= 1 c belongs to real numbers) and the resulting sound which is a function of
 * n and c.
 */
public class Simulation {

    private Scanner scanner;

    private AudioPlayer audioPlayer;

    private LinearFrequencyFunction linearFrequencyFunction;

    public Simulation(){
        scanner = new Scanner(System.in);
        audioPlayer = new FriendClusterAudio();
        linearFrequencyFunction = new LinearFrequencyFunction(300, 15000, 5000);
    }


    public void start(){
        System.out.println("Enter the value of the number of friends and the clustering coefficient." +
                "(in the format 'x y' where x is number of friends and y is the clustering coefficient)." +
                "Enter 'exit' to stop");

        while (true){
            if(scanner.hasNextLine()){
                String response = scanner.nextLine();
                if(response.equals("exit"))
                    System.exit(0);

                String[] responseInts = response.split(" ");

                try{
                    int numFriends = Integer.parseInt(responseInts[0]);
                    float clusteringCoEf = Float.parseFloat(responseInts[1]);

                    if (audioPlayer.isPlaying()) {
                        audioPlayer.stop();
                        Thread.sleep(1000);// allow some time for the audio context to stop before starting it again
                    }

                    audioPlayer.play(linearFrequencyFunction.apply(numFriends), clusteringCoEf);
                }catch (IndexOutOfBoundsException | NumberFormatException ex){
                    System.out.println("Please enter the input in the correct format i.e. "
                    +"'x y' where x is number of friends and y is the clustering coefficient. ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }


    public static void main(String[] args){
        new Simulation().start();
    }

}
