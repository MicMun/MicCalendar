package de.micmun.java.mickalender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class as starting point.
 *
 * @author MicMun
 * @version 1.1, 16.06.17
 */
public class Main extends Application {
   private static final String VERSION = "1.0";

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("gui/main.fxml"));
      primaryStage.setTitle("MicCalendar " + VERSION);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/assets/days.css")
              .toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
