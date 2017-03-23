package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Class Name: WindowPanel.java
 *
 * Description: This class creates the windows that are positioned into
 *              the left panel that is created by {@link LeftPanel}.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class WindowPanel extends JPanel{

    private JPanel windowPanel, buttonPanel, windowTextPanel; //The JPanels for the window, the buttons and the textarea
    private JButton endOfDayButton, graphButton; //The "End Of Day" and "Show Graph" buttons
    private JTextArea windowContent; //The textarea
    private JScrollPane windowScroll; //The scrollbar for each window

    private int windowID; //The ID of the window

    /**
     * This is the constructor of this class.
     *
     * @param windowID The identifier for each window
     */
    public WindowPanel(int windowID){
        this.windowID = windowID;
        initializeComponents();
    }

    /**
     * This method creates one new window that consists of the
     * textarea and the buttons.
     */
    private void initializeComponents() {

        this.setLayout(new GridLayout(2,1)); //Set layout of the panel as GridLayout
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //Create a FlowLayout for the buttons

        buttonPanel.setBackground(SimulationView.backgroundColor); //Set the background color for the button's panel

        endOfDayButton = new JButton("End of day"); //Create the buttons and give them labels
        graphButton = new JButton("Show graph");

        /* For each button change background and foreground color and set names. */

        endOfDayButton.setBackground(SimulationView.buttonBackgroundColor);
        endOfDayButton.setForeground(SimulationView.buttonForegroundColor);
        endOfDayButton.setEnabled(false);
        endOfDayButton.setName("endOfDayButton" + this.windowID);

        graphButton.setBackground(SimulationView.buttonBackgroundColor);
        graphButton.setForeground(SimulationView.buttonForegroundColor);
        graphButton.setEnabled(true);
        graphButton.setName("graphButton" + this.windowID);


        /* Create the JPanel which is going to hold all the components */

        windowTextPanel = new JPanel();
        windowTextPanel.setLayout(new BoxLayout(windowTextPanel,BoxLayout.Y_AXIS));
        windowTextPanel.setBorder(new TitledBorder(new EtchedBorder(), "Window " + this.windowID));
        windowContent = new JTextArea("Ready to start...");
        windowContent.setEditable(false);
        windowContent.setBackground(SimulationView.textAreaColor);

        /* Put a scrollbar into the panel */

        windowScroll = new JScrollPane(windowContent);
        windowScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        windowTextPanel.add(windowScroll);

        this.setBorder(new EmptyBorder(15,10,0,10)); //Margin around the panel
        this.setBackground(SimulationView.backgroundColor); //Change panel's background color
        windowScroll.setPreferredSize(new Dimension(150,100)); //Use fixed dimensions for the textarea

        this.add(windowTextPanel); //Add the textarea to the panel
        buttonPanel.add(endOfDayButton); //Add the buttons to the buttons' panel
        buttonPanel.add(graphButton);
        this.add(buttonPanel); //Add the buttons' panel to the main panel

    }


    /* Getters and Setters */

    public JPanel getWindowPanel() {
        return windowPanel;
    }

    public void setWindowPanel(JPanel windowPanel) {
        this.windowPanel = windowPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getWindowTextPanel() {
        return windowTextPanel;
    }

    public void setWindowTextPanel(JPanel windowTextPanel) {
        this.windowTextPanel = windowTextPanel;
    }

    public JButton getEndOfDayButton() {
        return endOfDayButton;
    }

    public void setEndOfDayButton(JButton endOfDayButton) {
        this.endOfDayButton = endOfDayButton;
    }

    public JButton getGraphButton() {
        return graphButton;
    }

    public void setGraphButton(JButton graphButton) {
        this.graphButton = graphButton;
    }

    public JTextArea getWindowContent() {
        return windowContent;
    }

    public void setWindowContent(JTextArea windowContent) {
        this.windowContent = windowContent;
    }

    public JScrollPane getWindowScroll() {
        return windowScroll;
    }

    public void setWindowScroll(JScrollPane windowScroll) {
        this.windowScroll = windowScroll;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }
}
