package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RightPanel extends JPanel{

    private RightSubPanel taxiPanel;
    private RightSubPanel groupPanel;

    public RightPanel(){

        initializeComponents();

    }

    private void initializeComponents(){

        this.taxiPanel = new RightSubPanel("taxi");
        this.groupPanel = new RightSubPanel("group");
        initializePanel();
        fillPanel();

    }

    private void initializePanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SimulationView.backgroundColor);
        this.setBorder(new EmptyBorder(50,50,50,50));

    }

    private void fillPanel() {
        this.add(this.taxiPanel);
        this.add(this.groupPanel);
    }

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
