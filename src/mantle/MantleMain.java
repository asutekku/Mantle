package mantle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import mantle.collection.Collection;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

import mantle.collection.Collection;
import mantle.controllers.MantleController;
import mantle.controllers.SplashController;

public class MantleMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/splash.fxml"));
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