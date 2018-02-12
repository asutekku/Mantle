package mantle.controllers;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

public class MantleController {

    @FXML
    BorderPane BProot;

    @FXML
    MenuBar menubar;

    @FXML
    public void initialize() {

        String macMenu = System.getProperty("apple.laf.useScreenMenuBar");

        if (macMenu != null && macMenu == "true") {
            //BProot.getChildren().remove(menubar);
        }
    }

    /**
     * Menu buttons
     */

    @FXML
    public void menuActionNew(){
        eventHandler.error("This menu is not working yet.");
    }

    @FXML
    public void menuActionOpen(){
        eventHandler.error("This menu is not working yet.");
    }

    @FXML
    public void menuActionSave(){
        eventHandler.error("This menu is not working yet.");
    }

    @FXML
    public void menuActionSaveAs(){
        eventHandler.error("This menu is not working yet.");
    }

    @FXML
    public void menuActionAbout(ActionEvent event){
        eventHandler.OpenNewWindow(event, "about", "Mantle - About", false);
    }

    @FXML
    public void menuActionClose() {
        eventHandler.error("This menu is not working yet.");
    }

    @FXML
    public void menuActionQuit() {
        Platform.exit();
    }

    @FXML
    private void importButtonAction(ActionEvent event) {
        eventHandler.error("This button does nothing.");
    }

    @FXML
    private void newButtonAction(ActionEvent event) {
        eventHandler.error("This button does nothing.");
    }

    @FXML
    private void editButtonAction(ActionEvent event) {
        eventHandler.error("This button does nothing.");
    }


}
