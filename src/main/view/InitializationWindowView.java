package main.view;

import main.controller.SimulationController;
import main.model.GroupOfPassengersGenerator;
import main.model.MainModel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Class Name: InitializationWindowView.java
 *
 * Description: This class creates the main window of our application.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class InitializationWindowView{

    /* Paths for the images of the app */

    private final String ICON_PATH = "resources/taxiIcon.png";
    private final String START_ICON_PATH = "resources/startButton.png";
    private final String START_ICON_HOVER_PATH = "resources/startButtonHover.png";
    private final String EXIT_ICON_PATH = "resources/exitButton.png";
    private final String EXIT_ICON_HOVER_PATH = "resources/exitButtonHover.png";

    /* Default options for the spinners */

    private static final int DEFAULT_NUM_OF_TAXIS = 10;
    private static final int DEFAULT_NUM_OF_WINDOWS = 2;
    private static final int DEFAULT_NUM_OF_GROUPS = 10;
    private static final int DEFAULT_NUM_OF_PASSENGERS = 5;

    /* Range (min,max) for the spinners */

    private static final int MINIMUM_NUM_OF_TAXIS = 0;
    private static final int MAXIMUM_NUM_OF_TAXIS = 20;
    private static final int MINIMUM_NUM_OF_WINDOWS = 1;
    private static final int MAXIMUM_NUM_OF_WINDOWS = 12;
    private static final int MINIMUM_NUM_OF_GROUPS = 0;
    private static final int MAXIMUM_NUM_OF_GROUPS = 1000;
    private static final int MINIMUM_NUM_OF_PASSENGERS = 1;
    private static final int MAXIMUM_NUM_OF_PASSENGERS = 30;

    private JFrame initializationFrame; //The main frame
    private JPanel initializationPanel, northPanel, southPanel, centerPanel; //The panels which are going to be useds
    private JLabel mainLabel, numOfTaxLabel,numPassGroupsLabel, passPerGroupLabel, numWindowsLabel,
             startButtonLabel, exitButtonLabel; //The labels for the components

    private JButton startButton, exitButton; //The start and exit buttons
    private JSpinner numTaxisSpinner, groupsSpinner, numGroupsSpinner, numWindowsSpinner; //The spinners
    private SimulationController simulationController; //The controller for the next view

    /* Variables in which the user's preferences are going to be stored */

    public static int numberOfTaxis = 0;
    public static int numberOfGroups = 0;
    public static int numberOfWindows = 0;
    public static int maxPassengersPerGroup = 0;

    private MainModel mm; //An instance of the main model

    /**
     * This is the constructor of this class. It creates a new main window.
     *
     * @param mm The main model
     */
    public InitializationWindowView(MainModel mm){

        this.mm = mm;
        initializationFrame = new JFrame();
        initializeComponents();
        initializeView();

    }

    /**
     * This method is calling the methods that initializing the main frame and its components.
     */
    public void initializeView(){

        initializeFrame();
        taxiIconInit();

    }

    /**
     * This method creates the main panel and fills it with the components.
     */
    private void initializeComponents(){

        setButtonImages();
        initializeSpinners();
        initializeHandlers();
        initializeTooltipDescriptions();

    }

    /**
     * This method initializes the main frame of the GUI
     */
    private void initializeFrame(){

        initializationFrame.setTitle("TaxiApplication v2.0");
        initializationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializationFrame.setSize(800,500);
        initializationFrame.setLocationRelativeTo(null);
        initializationFrame.setContentPane(initializationPanel);
        initializationFrame.setResizable(false);
        initializationFrame.setVisible(true);

    }

    /**
     * This method initializes the favicon of the application.
     */
    private void taxiIconInit(){

        try{
            initializationFrame.setIconImage(ImageIO.read(new File(ICON_PATH)));
        }
        catch (Exception e){
            System.out.println("Application icon not found.");
        }

    }

    /**
     * This method initializes the button images. It gives them
     * circle shape and disables the borders.
     */
    private void setButtonImages(){

        startButton.setIcon(new ImageIcon(START_ICON_PATH));
        exitButton.setIcon(new ImageIcon(EXIT_ICON_PATH));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);

    }

    /**
     * This method is using {@link SpinnerModel} to create a range for each spinner.
     * It also prevents the spinners from being edited or modified in any way.
     */
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

    /**
     * This method initializes some mouse listeners for the image buttons in order to
     * make them look like real buttons. When users put their mouse on the button we
     * use a version of the image with weaker color. When he removes the mouse from the
     * button we use a more vibrant version of the same picture.
     */
    private void initializeHandlers(){

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

    /**
     * This method is used to collect the user's preferences from the spinners
     * and send them to the Model.
     */
    public void getValues(){

        numberOfTaxis = (Integer)numTaxisSpinner.getValue();
        maxPassengersPerGroup = (Integer)numGroupsSpinner.getValue();
        numberOfWindows = (Integer)numWindowsSpinner.getValue();
        numberOfGroups = (Integer)groupsSpinner.getValue();

        mm = new MainModel(numberOfTaxis, numberOfGroups, numberOfWindows);
        GroupOfPassengersGenerator.MAX_NUMBER_OF_PEOPLE_IN_GROUP = maxPassengersPerGroup;

    }

    /**
     * This method initializes the tooltip descriptions for all the spinners.
     * In this way, the user can hover their mouse on the spinners to get extra
     * details about what these parameters actually do.
     */
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

    /**
     * This method adds the actionListener of the start button.
     * @param al ActionListener
     */
    public void addStartButtonListener (ActionListener al){
        startButton.addActionListener(al);
    }

    /**
     * This method adds the actionListener of the exit button.
     * @param al ActionListener
     */
    public void addExitButtonListener (ActionListener al){
        exitButton.addActionListener(al);
    }


    /* Getters and Setters */

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
