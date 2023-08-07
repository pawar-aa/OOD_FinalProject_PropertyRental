package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private ObservableList<application.Property> properties;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Property Rental App");

        properties = FXCollections.observableArrayList();

        showProfileSelection();
    }

    private void showProfileSelection() {
        VBox profileSelectionLayout = new VBox(10);

        Button postPropertyButton = new Button("Post a Property");
        Button rentPropertyButton = new Button("Rent a Property");

        postPropertyButton.setOnAction(event -> {
            PostProperty postProperty = new PostProperty(properties, primaryStage);
            postProperty.showPostPropertyProfile();
        });

        rentPropertyButton.setOnAction(event -> {
            RentProperty rentProperty = new RentProperty(properties, primaryStage);
            rentProperty.showRentPropertyProfile();
        });


        profileSelectionLayout.getChildren().addAll(postPropertyButton, rentPropertyButton);

        primaryStage.setScene(new Scene(profileSelectionLayout, 600, 400));
        primaryStage.show();
    }

    public class Property {
        private String address;
        private String type;
        private double rent;

        public Property(String address, String type, double rent) {
            this.address = address;
            this.type = type;
            this.rent = rent;
        }

        @Override
        public String toString() {
            return "Address: " + address + "\nType: " + type + "\nRent: $" + rent;
        }
    }
    
    
    
    
}
