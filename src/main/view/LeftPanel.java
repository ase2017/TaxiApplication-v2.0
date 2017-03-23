package main.view;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class Name: LeftPanel.java
 *
 * Description: This class creates the panel that is in the left side of the
 *              Graphical User Interface (Simulation Screen) that includes the
 *              windows.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */

public class LeftPanel extends JPanel{

    private int numOfWindows; //Total number of windows to be created
    private ArrayList<WindowPanel> windows = new ArrayList<>(); //ArrayList that keeps all the windows

    /**
     * This is the constructor of this class. It creates the left panel and fills
     * it with a given number of windows.
     *
     * @param numOfWindows The requested number of windows.
     */
    public LeftPanel(int numOfWindows){

        this.numOfWindows = numOfWindows;
        initializeComponents(); //Initialize Panel
    }

    /**
     * This method calls the methods that initialize the left panel's structure
     * and fil it with windows.
     */
    private void initializeComponents(){

        initializePanel();
        fillPanel();
    }

    /**
     * This method initializes the panel's structure.
     */
    private void initializePanel(){

        JScrollPane scroll = new JScrollPane(this); //Put a scrollbar to the panel
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Use vertical BoxLayout

    }

    /**
     * This method creates a number of windows using {@link WindowPanel} and puts
     * them into the left panel.
     */
    private void fillPanel(){

        /* Create windows, add them into the panel and into the list. */
        for(int i =0; i<this.numOfWindows;i++){
            WindowPanel windowPanel = new WindowPanel(i);
            this.add(windowPanel);
            windows.add(windowPanel);
        }
    }

    /* Getters and Setters */

    public ArrayList<WindowPanel> getWindows() {
        return windows;
    }

    public void setWindows(ArrayList<WindowPanel> windows) {
        this.windows = windows;
    }


}
