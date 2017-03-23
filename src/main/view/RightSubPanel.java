package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Class Name: RightSubPanel.java
 *
 * Description: This class creates the taxi queue and the group queue that
 *              are placed in the right panel of the Simulation Screen.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class RightSubPanel extends JPanel{

    private String subType; //The type of the panel (taxi/group)

    /* The text for the labels and the buttons for each group */
    private String[][] stringList = {{"Taxi Queue", "Add Taxi", "Automatically add taxis"},
            {"Group Queue", "Add group", "Automatically add groups"}};

    private int listID; //Depending the subType use the first/second row of stringList

    private JTextArea subArea; //The textarea that keeps the information
    private JPanel subTextPanel; //The panel that keeps the textarea
    private JCheckBox subCheck; //The checkbox to add elements automatically
    private JButton subButton; //The button to add elements manually

    /**
     * This is the constructor of this class. It receives the type of
     * window which is going to be created and calls the necessary methods.
     *
     * @param subType The type of window (taxi/group)
     */
    public RightSubPanel(String subType){
        this.subType = subType;
        initializeSubPanel();
    }

    /**
     * This method is initializing the panel which is going to hold
     * all the components.
     */
    private void initializeSubPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Use vertical BoxLayout for the panel
        this.setBackground(SimulationView.backgroundColor); //Change background color of the panel
        this.setBorder(new EmptyBorder(10,10,50,10)); //Set margin around the panel

        setListID(); //Determine if the window type is taxi/group
        createTextArea(); //Call the method that creates the textarea
        createButtonsAndCheckBoxes(); //Call the method that creates the checkbox and the button

    }

    /**
     * This method determines which text is going to be used as
     * labels for the components of the window.
     */
    private void setListID() {

        if(this.subType.equals("taxi"))
            listID = 0;
        else if(this.subType.equals("group"))
            listID = 1;
    }

    /**
     * This method creates the textarea.
     */
    private void createTextArea() {

        /* Create a new panel, use vertical BoxLayout and change background color */
        subTextPanel = new JPanel();
        subTextPanel.setLayout(new BoxLayout(subTextPanel,BoxLayout.Y_AXIS));
        subTextPanel.setBorder(new TitledBorder(new EtchedBorder(), stringList[listID][0]));

        subArea = new JTextArea("empty");
        subArea.setBackground(SimulationView.textAreaColor);
        subArea.setEditable(false);

        /* Use a scrollbar for the textarea */
        JScrollPane taxiScroll = new JScrollPane(subArea);
        taxiScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        subTextPanel.add(taxiScroll);
        this.add(subTextPanel);
    }

    /**
     * This method creates the checkbox and the buttons for the panel.
     */
    private void createButtonsAndCheckBoxes(){

        /* Create the components, use the appropriate labels and place them into the panel */

        JPanel checkButPanel = new JPanel();
        checkButPanel.setLayout(new BoxLayout(checkButPanel, BoxLayout.X_AXIS));
        subButton = new JButton(stringList[listID][1]);
        subButton.setForeground(SimulationView.buttonForegroundColor);
        subButton.setBackground(SimulationView.buttonBackgroundColor);
        subButton.setEnabled(false);

        subCheck = new JCheckBox(stringList[listID][2]);
        subCheck.setName("checkbox");
        subCheck.setBackground(SimulationView.backgroundColor);
        subCheck.setEnabled(false);

        checkButPanel.add(subCheck);
        checkButPanel.add(subButton);
        this.add(checkButPanel);
    }


    /* Getters and Setters */

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public JTextArea getSubArea() {
        return subArea;
    }

    public void setSubArea(JTextArea subArea) {
        this.subArea = subArea;
    }

    public JPanel getSubTextPanel() {
        return subTextPanel;
    }

    public void setSubTextPanel(JPanel subTextPanel) {
        this.subTextPanel = subTextPanel;
    }

    public JCheckBox getSubCheck() {
        return subCheck;
    }

    public void setSubCheck(JCheckBox subCheck) {
        this.subCheck = subCheck;
    }

    public JButton getSubButton() {
        return subButton;
    }

    public void setSubButton(JButton subButton) {
        this.subButton = subButton;
    }
}
