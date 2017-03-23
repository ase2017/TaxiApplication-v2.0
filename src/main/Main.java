package main;

import main.controller.InitController;
import main.model.MainModel;
import main.view.InitializationWindowView;

/*

$$$$$$$$\                  $$\        $$$$$$\                      $$\ $$\                     $$\     $$\
\__$$  __|                 \__|      $$  __$$\                     $$ |\__|                    $$ |    \__|
   $$ | $$$$$$\  $$\   $$\ $$\       $$ /  $$ | $$$$$$\   $$$$$$\  $$ |$$\  $$$$$$$\ $$$$$$\ $$$$$$\   $$\  $$$$$$\  $$$$$$$\
   $$ | \____$$\ \$$\ $$  |$$ |      $$$$$$$$ |$$  __$$\ $$  __$$\ $$ |$$ |$$  _____|\____$$\\_$$  _|  $$ |$$  __$$\ $$  __$$\
   $$ | $$$$$$$ | \$$$$  / $$ |      $$  __$$ |$$ /  $$ |$$ /  $$ |$$ |$$ |$$ /      $$$$$$$ | $$ |    $$ |$$ /  $$ |$$ |  $$ |
   $$ |$$  __$$ | $$  $$<  $$ |      $$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |$$ |$$ |     $$  __$$ | $$ |$$\ $$ |$$ |  $$ |$$ |  $$ |
   $$ |\$$$$$$$ |$$  /\$$\ $$ |      $$ |  $$ |$$$$$$$  |$$$$$$$  |$$ |$$ |\$$$$$$$\\$$$$$$$ | \$$$$  |$$ |\$$$$$$  |$$ |  $$ |
   \__| \_______|\__/  \__|\__|      \__|  \__|$$  ____/ $$  ____/ \__|\__| \_______|\_______|  \____/ \__| \______/ \__|  \__|
                                               $$ |      $$ |
                                               $$ |      $$ |
                                               \__|      \__|

        George Goniotakis
        George Chiotis
        Jules Detrie
 */

public class Main {

    public static void main(String[] args) {

        MainModel mainModel = new MainModel();
        InitializationWindowView view = new InitializationWindowView(mainModel);
        InitController initController = new InitController(view,mainModel);

    }
}
