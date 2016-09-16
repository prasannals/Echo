package lakkur.echo.visualization;

import lakkur.echo.audio.AudioMode;

/**
 * To be used with integer inputs. It is used to specify what needs to be done with the input once it is retrieved
 * @author Prasanna Lakkur Subramanyam
 */
public interface InputAction {
    /**
     * This method is used to specify what needs to be done with the input once it is retrieved
     * @param vertex the integer retrieved
     */
    void processInput(int vertex, AudioMode audioMode);
}
