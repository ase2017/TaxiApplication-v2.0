package main.view;

import main.model.MainModel;
import main.model.WindowStatuses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Class Name: SimulationView.java
 *
 * Description: This class is the view of the MVC Pattern for the Simulation
 *              mode. This view is creating the Graphical User Interface for
 *              the simulation mode.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class SimulationView implements Observer{

    private JFrame mainFrame = new JFrame(); //The simulation frame
    private JPanel mainPanel,menuPanel,simulationPanel; //The panels for the menu and the simulation
    private RightPanel rightPanel; //The panel of the right side
    private LeftPanel leftPanel; //The panel of the left side
    private JButton startButton, stopButton, exportButton; //The buttons in the main menu
    private JSplitPane split1; //The SplitPane view for the left and the right panel

    /* Colors for the user interface */

    public static Color backgroundColor = new Color(217,217,217);
    public static Color textAreaColor = new Color(255,255,253);
    public static Color buttonBackgroundColor = new Color(44,62,80);
    public static Color buttonForegroundColor = new Color(255,255,255);

    private MainModel md; //An instance of the main model

    /**
     * This is the constructor of this class. It gets as a parameter the
     * main model.
     *
     * @param md The main model
     */
    public SimulationView(MainModel md){

        this.md = md;
        initializeComponents(); //Call this method to initialize the content of the frame
    }

    /**
     * This method is calling the method that initializes the main frame.
     */
    private void initializeComponents(){
        initializeMainFrame();
    }

    /**
     * This method is initializing the structure of the main frame.
     */
    private void initializeMainFrame() {

        mainFrame.setTitle("SimulationView - TaxiApplication v2.0"); //Give a title in the main window
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set close operation (exit app on close)
        mainFrame.setSize(1000,800); //Set fixed dimensions
        mainFrame.setLocationRelativeTo(null); //Center the window to the screen
        initializeMainPanel(); //Call the method that initializes the main panel
        initializeFrame(); // Call the method that add the created components to the frame
        mainFrame.setContentPane(mainPanel); //Set content of the frame
        mainFrame.setResizable(false); //Disable resizing
        mainFrame.setVisible(true); //Show frame

    }

    /**
     * Add the panel that was created into the frame.
     */
    private void initializeFrame() {
        mainFrame.add(mainPanel);
    }

    /**
     * This method initializes the main panel and calls the methods that
     * insert components into it.
     */
    private void initializeMainPanel(){

        mainPanel = new JPanel(new BorderLayout());

        initializeMenuPanel();
        initializeSimulationPanel();

    }

    /**
     * This method creates the basic structure of the simulation
     * panel that is under the main menu.
     */
    private void initializeSimulationPanel() {

        simulationPanel = new JPanel(new GridLayout()); //Use GridLayout with default parameters
        simulationPanel.setBackground(backgroundColor); //Change the color of this panel

        split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT); //Create the SplitPane for the right and left panel

        initializeLeftPanel(); //Call the method that initializes the left panel
        initializeRightPanel(); //Call the method that initializes the right panel

        split1.setResizeWeight(0.7); //Set fixed dimensions for the splits.
        simulationPanel.add(split1); //Add the split view to the simulation panel

        mainPanel.add(simulationPanel, BorderLayout.CENTER); //Add the simulation panel to center side of the main panel
        mainPanel.setBackground(backgroundColor); //Change the main panel's background color
    }

    /**
     * This method creates the left panel of the simulation panel by
     * creating a new Object of the class {@link LeftPanel}, adds a
     * scrollbar around it and add it in the split view.
     */
    private void initializeLeftPanel() {

        leftPanel = new LeftPanel(this.md.getWindows().length);
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        split1.add(scrollPane);
    }

    /**
     * This method fills the right panel of the simulation panel by
     * creating a new Object of the class {@link RightPanel} and adds
     * it to the split view.
     */
    private void initializeRightPanel() {

        rightPanel = new RightPanel();
        split1.add(rightPanel);

    }

    /**
     * This method is initializing the components of the main
     * menu panel.
     */
    private void initializeMenuPanel() {

        menuPanel = new JPanel(); //Create a new panel for the components
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS)); //Use horizontal BoxLayout

        /* Create buttons, add labels, change background and text color */
        startButton = new JButton("Start");
        startButton.setBackground(buttonBackgroundColor);
        startButton.setForeground(buttonForegroundColor);

        stopButton = new JButton("Stop");
        exportButton = new JButton("Export");

        stopButton.setBackground(buttonBackgroundColor);
        stopButton.setForeground(buttonForegroundColor);

        exportButton.setBackground(buttonBackgroundColor);
        exportButton.setForeground(buttonForegroundColor);

        /* Add tooltip descriptions so that the user understands their purpose */
        startButton.setToolTipText("Start simulation");
        stopButton.setToolTipText("Stop simulation");
        exportButton.setToolTipText("Print report");

        /* Align the button to the center */
        startButton.setHorizontalAlignment(JLabel.CENTER);
        stopButton.setHorizontalAlignment(JLabel.CENTER);
        exportButton.setHorizontalAlignment(JLabel.CENTER);

        stopButton.setEnabled(false); //Disable stop button at the beginning

        /* Add the buttons into the menu panel */
        menuPanel.add(startButton);
        menuPanel.add(stopButton);
        menuPanel.add(exportButton);

        /* Change the background color of the panel, add margin around it and position it at the north side */
        menuPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20,5,30,5));
        mainPanel.add(menuPanel, BorderLayout.NORTH);

    }

    /**
     * This method enables all the buttons at the beginning.
     */
    public void enableButtonsOnStart(){
        stopButton.setEnabled(true);
        this.getRightPanel().getTaxiPanel().getSubButton().setEnabled(true);
        this.getRightPanel().getGroupPanel().getSubButton().setEnabled(true);
        this.getRightPanel().getTaxiPanel().getSubCheck().setEnabled(true);
        this.getRightPanel().getGroupPanel().getSubCheck().setEnabled(true);

        for(int i =0; i< this.md.getWindows().length; i++) {
            this.getLeftPanel().getWindows().get(i).getEndOfDayButton().setEnabled(true);
            this.getLeftPanel().getWindows().get(i).getGraphButton().setEnabled(true);
        }
    }

    /**
     * This method disables all the buttons at the end.
     */
    public void disableButtonsOnStop(){

        stopButton.setEnabled(false);
        this.getRightPanel().getTaxiPanel().getSubButton().setEnabled(false);
        this.getRightPanel().getGroupPanel().getSubButton().setEnabled(false);
        this.getRightPanel().getTaxiPanel().getSubCheck().setEnabled(false);
        this.getRightPanel().getGroupPanel().getSubCheck().setEnabled(false);

        for(int i =0; i< this.md.getWindows().length; i++) {
            this.getLeftPanel().getWindows().get(i).getEndOfDayButton().setEnabled(false);
        }
    }

    /**
     * This method is used to add actionListeners to the buttons.
     *
     * @param actionListener ActionListener
     */
    public void addListeners(ActionListener actionListener){

        startButton.addActionListener(actionListener);
        stopButton.addActionListener(actionListener);
        this.getRightPanel().getTaxiPanel().getSubButton().addActionListener(actionListener);
        this.getRightPanel().getTaxiPanel().getSubCheck().addActionListener(actionListener);
        this.getRightPanel().getGroupPanel().getSubButton().addActionListener(actionListener);
        this.getRightPanel().getGroupPanel().getSubCheck().addActionListener(actionListener);

        for(int i =0; i< this.md.getWindows().length; i++) {
            this.getLeftPanel().getWindows().get(i).getEndOfDayButton().addActionListener(actionListener);
            this.getLeftPanel().getWindows().get(i).getGraphButton().addActionListener(actionListener);
        }


        exportButton.addActionListener(actionListener);

    }

    /**
     * This method is changing the current state of a given window to busy.
     *
     * @param windowID The window's identifier
     */
    private void busyWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(80,200,240));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    /**
     * This method is changing the current state of a given window to paused.
     *
     * @param windowID The window's identifier
     */
    private void pauseWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(245,221,80));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    /**
     * This method is changing the current state of a given window to available.
     *
     * @param windowID The window's identifier
     */
    private void availableWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(146,200,138)); //204.232.202
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(0,0,0));
    }

    /**
     * This method is changing the current state of a given window to stopped.
     *
     * @param windowID The window's identifier
     */
    private void stopWindow(int windowID){
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setBackground(new Color(240,101,96));
         this.getLeftPanel().getWindows().get(windowID).getWindowContent().setForeground(new Color(75,0,0));
    }

    /**
     * This method is used to update the content of a textarea of a window in the
     * left panel.
     *
     * @param windowID The window's identifier
     * @param content The new content
     */
    private void updateContent(int windowID, String content){
        this.getLeftPanel().getWindows().get(windowID).getWindowContent().setText(content);
    }

    /**
     * This method is triggered whenever the observer is notified
     * for changes. Then, it triggers the methods that are updating the
     * content of each TextArea in the simulation panel.
     *
     * @param o Observable
     * @param arg Arguments
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
        updateWindows();
        updateTaxiQueue();
        updateGroupsQueue();
    }

    /**
     * This method is updating the state and the textarea of the windows
     * in the left panel of the simulation view.
     */
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
    }

    /**
     * This method is checking the current state of a given window and calls
     * the appropriate methods to change its background color.
     *
     * @param i The window's identifier
     */
    private void updateWindowColor(int i){

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

    /**
     * This method is updating the content of the TextArea of the
     * taxi queue that is in the right panel.
     */
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

    /**
     * This method is updating the content of the TextArea of the
     * group queue that is in the right panel.
     */
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


    /* Getters and Setters */

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

}
