package mantle.util.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.event.*;

public class SplashController {

    @FXML
    BorderPane splashRoot;
    @FXML
    TextField splashInputField;

    /**
     * Displays the about window
     *
     * @param event
     */
    @FXML
    public void splashAboutButtonAction(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "about", "Mantle - About", false);
    }

    @FXML
    public void splashCreateButtonAction(ActionEvent event) {
        String input = getName();
        eventHandler.OpenNewWindow(event, "mantle", "Mantle - " + input, false);
    }

    /**
     * This method is called when user presses Skip button in the
     * splash screen. Splash screen is hidden and new empty mantle screen is opened.
     *
     * @param event This checks the root of the event caller
     */
    @FXML
    public void splashSkipButtonAction(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "mantle", "Mantle", false);
        eventHandler.closeStage(event);
    }

    @FXML
    public void aboutButtonClose(ActionEvent event) {
        eventHandler.closeStage(event);
    }

    @FXML
    public String getName() {
        return splashInputField.getText();
        //return "";
    }
}