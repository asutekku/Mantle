package mantle.util.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.*;
import javafx.scene.layout.Pane;
import mantle.util.Localization;
import mantle.util.preferences.PreferenceLoader;

import java.util.ResourceBundle;

/**
 * Eventhandler class
 * <p>
 * Handles basic functionality of the application
 *
 * @Author Aku Mäkelä
 */
@SuppressWarnings("unused")
public class eventHandler {

    private static ResourceBundle bundle = PreferenceLoader.getLanguageBundle();

    /**
     * This method allows to return a customizable error string
     *
     * @param Message The string to be shown in the error message
     */
    public static void error(String Message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }

    /**
     * @param grid Pane to toggle visibility
     */
    public static void changeVisibility(Pane grid) {
        for (Node child : grid.getChildren()) {
            child.setVisible(!child.isVisible());
        }
    }

    /**
     * Closes the stage method is called from
     *
     * @param event The event to determinate which stage to close
     */
    public static void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Saves data to file
     * Well obviously not yet
     *
     * @param Message The message to show in a string form
     */
    public static void saveData(String Message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }

    /**
     * This method is used to call, when one wants to change scene without changing the stage
     * Bundle is the localization bundle specified in the preferenceloader
     *
     * @param event     Event to determinate which scene to to replace
     * @param sceneName Part of the scene's filename without fxml extension.
     */
    public static void changeScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(eventHandler.class.getClassLoader().getResource("mantle/resources/scenes/" + sceneName + ".fxml"), bundle);
            Scene newScene = new Scene(root);
            Stage originalScene = (Stage) ((Node) event.getSource()).getScene().getWindow();
            originalScene.setScene(newScene);
        } catch (Exception e) {
            error("The path to the fmxl-file is wrong");
            e.printStackTrace();
        }
    }

    /**
     * Opens new window when called from
     *
     * @param event         Used to determinate the stage
     * @param windowName    Part of the filename without .fxml extensions
     * @param windowTitle   Name for the window to be displayed in the title bar
     * @param hideOldWindow True or false depending if you want to hide the old scene.
     */
    public static void OpenNewWindow(ActionEvent event, String windowName, String windowTitle, boolean hideOldWindow) {
        Parent root;
        try {
            root = FXMLLoader.load(eventHandler.class.getClassLoader().getResource("mantle/resources/scenes/" + windowName + ".fxml"), bundle);
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
            if (hideOldWindow) ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            error("Failed to create a new window");
            e.printStackTrace();
        }

    }


}
