package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Giorgos on 22-Mar-17.
 */
public class WindowPanel extends JPanel{

    private JPanel windowPanel, buttonPanel, windowTextPanel;
    private JButton endOfDayButton, graphButton;
    private JTextArea windowContent;
    private JScrollPane windowScroll;

    private int windowID;

    public WindowPanel(int windowID){
        this.windowID = windowID;
        initializeComponents();
    }

    private void initializeComponents() {

        this.setLayout(new GridLayout(2,1));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(SimulationView.backgroundColor);

        endOfDayButton = new JButton("End of day");
        graphButton = new JButton("Show graph");

        endOfDayButton.setBackground(SimulationView.buttonBackgroundColor);
        endOfDayButton.setForeground(SimulationView.buttonForegroundColor);
        endOfDayButton.setEnabled(false);
        endOfDayButton.setName("endOfDayButton" + this.windowID);

        graphButton.setBackground(SimulationView.buttonBackgroundColor);
        graphButton.setForeground(SimulationView.buttonForegroundColor);
        graphButton.setEnabled(true);
        graphButton.setName("graphButton" + this.windowID);


        windowTextPanel = new JPanel();
        windowTextPanel.setLayout(new BoxLayout(windowTextPanel,BoxLayout.Y_AXIS));
        windowTextPanel.setBorder(new TitledBorder(new EtchedBorder(), "Window " + this.windowID));
        windowContent = new JTextArea("Ready to start...");
        windowContent.setEditable(false);
        windowContent.setBackground(SimulationView.textAreaColor);

        windowScroll = new JScrollPane(windowContent);
        windowScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        windowTextPanel.add(windowScroll);

        this.setBorder(new EmptyBorder(15,10,0,10));
        this.setBackground(SimulationView.backgroundColor);
        windowContent.setPreferredSize(new Dimension(150,100));

        this.add(windowTextPanel);
        buttonPanel.add(endOfDayButton);
        buttonPanel.add(graphButton);
        this.add(buttonPanel);

    }


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
