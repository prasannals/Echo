package lakkur.echo.visualization;

import lakkur.echo.audio.AudioMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Panel used to get user inputs
 * @author Prasanna Lakkur Subramanyam
 */
class InputPanel extends JPanel{
    private JTextField textField;
    private JButton button;
    private List<JRadioButton> jRadioButtons;
    private ButtonGroup jRadioButtonGroup;
    private Map<String, AudioMode> audioModeMap;

    /**
     *
     * @param inputListener implementation of InputListener specifying what needs to be done with the number retrieved
     *                      from the user
     */
    InputPanel(InputAction inputListener){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(800, 25));

        jRadioButtons = generateJRadioButtons();

        jRadioButtonGroup = generateButtonGroup(jRadioButtons);


        button = new JButton("Play");

        //telling the button what to do when it is pressed
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vertexNumber = Integer.parseInt(textField.getText());
                    textField.setText("");

                    String actionCommand = jRadioButtonGroup.getSelection().getActionCommand();
                    AudioMode audioMode = audioModeMap.get(actionCommand);

                    inputListener.processInput(vertexNumber, audioMode);
                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        });

        add(textField);
        addRadioButtons(jRadioButtons);
        jRadioButtons.get(0).setSelected(true);
        add(button);
    }

    private void addRadioButtons(List<JRadioButton> radioButtons) {
        for(JRadioButton button : radioButtons){
            add(button);
        }
    }

    private ButtonGroup generateButtonGroup(List<JRadioButton> radioButtons) {
        ButtonGroup buttonGroup = new ButtonGroup();

        for(JRadioButton button : radioButtons){
            buttonGroup.add(button);
        }

        return buttonGroup;
    }

    private List<JRadioButton> generateJRadioButtons() {
        List<JRadioButton> radioButtons = new ArrayList<>();
        audioModeMap = new HashMap<>();

        JRadioButton radioButton1 = new JRadioButton("FriendCluster");
        // the action command is used to identify this button
        final String radioButton1ActionCommand = "FriendCluster";
        radioButton1.setActionCommand(radioButton1ActionCommand);
        // adding this to a map for easy and fast matching of the action command with the AudioMode
        audioModeMap.put(radioButton1ActionCommand, AudioMode.FRIEND_CLUSTER);
        radioButtons.add( radioButton1 );


        JRadioButton radioButton2 = new JRadioButton("Similarity");
        // the action command is used to identify this button
        final String radioButton2ActionCommand = "Similarity";
        radioButton2.setActionCommand(radioButton2ActionCommand);
        // adding this to a map for easy and fast matching of the action command with the AudioMode
        audioModeMap.put(radioButton2ActionCommand, AudioMode.SIMILARITY);
        radioButtons.add( radioButton2 );

        return radioButtons;
    }

}
