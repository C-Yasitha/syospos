package com.brylix.derp.controller;

import com.brylix.derp.dao.UserDAO;
import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.factory.UserDAOFactory;
import com.brylix.derp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;
    private UserDAO userDAO;

    public LoginController() {
        userDAO = UserDAOFactory.getUserDAO();
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = Login(username,password);

        if (isAuthenticated) {
            // Successful login, proceed to the next screen or perform other actions
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");

            // Load the main screen
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/brylix/derp/view/Main.fxml"));
                Parent root = loader.load();

                double desiredWidth = root.prefWidth(-1);
                double desiredHeight = root.prefHeight(desiredWidth);

                Stage mainStage = new Stage();
                // Set the size of the stage
                mainStage.setWidth(desiredWidth);
                mainStage.setHeight(desiredHeight);
                // Create a scene
                Scene scene = new Scene(root);

                // Set the scene onto the stage
                mainStage.setScene(scene);
                mainStage.setTitle("Main Screen");
                // Show the main screen
                mainStage.show();

                // Close the login screen
                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception according to your application's error handling mechanism
            }
        } else {
            // Invalid credentials, show an error message
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    public boolean Login(String username,String password){
        // Authenticate the user
        UserAuthDTO enterdUser = new UserAuthDTO(username,password);
        try {
            boolean isAuthenticated = userDAO.authenticateUser(enterdUser);
            return isAuthenticated;
        }catch(Exception e){
            showAlert(Alert.AlertType.ERROR, "Login Failed", e.getMessage());
            return false;
        }
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        // Exit the application
        System.exit(0);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
