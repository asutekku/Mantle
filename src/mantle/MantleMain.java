package mantle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import mantle.util.Localization;

import java.util.ResourceBundle;

public class MantleMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            Localization local = new Localization("fi", "FI");
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

    public static void main(String[] args) {
        launch(args);
    }
}