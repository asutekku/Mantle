package mantle.util.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.event.*;
import mantle.util.Session;

/**
 * Splashcontroller class
 *
 * Handles the controls for the splashscreen and about screen
 */
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

    /**
     * Creates a new collection with name from the inputField
     * Right now just sets the title for the app to match the input
     *
     * @param event
     */
    @FXML
    public void splashCreateButtonAction(ActionEvent event) {
        String input = getName();
        Session.setCollectionName(input);
        eventHandler.OpenNewWindow(event, "mantle", "Mantle - " + input, false);
    }

    /**
     * This method is called when user presses Skip button in the
     * splash screen. Splash screen is closed and new empty main screen is opened.
     *
     * @param event This checks the root of the event caller
     */
    @FXML
    public void splashSkipButtonAction(ActionEvent event) {
        eventHandler.OpenNewWindow(event, "mantle", "Mantle", false);
        eventHandler.closeStage(event);
    }

    /**
     * Closes the application
     *
     * @param event
     */
    @FXML
    public void aboutButtonClose(ActionEvent event) {
        eventHandler.closeStage(event);
    }

    /**
     * @return The name written in the inputfield
     */
    @FXML
    public String getName() {
        return splashInputField.getText();
    }
}