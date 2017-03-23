package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class Name: RightPanel.java
 *
 * Description: This class creates the panel that is in the right side of the
 *              Graphical User Interface (Simulation Screen) that includes the
 *              taxi queue and the group queue.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */

public class RightPanel extends JPanel{

    private RightSubPanel taxiPanel; //The panel with the taxi queue
    private RightSubPanel groupPanel; //The panel with the group queue

    /**
     * This is the constructor of this class. It calls the method {@link #initializeComponents()}
     * to create the panel.
     */
    public RightPanel(){

        initializeComponents();

    }

    /**
     * This method calls the methods {@link #initializePanel()} and {@link #fillPanel()}
     * to create the structure of the panel and fill it with the two queues.
     */
    private void initializeComponents(){

        this.taxiPanel = new RightSubPanel("taxi");
        this.groupPanel = new RightSubPanel("group");
        initializePanel();
        fillPanel();

    }

    /**
     * This method initializes the panel's structure.
     */
    private void initializePanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Use vertical BoxLayout
        this.setBackground(SimulationView.backgroundColor); //Change the panel's background color
        this.setBorder(new EmptyBorder(50,50,50,50)); //Put margin aroung the panel

    }

    /**
     * This method is filling the panel with the two queues.
     */
    private void fillPanel() {
        this.add(this.taxiPanel);
        this.add(this.groupPanel);
    }

    /* Getters and Setters */

    public RightSubPanel getTaxiPanel() {
        return taxiPanel;
    }

    public void setTaxiPanel(RightSubPanel taxiPanel) {
        this.taxiPanel = taxiPanel;
    }

    public RightSubPanel getGroupPanel() {
        return groupPanel;
    }

    public void setGroupPanel(RightSubPanel groupPanel) {
        this.groupPanel = groupPanel;
    }
}
