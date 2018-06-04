package mantle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import mantle.util.Localization;

import java.util.ResourceBundle;

/**
 * Main class for the Mantle
 *
 * In here you define the locale and handle the launch arguments
 *
 */
public class MantleMain extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            //System.setProperty("apple.laf.useScreenMenuBar", "true");
            Localization local = new Localization("en", "US");
            ResourceBundle bundle = local.getLocale();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/scenes/splash.fxml"), bundle);
            final Pane root = loader.load();
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("resources/bootstrap3.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mantle");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Main function
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param newTitle New title for the stage
     */
    public static void setStageTitle(String newTitle) {
        stage.setTitle(newTitle);
    }

}