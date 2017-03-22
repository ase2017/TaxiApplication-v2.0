package main.view;

import main.controller.SimulationController;
import main.model.GroupOfPassengersGenerator;
import main.model.MainModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;


public class InitializationWindowView{

    private final String ICON_PATH = "resources/taxiIcon.png";
    private final String START_ICON_PATH = "resources/startButton.png";
    private final String START_ICON_HOVER_PATH = "resources/startButtonHover.png";
    private final String EXIT_ICON_PATH = "resources/exitButton.png";
    private final String EXIT_ICON_HOVER_PATH = "resources/exitButtonHover.png";

    private static final int DEFAULT_NUM_OF_TAXIS = 10;
    private static final int DEFAULT_NUM_OF_WINDOWS = 2;
    private static final int DEFAULT_NUM_OF_GROUPS = 10;
    private static final int DEFAULT_NUM_OF_PASSENGERS = 5;

    private static final int MINIMUM_NUM_OF_TAXIS = 0;
    private static final int MAXIMUM_NUM_OF_TAXIS = 20;
    private static final int MINIMUM_NUM_OF_WINDOWS = 1;
    private static final int MAXIMUM_NUM_OF_WINDOWS = 12;
    private static final int MINIMUM_NUM_OF_GROUPS = 0;
    private static final int MAXIMUM_NUM_OF_GROUPS = 1000;
    private static final int MINIMUM_NUM_OF_PASSENGERS = 1;
    private static final int MAXIMUM_NUM_OF_PASSENGERS = 30;

    private JFrame initializationFrame;
    private JPanel initializationPanel, northPanel, southPanel, centerPanel;
    private JLabel mainLabel, numOfTaxLabel,numPassGroupsLabel, passPerGroupLabel, numWindowsLabel,
             startButtonLabel, exitButtonLabel;

    private JButton startButton, exitButton;
    private JSpinner numTaxisSpinner, groupsSpinner, numGroupsSpinner, numWindowsSpinner;
    private SimulationController simulationController;


    public static int numberOfTaxis = 0;
    public static int numberOfGroups = 0;
    public static int numberOfWindows = 0;
    public static int maxPassengersPerGroup = 0;

    private MainModel mm;

    public InitializationWindowView(MainModel mm){

        this.mm = mm;
        initializationFrame = new JFrame();
        initializeComponents();
        initializeView();

    }

    public void initializeView(){

        initializeFrame();
        taxiIconInit();

    }

    private void initializeComponents(){

        setButtonImages();
        initializeSpinners();
        initializeHandlers();
        initializeTooltipDescriptions();

    }

    private void initializeFrame(){

        initializationFrame.setTitle("TaxiApplication v2.0");
        initializationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializationFrame.setSize(800,500);
        initializationFrame.setLocationRelativeTo(null);
        initializationFrame.setContentPane(initializationPanel);
        initializationFrame.setResizable(false);
        initializationFrame.setVisible(true);

    }

    private void taxiIconInit(){

        try{
            initializationFrame.setIconImage(ImageIO.read(new File(ICON_PATH)));
        }
        catch (Exception e){
            System.out.println("Application icon not found.");
        }

    }

    private void setButtonImages(){

        startButton.setIcon(new ImageIcon(START_ICON_PATH));
        exitButton.setIcon(new ImageIcon(EXIT_ICON_PATH));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);

    }

    private void initializeSpinners(){

        SpinnerModel numOfTaxisModel = new SpinnerNumberModel(DEFAULT_NUM_OF_TAXIS, MINIMUM_NUM_OF_TAXIS,
                MAXIMUM_NUM_OF_TAXIS, 1);
        SpinnerModel numGroupsModel = new SpinnerNumberModel(DEFAULT_NUM_OF_GROUPS, MINIMUM_NUM_OF_GROUPS,
                MAXIMUM_NUM_OF_GROUPS, 1);
        SpinnerModel passengerModel = new SpinnerNumberModel(DEFAULT_NUM_OF_PASSENGERS, MINIMUM_NUM_OF_PASSENGERS,
                MAXIMUM_NUM_OF_PASSENGERS, 1);
        SpinnerModel numWindowsModel = new SpinnerNumberModel(DEFAULT_NUM_OF_WINDOWS, MINIMUM_NUM_OF_WINDOWS,
                MAXIMUM_NUM_OF_WINDOWS, 1);

        numTaxisSpinner.setModel(numOfTaxisModel);
        numGroupsSpinner.setModel(numGroupsModel);
        groupsSpinner.setModel(passengerModel);
        numWindowsSpinner.setModel(numWindowsModel);

        ((JSpinner.DefaultEditor) numTaxisSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) numGroupsSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) groupsSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) numWindowsSpinner.getEditor()).getTextField().setEditable(false);
    }

    private void initializeHandlers(){

        //startButton.addActionListener(this);
        //exitButton.addActionListener(this);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(new ImageIcon(START_ICON_HOVER_PATH));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(new ImageIcon(START_ICON_PATH));
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(EXIT_ICON_HOVER_PATH));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(EXIT_ICON_PATH));
            }
        });

    }

    public void getValues(){

        numberOfTaxis = (Integer)numTaxisSpinner.getValue();
        maxPassengersPerGroup = (Integer)numGroupsSpinner.getValue();
        numberOfWindows = (Integer)numWindowsSpinner.getValue();
        numberOfGroups = (Integer)groupsSpinner.getValue();

        mm = new MainModel(numberOfTaxis, numberOfGroups, numberOfWindows);
        GroupOfPassengersGenerator.MAX_NUMBER_OF_PEOPLE_IN_GROUP = maxPassengersPerGroup;

    }
    public void addStartButtonListener (ActionListener al){
        startButton.addActionListener(al);
    }

    public void addExitButtonListener (ActionListener al){
        exitButton.addActionListener(al);
    }




    private void initializeTooltipDescriptions(){

        numTaxisSpinner.setToolTipText("The number of taxis that are available at any time. It should be a number" +
                " in the range [" + MINIMUM_NUM_OF_TAXIS + "-" + MAXIMUM_NUM_OF_TAXIS + "].");
        groupsSpinner.setToolTipText("The number of customer groups. It should be a number" +
                " in the range [" + MINIMUM_NUM_OF_GROUPS + "-" + MAXIMUM_NUM_OF_GROUPS + "].");
        numGroupsSpinner.setToolTipText("The maximum number of passengers per group. It should be a number" +
                " in the range [" + MINIMUM_NUM_OF_PASSENGERS + "-" + MAXIMUM_NUM_OF_PASSENGERS + "].");
        numWindowsSpinner.setToolTipText("The number of available windows. It should be a number" +
                " in the range [" + MINIMUM_NUM_OF_WINDOWS + "-" + MAXIMUM_NUM_OF_WINDOWS + "].");
    }

    public static int getNumberOfTaxis() {
        return numberOfTaxis;
    }

    public static int getNumberOfGroups() {
        return numberOfGroups;
    }

    public static int getNumberOfWindows() {
        return numberOfWindows;
    }

    public static int getMaxPassengersPerGroup() {
        return maxPassengersPerGroup;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JFrame getInitializationFrame() {
        return initializationFrame;
    }

    public void setInitializationFrame(JFrame initializationFrame) {
        this.initializationFrame = initializationFrame;
    }


    public static void setMaxPassengersPerGroup(int maxPassengersPerGroup) {
        InitializationWindowView.maxPassengersPerGroup = maxPassengersPerGroup;
    }

    public MainModel getMm() {
        return mm;
    }

    public void setMm(MainModel mm) {
        this.mm = mm;
    }
}
