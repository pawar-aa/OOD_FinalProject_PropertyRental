package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import utility.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    @FXML
    private void loginAdmin(ActionEvent event) {

        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        boolean isValid = checkCredentials(enteredUsername, enteredPassword);

        if (isValid) {
            showAlert("Login Successful", "Welcome, " + enteredUsername + "!");
        } else {
            showAlert(" Login Failed",enteredUsername + " Invalid username or password.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.connectDb();
            String query = "SELECT * FROM users WHERE username =" + username + " and password = " + password;
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return resultSet.next(); // If there's a row, credentials are valid
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    private void close(ActionEvent event) {
        // Add your code to close the application or perform other actions
    	System.exit(0);
    }
}
