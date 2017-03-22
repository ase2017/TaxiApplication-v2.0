package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Created by Giorgos on 22-Mar-17.
 */
public class RightSubPanel extends JPanel{

    private String subType;
    private String[][] stringList = {{"Taxi Queue", "Add Taxi", "Automatically add taxis"},
            {"Group Queue", "Add group", "Automatically add groups"}};
    private int listID;

    private JTextArea subArea;
    private JPanel subTextPanel;
    private JCheckBox subCheck;
    private JButton subButton;


    public RightSubPanel(String subType){
        this.subType = subType;
        initializeSubPanel();
    }

    public void initializeSubPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SimulationView.backgroundColor);
        this.setBorder(new EmptyBorder(10,10,50,10));

        setListID();
        createTextArea();
        createButtonsAndCheckBoxes();

    }

    private void setListID() {

        if(this.subType.equals("taxi"))
            listID = 0;
        else if(this.subType.equals("group"))
            listID = 1;
    }

    private void createTextArea() {

        subTextPanel = new JPanel();
        subTextPanel.setLayout(new BoxLayout(subTextPanel,BoxLayout.Y_AXIS));
        subTextPanel.setBorder(new TitledBorder(new EtchedBorder(), stringList[listID][0]));

        subArea = new JTextArea("empty");
        subArea.setBackground(SimulationView.textAreaColor);
        subArea.setEditable(false);

        JScrollPane taxiScroll = new JScrollPane(subArea);
        taxiScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        subTextPanel.add(taxiScroll);
        this.add(subTextPanel);
    }

    private void createButtonsAndCheckBoxes(){

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
