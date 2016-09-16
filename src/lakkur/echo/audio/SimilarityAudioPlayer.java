package lakkur.echo.audio;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Glide;
import net.beadsproject.beads.ugens.WavePlayer;

/**
 * @author Prasanna Lakkur Subramanyam
 *
 * Plays an audio that represents how similar a given node is to the node passed in the constructor
 */
public class SimilarityAudioPlayer implements AudioPlayer{
    private AudioContext audioContext;
    private Glide modulatorFreqGlide;
    private WavePlayer carrier, modulator;
    private Gain carrierGain;
    private Function frequencyModulatorFunction;
    private float modulatorInitFrequency = 1;

    private float freqVariation = 50;
    private float baseFreq = 200;
    private float gainVal = 0.7f;
    private float maxModulatorFrequency = 6;

    public SimilarityAudioPlayer(){
        audioContext = new AudioContext();

        modulatorFreqGlide = new Glide(audioContext, modulatorInitFrequency, 50);

        modulator = new WavePlayer(audioContext, modulatorFreqGlide, Buffer.SINE);

        frequencyModulatorFunction = new Function(modulator) {
            @Override
            public float calculate() {
                return (x[0] * freqVariation ) + baseFreq;
            }
        };

        carrier = new WavePlayer(audioContext, frequencyModulatorFunction, Buffer.SINE);

        carrierGain = new Gain(audioContext, 1, gainVal);

        carrierGain.addInput(carrier);

        audioContext.out.addInput(carrierGain);

    }

    /**
     * @see AudioPlayer#play(float...)
     */
    @Override
    public void play(float... args) {
        if(args == null)
            throw new IllegalArgumentException("args cannot be null");
        if(args.length != 1)
            throw new IllegalArgumentException("This audio player expects one argument");
        if(args[0] > 1 || args[0] < 0)
            throw new IllegalArgumentException("The first argument value should be between 0 and 1(both inclusive)");

        modulatorFreqGlide.setValue(maxModulatorFrequency * args[0]);

        audioContext.start();
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
