package lakkur.echo.audio;


/**
 * Defines an audio player.
 */
public interface AudioPlayer {

    /**
     * Starts playing the audio. The audio will be a function of the parameters passsed in.
     * @param args the parameters upon which the audio depends
     */
    void play(float... args);

    /**
     * Stops playing the audio
     */
    void stop();

    /**
     * Returns true if audio is playing, false otherwise.
     * @return true if audio is playing, false otherwise
     */
    boolean isPlaying();
}
