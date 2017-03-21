package main.view;
import main.log.LoggerSingleton;
import main.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by Giorgos on 19-Mar-17.
 */
public class SimulationView implements ActionListener,Observer{

    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel,menuPanel,leftPanel,rightPanel,simulationPanel,taxiPanel,groupPanel;
    private JButton startButton, resumeButton, stopButton, exportButton, taxiButton, groupButton;
    private JTextArea taxiArea, groupArea;
    private JCheckBox taxiCheck, groupCheck;
    private JSplitPane split1;
    private JScrollPane scroll;

    private Color backgroundColor = new Color(217,217,217); //44.62.80
    private Color textAreaColor = new Color(255,255,253);
    private Color buttonBackgroundColor = new Color(44,62,80);
    private Color buttonForegroundColor = new Color(255,255,255);

    private boolean isRunning = false;
    private ArrayList<JTextArea> windowList = new ArrayList<>();
    private ArrayList<JPanel> windowPanels = new ArrayList<>();

    private MainModel md;


    public SimulationView(MainModel md){

        this.md = md;




    }

    public void createWindows(){
        for(int i=0; i<this.md.getWindows().length; i++)
            addWindow();
    }

    public void initializeComponents(){

        initializeMainFrame();

    }

    private void initializeMainFrame() {

        mainFrame.setTitle("SimulationView - TaxiApplication v2.0");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000,800);
        mainFrame.setLocationRelativeTo(null);
        initializeMainPanel();
        initializeFrame();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

    private void initializeFrame() {
        mainFrame.add(mainPanel);
    }

    private void initializeMainPanel(){

        mainPanel = new JPanel(new BorderLayout());

        initializeMenuPanel();
        initializeSimulationPanel();

    }

    private void initializeSimulationPanel() {

        simulationPanel = new JPanel(new GridLayout());
        simulationPanel.setBackground(backgroundColor);

        split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        initializeLeftPanel();
        initializeRightPanel();

        split1.setResizeWeight(0.7);
        simulationPanel.add(split1);

        mainPanel.add(simulationPanel, BorderLayout.CENTER);
        mainPanel.setBackground(backgroundColor);
    }

    private void initializeLeftPanel() {

        leftPanel = new JPanel();
        scroll = new JScrollPane(leftPanel);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        split1.add(scroll);
    }

    private void initializeRightPanel() {

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(backgroundColor);

        taxiPanel = new JPanel();
        taxiPanel.setLayout(new BoxLayout(taxiPanel, BoxLayout.X_AXIS));
        taxiPanel.setBackground(backgroundColor);

        groupPanel = new JPanel();
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));
        groupPanel.setBackground(backgroundColor);

        rightPanel.setBorder(new EmptyBorder(50,50,50,50));
        groupPanel.setBorder(new EmptyBorder(10,10,50,10));
        taxiPanel.setBorder(new EmptyBorder(10,10,50,10));

        taxiArea = new JTextArea("empty");
        taxiArea.setBackground(textAreaColor);

        groupArea = new JTextArea("empty");
        groupArea.setBackground(textAreaColor);

        taxiButton = new JButton("Add Taxi");
        taxiButton.addActionListener(this);
        taxiButton.setForeground(buttonForegroundColor);
        taxiButton.setBackground(buttonBackgroundColor);
        taxiButton.setEnabled(false);

        taxiCheck = new JCheckBox("Automatically add taxis");
        taxiCheck.setBackground(backgroundColor);
        taxiCheck.setEnabled(false);

        groupButton = new JButton("Add Group");
        groupButton.addActionListener(this);
        groupButton.setForeground(buttonForegroundColor);
        groupButton.setBackground(buttonBackgroundColor);
        groupButton.setEnabled(false);

        groupCheck = new JCheckBox("Automatically add groups");
        groupCheck.setBackground(backgroundColor);
        groupCheck.setEnabled(false);

        rightPanel.add(taxiArea);
        taxiPanel.add(taxiCheck);
        taxiPanel.add(taxiButton);
        rightPanel.add(taxiPanel);

        rightPanel.add(groupArea);
        groupPanel.add(groupCheck);
        groupPanel.add(groupButton);
        rightPanel.add(groupPanel);

        split1.add(rightPanel);

    }

    private void addWindow(){

        JPanel windowPanel = new JPanel(new GridLayout(2,1));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(backgroundColor);

        JButton breakButton = new JButton("Break");
        JButton graphButton = new JButton("Show graph");

        breakButton.setBackground(buttonBackgroundColor);
        breakButton.setForeground(buttonForegroundColor);
        breakButton.setEnabled(false);

        graphButton.setBackground(buttonBackgroundColor);
        graphButton.setForeground(buttonForegroundColor);
        graphButton.setEnabled(false);

        int size = windowList.size() + 1;

        breakButton.setName("breakButton" + size);

        breakButton.addActionListener(this);

        JTextArea windowContent = new JTextArea("Ready to start...");
        windowContent.setBackground(textAreaColor);

        windowPanel.setBorder(new EmptyBorder(15,10,0,10));
        windowPanel.setBackground(backgroundColor);
        windowContent.setPreferredSize(new Dimension(150,100));

        windowPanel.add(windowContent);
        buttonPanel.add(breakButton);
        buttonPanel.add(graphButton);
        windowPanel.add(buttonPanel);

        leftPanel.add(windowPanel);
        leftPanel.revalidate();
        leftPanel.repaint();

        windowList.add(windowContent);
        windowPanels.add(windowPanel);
    }

    private void initializeMenuPanel() {

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));

        startButton = new JButton("Start");
        startButton.addActionListener(this);

        startButton.setBackground(buttonBackgroundColor);
        startButton.setForeground(buttonForegroundColor);

        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(this);

        resumeButton.setBackground(buttonBackgroundColor);
        resumeButton.setForeground(buttonForegroundColor);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        exportButton = new JButton("Export");
        exportButton.addActionListener(this);

        stopButton.setBackground(buttonBackgroundColor);
        stopButton.setForeground(buttonForegroundColor);

        exportButton.setBackground(buttonBackgroundColor);
        exportButton.setForeground(buttonForegroundColor);

        startButton.setToolTipText("Start simulation");
        resumeButton.setToolTipText("Resume simulation");
        stopButton.setToolTipText("Stop simulation");
        exportButton.setToolTipText("Print report");

        startButton.setHorizontalAlignment(JLabel.CENTER);
        resumeButton.setHorizontalAlignment(JLabel.CENTER);
        stopButton.setHorizontalAlignment(JLabel.CENTER);
        exportButton.setHorizontalAlignment(JLabel.CENTER);


        /* DISABLING BUTTONS AT FIRST */

        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);
        //exportButton.setEnabled(false);



        /* ************************ */

        menuPanel.add(startButton);
        menuPanel.add(resumeButton);
        menuPanel.add(stopButton);
        menuPanel.add(exportButton);

        menuPanel.setBackground(backgroundColor);

        mainPanel.setBorder(new EmptyBorder(20,5,30,5));

        mainPanel.add(menuPanel, BorderLayout.NORTH);

    }

    public void enableButtonsOnStart(){
        stopButton.setEnabled(true);
        resumeButton.setEnabled(true);
        taxiButton.setEnabled(true);
        groupButton.setEnabled(true);
        groupCheck.setEnabled(true);
        taxiCheck.setEnabled(true);

        /*for(int i = 0; i < windowPanels.size(); i++){
            // currently not access to window buttons to re enable them
        }*/

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent jc = (JComponent) e.getSource();

        if(e.getSource() == startButton){
            LoggerSingleton.getInstance().add("Starting");
            startButton.setEnabled(false);
            md.addObserver(this);
            for (int i = 0; i < md.getWindows().length; i++){
                md.getWindows()[i].addObserver(this);
            }
            md.getTaxiData().getPassengerQueue().addObserver(this);
            md.getTaxiData().getTaxiQueue().addObserver(this);
            md.run();
            enableButtonsOnStart();


        } else if(e.getSource() == stopButton){
            stopButton.setEnabled(false);
            resumeButton.setEnabled(false);
            taxiButton.setEnabled(false);
            groupButton.setEnabled(false);
            groupCheck.setEnabled(false);
            taxiCheck.setEnabled(false);
            md.stopAllWindows();


        } else if(e.getSource() == taxiButton){
            md.getTaxiData().generateAndAddTaxi();
        } else if(e.getSource() == groupButton){
            md.getTaxiData().generateAndAddGroup();
        } else if (e.getSource() == exportButton) {
            LoggerSingleton.getInstance().exportData();
        }
    }

    private void swapButton() {
        if(!isRunning){
            pauseWindow(0);
            startButton.setText("Pause");
            isRunning = true;
        } else if (isRunning) {
            startButton.setText("Start");
            isRunning = false;
        }
    }

    private void pauseWindow(int windowID){
        windowList.get(windowID).setBackground(new Color(245,221,80));
        windowList.get(windowID).setForeground(new Color(0,0,0));
    }

    private void activeWindow(int windowID){
        windowList.get(windowID).setBackground(new Color(146,200,138)); //204.232.202
        windowList.get(windowID).setForeground(new Color(0,0,0));
    }

    private void stopWindow(int windowID){
        windowList.get(windowID).setBackground(new Color(240,101,96));
        windowList.get(windowID).setForeground(new Color(75,0,0));
    }

    private void runningWindow(int windowID){
        windowList.get(windowID).setBackground(textAreaColor);
        windowList.get(windowID).setForeground(new Color(0,0,0));
    }

    private void updateContent(int windowID, String content){
        windowList.get(windowID).setText(content);
    }

    private void changeStateAll(String state){

        if(state.equals("PAUSE"))
            for(int j =0; j<windowList.size(); j++)
                pauseWindow(j);
        else if(state.equals("RUNNING"))
            for(int j =0; j<windowList.size(); j++)
                runningWindow(j);
        else if(state.equals("STOP"))
            for(int j =0; j<windowList.size(); j++)
                stopWindow(j);
        else if(state.equals("ACTIVE"))
            for(int j =0; j<windowList.size(); j++)
                activeWindow(j);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        updateWindows();
        updateTaxiQueue();
        updateGroupsQueue();
        //System.err.println(md.getWindows()[0].getGroupOfPassengers().getDestinationName());
        //updateContent(0, md.getWindows()[0].getGroupOfPassengers().getDestinationName());
    }

    private void updateWindows() {
        for(int i =0; i<windowList.size(); i++){
            String newContent = "WINDOW " + i + "\n" + "Destination: "
                    + (md.getWindows()[i].getGroupOfPassengers() == null ? "" : md.getWindows()[i].getGroupOfPassengers().getDestinationName())
                    + "\nTotal number of passengers : " + (md.getWindows()[i].getGroupOfPassengers() == null ? "" : md.getWindows()[i].getGroupOfPassengers().getNumberOfPassengers())
                    + "\nRemaining number of passengers : " + md.getWindows()[i].getRemainingNumberOfPassengers()
                    + "\nTaxi : " + (md.getWindows()[i].getTaxi() == null ? "" : md.getWindows()[i].getTaxi().getTaxiRegistrationNumber());
            updateContent(i, newContent);

        }
        System.out.println("UPDATE!!!");
    }

    private void updateTaxiQueue(){
        String res = "";

        if (md.getTaxiData().getTaxiQueue().getTaxisQueue().size() > 0) {
            for (int i = 0; i < md.getTaxiData().getTaxiQueue().getTaxisQueue().size();i++){
                res += md.getTaxiData().getTaxiQueue().getTaxisQueue().get(i).getTaxiRegistrationNumber() + "\n";
            }
        } else {
            res = "empty";
        }

        taxiArea.setText(res);
    }

    private void updateGroupsQueue(){
        String res = "";
        if (md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().size() > 0) {
            for (int i = 0; i < md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().size();i++) {
                res += md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().get(i).getNumberOfPassengers() + " people for "
                        + md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().get(i).getDestinationName() + "\n";
            }
        } else {
            res = "empty";
        }
        groupArea.setText(res);
    }

    public MainModel getMd() {
        return md;
    }

    public void setMd(MainModel md) {
        this.md = md;
    }

    public void temp(){
        //destination
        md.getWindows()[0].getGroupOfPassengers().getDestinationName();
        md.getWindows()[0].getRemainingNumberOfPassengers();
        md.getWindows()[0].getTaxi().getTaxiRegistrationNumber();

        md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().size();
        md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().get(0).getDestinationName();
        md.getTaxiData().getPassengerQueue().getGroupOfPassengersQueue().get(0).getNumberOfPassengers();

        md.getTaxiData().getTaxiQueue().getTaxisQueue().size();
        md.getTaxiData().getTaxiQueue().getTaxisQueue().get(0).getTaxiRegistrationNumber();

    }
}