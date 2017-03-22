package main.view;

import main.model.MainModel;
import main.model.WindowStatuses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Created by Giorgos on 19-Mar-17.
 */
public class SimulationView implements Observer{

    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel,menuPanel,simulationPanel;
    private RightPanel rightPanel;
    private LeftPanel leftPanel;
    private JButton startButton, resumeButton, stopButton, exportButton;
    private JSplitPane split1;

    public static Color backgroundColor = new Color(217,217,217);
    public static Color textAreaColor = new Color(255,255,253);
    public static Color buttonBackgroundColor = new Color(44,62,80);
    public static Color buttonForegroundColor = new Color(255,255,255);

    private boolean isRunning = false;

    private MainModel md;


    public SimulationView(MainModel md){

        this.md = md;
        initializeComponents();
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

        leftPanel = new LeftPanel(this.md.getWindows().length);
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        split1.add(scrollPane);
    }

    private void initializeRightPanel() {

        rightPanel = new RightPanel();
        split1.add(rightPanel);

    }

    private void initializeMenuPanel() {

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));

        startButton = new JButton("Start");
        startButton.setBackground(buttonBackgroundColor);
        startButton.setForeground(buttonForegroundColor);

        resumeButton = new JButton("Pause");
        resumeButton.setBackground(buttonBackgroundColor);
        resumeButton.setForeground(buttonForegroundColor);

        stopButton = new JButton("Stop");

        exportButton = new JButton("Export");

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
        this.getRightPanel().getTaxiPanel().getSubButton().setEnabled(true);
        this.getRightPanel().getGroupPanel().getSubButton().setEnabled(true);
        this.getRightPanel().getTaxiPanel().getSubCheck().setEnabled(true);
        this.getRightPanel().getGroupPanel().getSubCheck().setEnabled(true);
    }

    public void disableButtonsOnStop(){
        stopButton.setEnabled(false);
        resumeButton.setEnabled(false);
        this.getRightPanel().getTaxiPanel().getSubButton().setEnabled(false);
        this.getRightPanel().getGroupPanel().getSubButton().setEnabled(false);
        this.getRightPanel().getTaxiPanel().getSubCheck().setEnabled(false);
        this.getRightPanel().getGroupPanel().getSubCheck().setEnabled(false);
    }

    public void addListeners(ActionListener actionListener){

        startButton.addActionListener(actionListener);
        stopButton.addActionListener(actionListener);
        resumeButton.addActionListener(actionListener);
        this.getRightPanel().getTaxiPanel().getSubButton().addActionListener(actionListener);
        this.getRightPanel().getTaxiPanel().getSubCheck().addActionListener(actionListener);
        this.getRightPanel().getGroupPanel().getSubButton().addActionListener(actionListener);
        this.getRightPanel().getGroupPanel().getSubCheck().addActionListener(actionListener);

        for(int i =0; i< this.md.getWindows().length; i++) {
            this.getLeftPanel().getWindows().get(i).getBreakButton().addActionListener(actionListener);
            this.getLeftPanel().getWindows().get(i).getGraphButton().addActionListener(actionListener);
        }


        exportButton.addActionListener(actionListener);

    }

    public void swapButton() {
        if(!isRunning){
            resumeButton.setText("Pause");
            isRunning = true;
        } else if (isRunning) {
            resumeButton.setText("Resume");
            isRunning = false;
        }
    }

    private void busyWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(80,200,240));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    private void pauseWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(245,221,80));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    private void availableWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(146,200,138)); //204.232.202
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    private void stopWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(240,101,96));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(75,0,0));
    }

    private void updateContent(int windowID, String content){
        this.getLeftPanel().getWindows().get(windowID).getWindowContent().setText(content);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        updateWindows();
        updateTaxiQueue();
        updateGroupsQueue();
    }

    private void updateWindows() {
        for(int i =0; i<md.getWindows().length; i++){
            String newContent = "Destination: "
                    + (md.getWindows()[i].getGroupOfPassengers() == null ? "" : md.getWindows()[i].getGroupOfPassengers().getDestinationName())
                    + "\nTotal number of passengers : " + (md.getWindows()[i].getGroupOfPassengers() == null ? "" : md.getWindows()[i].getGroupOfPassengers().getNumberOfPassengers())
                    + "\nRemaining number of passengers : " + md.getWindows()[i].getRemainingNumberOfPassengers()
                    + "\nTaxi : " + (md.getWindows()[i].getTaxi() == null ? "" : md.getWindows()[i].getTaxi().getTaxiRegistrationNumber());
            updateContent(i, newContent);
            updateWindowColor(i);
            this.getLeftPanel().getWindows().get(i ).getWindowContent().revalidate();

        }
        System.out.println("UPDATE!!!");
    }

    private void updateWindowColor(int i){
        //System.out.println("updateWindowColor, Window " + i + " status : " + md.getWindows()[i].getStatus());
        if(md.getWindows()[i].getStatus().equals(WindowStatuses.AVAILABLE.toString())){
            availableWindow(i);
        } else if(md.getWindows()[i].getStatus().equals(WindowStatuses.BREAK.toString())){
            pauseWindow(i);
        } else if(md.getWindows()[i].getStatus().equals(WindowStatuses.BUSY.toString())){
            busyWindow(i);
        } else if(md.getWindows()[i].getStatus().equals(WindowStatuses.UNAVAILABLE.toString())){
            stopWindow(i);
        }

        this.getLeftPanel().getWindows().get(i ).getWindowContent().repaint();
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

        this.getRightPanel().getTaxiPanel().getSubArea().setText(res);
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
        this.getRightPanel().getGroupPanel().getSubArea().setText(res);
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

    public void addListner(JButton jButton, ActionListener actionListener){
        jButton.addActionListener(actionListener);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public void setResumeButton(JButton resumeButton) {
        this.resumeButton = resumeButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public void setExportButton(JButton exportButton) {
        this.exportButton = exportButton;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(RightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }
}
