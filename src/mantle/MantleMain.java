package mantle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MantleMain extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Mantle");
            System.setProperty("apple.laf.useScreenMenuBar", "true");


            FXMLLoader splashScreen = new FXMLLoader( this.getClass().getResource("scenes/splash.fxml"));
            splashScreen.load();

            Parent root = splashScreen.getRoot();

            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("rsc/bootstrap3.css").toExternalForm());

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}