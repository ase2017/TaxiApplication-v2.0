package main;


import main.controller.InitController;
import main.controller.SimulationController;
import main.model.MainModel;
import main.view.InitializationWindowView;
import main.view.SimulationView;

public class Main {



    public static void main(String[] args) {

        MainModel mainModel = new MainModel();
        InitializationWindowView view = new InitializationWindowView(mainModel);
        InitController initController = new InitController(view,mainModel);

    }
}
