package com.lms.librarymanagementsystem;

import dao.AuthorDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a scene with the loaded FXML
            Scene scene = new Scene(root, 800, 500);

            // Set the title of the window
            primaryStage.setTitle("Library Management System");

            // Set the scene to the stage
            primaryStage.setScene(scene);

            // Show the stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Add logging or error dialog if needed
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
