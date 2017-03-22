package main.controller;

import main.model.GroupOfPassengersGenerator;
import main.model.MainModel;
import main.view.InitializationWindowView;
import main.view.SimulationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jules
 */
public class InitController {
    InitializationWindowView initializationWindowView;
    MainModel mainModel;

    public InitController(InitializationWindowView initializationWindowView, MainModel mainModel) {
        this.initializationWindowView = initializationWindowView;
        this.mainModel = mainModel;
        initializationWindowView.addStartButtonListener(new StartButtonListener());
        initializationWindowView.addExitButtonListener(new StartButtonListener());

    }


    class StartButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object temp = e.getSource();

            if(temp == initializationWindowView.getStartButton()) {

                // getting settings values
                initializationWindowView.getValues();

                // binding main model
                mainModel = initializationWindowView.getMm();

                // creating the simulation view
                SimulationView sm = new SimulationView(mainModel);
                sm.initializeComponents();
                sm.createWindows();
                // creating the simulation controller
                SimulationController sc = new SimulationController(mainModel, sm);
                initializationWindowView.getInitializationFrame().setVisible(false);


            }
            else if(temp == initializationWindowView.getExitButton())
                System.exit(0);
        }
    }
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
