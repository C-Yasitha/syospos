package com.brylix.derp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load the Login.fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/com/brylix/derp/view/Login.fxml"));

        // Create a scene
        Scene scene = new Scene(root);

        // Set the scene onto the stage
        primaryStage.setScene(scene);

        // Calculate the desired size for the stage
        double desiredWidth = root.prefWidth(-1);
        double desiredHeight = root.prefHeight(desiredWidth);

        // Set the size of the stage
        primaryStage.setWidth(desiredWidth);
        primaryStage.setHeight(desiredHeight);

        // Display the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}