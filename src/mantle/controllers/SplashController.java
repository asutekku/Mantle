package mantle.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.event.*;

public class SplashController {

    @FXML
    BorderPane splashRoot;

    /**
     * Displays the about window
     * @param event
     */
    @FXML
    public void splashAboutButtonAction(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "about", "Mantle - About", false);
    }

    @FXML
    public void splashCreateButtonAction() {
        eventHandler.error("This button does nothing.");
    }


    /**
     * This method is called when user presses Skip button in the
     * splash screen. Splash screen is hidden and new empty mantle screen is opened.
     * @param event This checks the root of the event caller
     */
    @FXML
    public void splashSkipButtonAction(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "mantle", "Mantle", true);
    }

    @FXML
    public void aboutButtonClose(ActionEvent event){
        eventHandler.closeStage(event);
    }

}