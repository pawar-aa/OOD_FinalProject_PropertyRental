package application;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostProperty {

    private ObservableList<Property> properties;
    private Stage primaryStage;

    public PostProperty(ObservableList<Property> properties, Stage primaryStage) {
        this.properties = properties;
        this.primaryStage = primaryStage;
        createUI();
    }

    private void createUI() {
        VBox postPropertyLayout = new VBox(10);

        TextField addressField = new TextField();
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Apartment", "House", "Condo");
        TextField rentField = new TextField();
        Button postButton = new Button("Post Property");
        Button backButton = new Button("Back");

        postButton.setOnAction(event -> {
            String address = addressField.getText();
            String type = typeComboBox.getValue();
            double rent = Double.parseDouble(rentField.getText());

            properties.add(new Property(address, type, rent));
            showAlert("Property Posted", "Your property has been posted successfully.");
            showProfileSelection();
        });

        backButton.setOnAction(event -> showProfileSelection());

        postPropertyLayout.getChildren().addAll(
                new Label("Address:"), addressField,
                new Label("Type:"), typeComboBox,
                new Label("Rent:"), rentField,
                postButton, backButton
        );

        primaryStage.setScene(new Scene(postPropertyLayout, 400, 300));
    }

    private void showProfileSelection() {
        VBox profileSelectionLayout = new VBox(10);

        Button postPropertyButton = new Button("Post a Property");
        Button rentPropertyButton = new Button("Rent a Property");

        postPropertyButton.setOnAction(event -> showPostPropertyProfile());
        rentPropertyButton.setOnAction(event -> showRentPropertyProfile());

        profileSelectionLayout.getChildren().addAll(postPropertyButton, rentPropertyButton);

        primaryStage.setScene(new Scene(profileSelectionLayout, 600, 400));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    void showPostPropertyProfile() {
        VBox postPropertyLayout = new VBox(10);

        TextField addressField = new TextField();
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Apartment", "House", "Condo");
        TextField rentField = new TextField();
        Button postButton = new Button("Post Property");
        Button backButton = new Button("Back");

        postButton.setOnAction(event -> {
            String address = addressField.getText();
            String type = typeComboBox.getValue();
            double rent = Double.parseDouble(rentField.getText());

            properties.add(new Property(address, type, rent));
            showAlert("Property Posted", "Your property has been posted successfully.");
            showProfileSelection();
        });

        backButton.setOnAction(event -> showProfileSelection());

        postPropertyLayout.getChildren().addAll(
                new Label("Address:"), addressField,
                new Label("Type:"), typeComboBox,
                new Label("Rent:"), rentField,
                postButton, backButton
        );

        primaryStage.setScene(new Scene(postPropertyLayout, 400, 300));
    }
    
    private void showRentPropertyProfile() {
        ListView<Property> propertyListView = new ListView<>(properties);
        propertyListView.setPrefHeight(200);

        TextArea propertyDetailsTextArea = new TextArea();
        propertyDetailsTextArea.setEditable(false);
        propertyDetailsTextArea.setPrefHeight(200);

        propertyListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                propertyDetailsTextArea.setText(newValue.toString());
            } else {
                propertyDetailsTextArea.clear();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showProfileSelection());

        VBox rentPropertyLayout = new VBox(10);
        rentPropertyLayout.getChildren().addAll(propertyListView, propertyDetailsTextArea, backButton);

        primaryStage.setScene(new Scene(rentPropertyLayout, 400, 300));
    }


    
    
}

