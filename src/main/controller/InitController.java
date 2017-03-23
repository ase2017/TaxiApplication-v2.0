package main.controller;

import main.model.MainModel;
import main.view.InitializationWindowView;
import main.view.SimulationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Name: InitController.java
 *
 * Description: This class represents the controller of the main
 *              application GUI.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class InitController {

    InitializationWindowView initializationWindowView; //The Main Graphical User Interface
    MainModel mainModel; //The main model

    /**
     * This method is the constructor of this class. It creates a new
     * InitiController Object.
     *
     * @param initializationWindowView The initialized view
     * @param mainModel The main model
     */
    public InitController(InitializationWindowView initializationWindowView, MainModel mainModel) {

        this.initializationWindowView = initializationWindowView;
        this.mainModel = mainModel;

        initializationWindowView.addStartButtonListener(new StartButtonListener()); //Add new event listener to the start button
        initializationWindowView.addExitButtonListener(new StartButtonListener()); //Add new event listener to the exit button

    }

    /**
     * Following the MVC pattern, this is the class that includes the actionPerformed method
     * for the application buttons.
     */
    class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JComponent temp = (JComponent) e.getSource(); //The component that triggered this

            /* If the component was the start button */
            if(temp == initializationWindowView.getStartButton()) {

                initializationWindowView.getValues(); //Get the values that the user defined
                mainModel = initializationWindowView.getMm(); //Get the main model from the view
                SimulationView sm = new SimulationView(mainModel); //Create a new simulation view and pass the main model
                SimulationController sc = new SimulationController(mainModel, sm); //Create a new simulation controller and pass the model and the view
                initializationWindowView.getInitializationFrame().setVisible(false); //Hide the main window

            }
            else if(temp == initializationWindowView.getExitButton())
                System.exit(0); //If the exit button was triggered, exit the application
        }
    }

    /* Getters and Setters */

    public InitializationWindowView getInitializationWindowView() {
        return initializationWindowView;
    }

    public void setInitializationWindowView(InitializationWindowView initializationWindowView) {
        this.initializationWindowView = initializationWindowView;
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }
}
