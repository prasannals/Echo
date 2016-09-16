package lakkur.echo.audio;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Glide;
import net.beadsproject.beads.ugens.WavePlayer;


/**
 * Plays an audio for a graph. The audio will be a function of the number of friends of the configured vertex and the
 * clustering coefficient of the configured vertex.
 *
 */
public class FriendClusterAudio implements AudioPlayer{

    /**
     * time constant for 10 ms
     */
    private static final float TIME_ALMOST_IMMEDIATE = 10;

    /**
     * AudioContext is the central class in Beads. It helps process and play audio.
     */
    private AudioContext audioContext;
//    /**
//     * Glide is used to control the frequency
//     */
//    private Glide freqGlide;

    /**
     * This Envelope is used to control the frequency.
     */
    private Envelope freqEnv;

    /**
     * WavePlayer is used to generate sound waves.
     */
    private WavePlayer wavePlayer;
    /**
     * Gain of the wave player
     */
    private Gain gain;

    /**
     * Base Frequency and the clustering co efficient
     */
    private float baseFreq, clusteringCoEfficient;

    public FriendClusterAudio(){
        audioContext = new AudioContext();
        freqEnv = new Envelope(audioContext, 100);
        wavePlayer = new WavePlayer(audioContext, freqEnv, Buffer.SINE);
        //one output and 70% gain
        gain = new Gain(audioContext, 1, 0.7f);

        gain.addInput(wavePlayer);

        audioContext.out.addInput(gain);
    }

    /**
     * The first parameter should be the base frequency. The second parameter should be the clustering co efficient.
     * @param args the parameters upon which the audio depends
     */
    @Override
    public void play(float... args) {
        if(args == null)
            throw new IllegalArgumentException("args cannot be null");
        if(args.length != 2)
            throw new IllegalArgumentException("This audio player expects two arguments");


        baseFreq = args[0];
        clusteringCoEfficient = args[1];


        //for informational purposes. Will be removed later
        System.out.println("Frequency is " + baseFreq);
        System.out.println("Clustering co ef is " + clusteringCoEfficient);

        audioContext.start();

        freqEnv.addSegment(baseFreq, TIME_ALMOST_IMMEDIATE);

        //if clusteringCoEfficient is zero, there will be a constant frequency playing. If not, vary it.
        if(clusteringCoEfficient != 0) {
            //frequency will vary between baseFreq and baseFreq + freqRange
            float freqRange = getFreqRange();

            //it will take these many steps to go from baseFreq to baseFreq + freqRange
            int numSteps = getNumSteps();

            //the frequency will be incremented/decremented by this much every step
            int incrementEachStep = Math.round(freqRange) / numSteps;

            //keeps track of whether the frequency should increase or decrease
            boolean increasing = true;

            //keeps track of the number by which incrementEachStep should be multiplied with. Varies from 0 to
            //numSteps - 1
            int i = 0;

            //going from base to max frequency and then back again is one cycle. decides how many such cycles should
            //happen
            int numCycles = 3;
            //total number of steps in numCycles number of cycles
            int totalSteps = ((numSteps - 1) * 2  * numCycles) + 1;
            //total steps completed so far
            int totalIters = 0;

            //total time the audio should play for. This will be a constant. Time is in milli seconds
            long totalTime = (8 * 1000);
            //delayTime is the delay between each increase/decrease in frequency.
            //It is a function of clusteringCoEfficient
            long delayTime = totalTime/ totalSteps;


            while (totalIters < totalSteps) {
                //calculate the frequency to be played this iteration
                float resFreq = baseFreq + (i * incrementEachStep);

                //or information. will be taken out later
                System.out.println("Freq is " + resFreq);
                freqEnv.addSegment(resFreq, TIME_ALMOST_IMMEDIATE);

                if (increasing) {
                    //if the last step is reached.
                    if (i == (numSteps - 1)) {
                        increasing = false;
                        i--;
                    } else {
                        i++;
                    }
                } else {
                    //if the first step is reached
                    if (i == 0) {
                        increasing = true;
                        i++;
                    } else {
                        i--;
                    }
                }

//                try {
//                    //wait for delayTime number of milliseconds.(This works because audio is being played using Beads.
//                    //Beads plays the audio in a new thread.
//                    Thread.sleep(delayTime);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                freqEnv.addSegment(resFreq, delayTime);

                //will be removed later
                System.out.println(" Total steps " + totalIters);
                totalIters++;
            }

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            audioContext.stop(); todo or not to todo

        }


    }



    /**
     * Encapsulates the calculation of frequency range.
     * @return the number which when added to the base frequency, gives the maximum frequency
     */
    private float getFreqRange(){
        return (baseFreq * clusteringCoEfficient);
    }

    /**
     * Encapsulates the calculation of number of steps in the audio.
     * @return the number of steps in the audio
     */
    private int getNumSteps(){
        return Math.round(clusteringCoEfficient * 10);
    }


    /**
     * @see AudioPlayer#stop()
     */
    @Override
    public void stop() {
        if (audioContext != null) {
            audioContext.stop();
        }
    }

    /**
     * @see AudioPlayer#isPlaying()
     */
    @Override
    public boolean isPlaying() {
        if (audioContext == null)
            return false;
        else
            return audioContext.isRunning();
    }

}
