package controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.FetchData;
import Model.propertyData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.DatabaseConnection;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button availableProperty_btn;

    @FXML
    private Button rentProperty_btn;

    @FXML
    private Label home_availableProperty;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Label home_totalCustomers;

    @FXML
    private BarChart<?, ?> home_incomeChart;

    @FXML
    private LineChart<?, ?> home_customerChart;

    @FXML
    private AnchorPane availableProperty_form;

    @FXML
    private TextField availableProperty_Id;

    @FXML
    private TextField availableProperty_Street;
    
    @FXML
    private TextField availableProperty_Unit;
    
    @FXML
    private TextField availableProperty_City;
    
    @FXML
    private TextField availableProperty_State;

    @FXML
    private ComboBox<?> availableProperty_Status;

    @FXML
    private ImageView availableProperty_imageView;

    @FXML
    private Button availableProperty_importBtn;

    @FXML
    private Button availableProperty_insertBtn;

    @FXML
    private Button availableProperty_updateBtn;

    @FXML
    private Button availableProperty_deleteBtn;

    @FXML
    private Button availableProperty_clearBtn;

    @FXML
    private TextField availableProperty_UpfrontAmount;

    @FXML
    private TableView<propertyData> availableProperty_tableView;

    @FXML
    private TableColumn<propertyData, String> availableProperty_col_propertyId;

    @FXML
    private TableColumn<propertyData, String> availableProperty_col_street;

    @FXML
    private TableColumn<propertyData, String> availableProperty_col_unit;

    @FXML
    private TableColumn<propertyData, String> availableProperty_col_upfrontAmount;

    @FXML
    private TableColumn<propertyData, String> availableProperty_col_status;
    
    @FXML
    private TableColumn<propertyData, String> availableProperty_col_city;
    
    @FXML
    private TableColumn<propertyData, String> availableProperty_col_state;

    @FXML
    private TextField availableProperty_search;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<?> rent_propertyId;
    
    @FXML
    private ComboBox<?> rent_City;
    
    @FXML
    private ComboBox<?> rent_Street;
    
    @FXML
    private ComboBox<?> rent_Unit;


    @FXML
    private TextField rent_firstName;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private Button rentBtn;

    @FXML
    private Label rent_total;

    @FXML
    private TextField rent_amount;

    @FXML
    private Label rent_balance;

    @FXML
    private AnchorPane home_form;

    @FXML
    private TableView<propertyData> rent_tableView;

    @FXML
    private TableColumn<propertyData, String> rent_col_propertyId;

    @FXML
    private TableColumn<propertyData, String> rent_col_price;

    @FXML
    private TableColumn<propertyData, String> rent_col_status;

//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;

    public void homeAvailableRentals(){
        
        String sql = "SELECT COUNT(id) FROM property WHERE status = 'Available'";
        
        connect = DatabaseConnection.connectDb();
        int countAC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAC = result.getInt("COUNT(id)");
            }
            System.out.println(String.valueOf(countAC));
            home_availableProperty.setText(String.valueOf(countAC));            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeTotalIncome(){
        String sql = "SELECT SUM(total) FROM customer";
        
        double sumIncome = 0;
        
        connect = DatabaseConnection.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                sumIncome = result.getDouble("SUM(total)");
            }
            home_totalIncome.setText("$" + String.valueOf(sumIncome));
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    public void homeTotalCustomers(){
        
        String sql = "SELECT COUNT(id) FROM customer";
        
        int countTC = 0;
        
        connect = DatabaseConnection.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countTC = result.getInt("COUNT(id)");
            }
            home_totalCustomers.setText(String.valueOf(countTC));
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeIncomeChart(){
        
        home_incomeChart.getData().clear();
        
        String sql = "SELECT start_date, SUM(total) FROM customer GROUP BY start_date ORDER BY TIMESTAMP(start_date) ASC LIMIT 6";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            home_incomeChart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    public void homeCustomerChart(){
        home_customerChart.getData().clear();
        
        String sql = "SELECT start_date, COUNT(id) FROM customer GROUP BY start_date ORDER BY TIMESTAMP(start_date) ASC LIMIT 4";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            home_customerChart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void availablePropertyAdd() {

        String sql = "INSERT INTO property (property_id, street, unit, status, city, state, upfront_amount, image, date) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;

            if (availableProperty_Id.getText().isEmpty()
                    || availableProperty_Street.getText().isEmpty()
                    || availableProperty_Unit.getText().isEmpty()
                    || availableProperty_City.getText().isEmpty()
                    || availableProperty_State.getText().isEmpty()
                    || availableProperty_Status.getSelectionModel().getSelectedItem() == null
                    || availableProperty_UpfrontAmount.getText().isEmpty()
                    || FetchData.path == null || FetchData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableProperty_Id.getText());
                prepare.setString(2, availableProperty_Street.getText());
                prepare.setString(3, availableProperty_Unit.getText());
                prepare.setString(4, (String) availableProperty_Status.getSelectionModel().getSelectedItem());
                prepare.setString(5, availableProperty_City.getText());
                prepare.setString(6, availableProperty_State.getText());
                prepare.setString(7, availableProperty_UpfrontAmount.getText());

                String uri = FetchData.path;
                uri = uri.replace("\\", "\\\\");

                prepare.setString(8, uri);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(9, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                availablePropertyShowListData();
                availablePropertyClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availablePropertyUpdate() {

        String uri = FetchData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE property SET status ='"
                + availableProperty_Status.getSelectionModel().getSelectedItem() + "', upfront_amount = '"
                + availableProperty_UpfrontAmount.getText() + "', image = '" + uri
                + "' WHERE property_id = '" + availableProperty_Id.getText() + "',street ='"
                +availableProperty_Street.getText()+"', unit ='"
                +availableProperty_Unit.getText()+"'city = '"
                +availableProperty_City.getText() + "'state = '"
                +availableProperty_State.getText()+"'";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;

            if (availableProperty_Id.getText().isEmpty()
                    || availableProperty_Street.getText().isEmpty()
                    || availableProperty_Unit.getText().isEmpty()
                    || availableProperty_City.getText().isEmpty()
                    || availableProperty_State.getText().isEmpty()
                    || availableProperty_Status.getSelectionModel().getSelectedItem() == null
                    || availableProperty_UpfrontAmount.getText().isEmpty()
                    || FetchData.path == null || FetchData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Property ID: " + availableProperty_Id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availablePropertyShowListData();
                    availablePropertyClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availablePropertyDelete() {

        String sql = "DELETE FROM property WHERE property_id = '" + availableProperty_Id.getText() + "'";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (availableProperty_Id.getText().isEmpty()
                    || availableProperty_Street.getText().isEmpty()
                    || availableProperty_Unit.getText().isEmpty()
                    || availableProperty_City.getText().isEmpty()
                    || availableProperty_State.getText().isEmpty()
                    || availableProperty_Status.getSelectionModel().getSelectedItem() == null
                    || availableProperty_UpfrontAmount.getText().isEmpty()
                    || FetchData.path == null || FetchData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Property ID: " + availableProperty_Id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availablePropertyShowListData();
                    availablePropertyClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availablePropertyClear() {
        availableProperty_Id.setText("");
        availableProperty_State.setText("");
        availableProperty_Status.getSelectionModel().clearSelection();
        availableProperty_UpfrontAmount.setText("");
        availableProperty_City.setText("");
        availableProperty_Street.setText("");
        availableProperty_Unit.setText("");

        FetchData.path = "";

        availableProperty_imageView.setImage(null);

    }

    private String[] listStatus = {"Available", "Not Available"};

    public void availablePropertyStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableProperty_Status.setItems(listData);
    }

    public void availablePropertyImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png", "*.jpeg"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            FetchData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 116, 153, false, true);
            availableProperty_imageView.setImage(image);

        }

    }

    public ObservableList<propertyData> availablePropertyListData() {

        ObservableList<propertyData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM property";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            propertyData propertyD;

            while (result.next()) {
            	propertyD = new propertyData(result.getInt("property_id"),
            			result.getString("street"),
            			result.getString("unit"),
            			result.getDouble("upfront_amount"),
            			result.getString("status"),
            			result.getString("city"),
            			result.getString("state"),
            			result.getString("image"),
            			result.getDate("date"));
            			

                listData.add(propertyD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<propertyData> availablePropertyList;

    public void availablePropertyShowListData() {
        availablePropertyList = availablePropertyListData();

        availableProperty_col_propertyId.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        availableProperty_col_street.setCellValueFactory(new PropertyValueFactory<>("street"));
        availableProperty_col_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        availableProperty_col_upfrontAmount.setCellValueFactory(new PropertyValueFactory<>("upfrontAmount"));
        availableProperty_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availableProperty_col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        availableProperty_col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        

        availableProperty_tableView.setItems(availablePropertyList);
    }

    public void availablePropertySearch() {

        FilteredList<propertyData> filter = new FilteredList<>(availablePropertyList, e -> true);

        availableProperty_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicatePropertyData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicatePropertyData.getPropertyID().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePropertyData.getDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePropertyData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<propertyData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(availableProperty_tableView.comparatorProperty());
        availableProperty_tableView.setItems(sortList);

    }

    public void availablePropertySelect() {
        propertyData propertyD = availableProperty_tableView.getSelectionModel().getSelectedItem();
        int num = availableProperty_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        availableProperty_Id.setText(String.valueOf(propertyD.getPropertyID()));
        availableProperty_Street.setText(propertyD.getStreet());
        availableProperty_UpfrontAmount.setText(String.valueOf(propertyD.getUpfrontAmount()));
        availableProperty_City.setText(String.valueOf(propertyD.getCity()));
        availableProperty_State.setText(String.valueOf(propertyD.getState()));

        FetchData.path = propertyD.getImage();

        String uri = "file:" + propertyD.getImage();

        image = new Image(uri, 116, 153, false, true);
        availableProperty_imageView.setImage(image);

    }
    
    public void rentPay(){
        rentCustomerId();
        
        String sql = "INSERT INTO customer "
                + "(customer_id, firstName, lastName, gender, property_id,"
                + "total, date_rented, date_return) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            Alert alert;
            
            if(rent_firstName.getText().isEmpty()
                    || rent_lastName.getText().isEmpty()
                    || rent_gender.getSelectionModel().getSelectedItem() == null
                    || rent_propertyId.getSelectionModel().getSelectedItem() == null
                    || totalP == 0 || rent_amount.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            }else{
                
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, rent_firstName.getText());
                    prepare.setString(3, rent_lastName.getText());
                    prepare.setString(4, (String)rent_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String)rent_propertyId.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(totalP));
                    prepare.setString(9, String.valueOf(rent_dateRented.getValue()));
                    prepare.setString(10, String.valueOf(rent_dateReturn.getValue()));

                    prepare.executeUpdate();
                    
                    // SET THE  STATUS OF CAR TO NOT AVAILABLE 
                    String updateProperty = "UPDATE property SET status = 'Not Available' WHERE property_id = '"
                            +rent_propertyId.getSelectionModel().getSelectedItem()+"'";
                    
                    statement = connect.createStatement();
                    statement.executeUpdate(updateProperty);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                    
                    rentPropertyShowListData();
                    
                    rentClear();
                } // NOW LETS PROCEED TO DASHBOARD FORM : ) 
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void rentClear(){
        totalP = 0;
        rent_firstName.setText("");
        rent_lastName.setText("");
        rent_gender.getSelectionModel().clearSelection();
        amount = 0;
        balance = 0;
        rent_balance.setText("$0.0");
        rent_total.setText("$0.0");
        rent_amount.setText("");
        rent_propertyId.getSelectionModel().clearSelection();
    }
    
    private int customerId;
    public void rentCustomerId(){
        String sql = "SELECT id FROM customer";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                // GET THE LAST id and add + 1
                customerId = result.getInt("id") + 1;
            }
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    private double amount;
    private double balance;
    public void rentAmount(){
        Alert alert;
        if(totalP == 0 || rent_amount.getText().isEmpty()){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid : Amount should be more than Total Rent to offer change");
            alert.showAndWait();
            
            rent_amount.setText("");
        }else{
            amount = Double.parseDouble(rent_amount.getText());
            
            if(amount >= totalP){
                balance = (amount - totalP);
                rent_balance.setText("$" + String.valueOf(balance));
            }else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid : Amount should be more than Total Rent to offer change");
                alert.showAndWait();
                
                rent_amount.setText("");
            }
            
        }
        
    }

    public ObservableList<propertyData> rentPropertyListData() {

        ObservableList<propertyData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM property";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            propertyData propertyD;

            while (result.next()) {
            	propertyD = new propertyData(result.getInt("property_ID"), result.getString("street"),
                         result.getString("unit"), result.getDouble("upfrontAmount"),
                         result.getString("status"),
                         result.getString("city"),
                         result.getString("state"),
                         result.getString("image"),
                         result.getDate("date"));

                listData.add(propertyD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private int countDate;
    public void rentDate(){
        Alert alert;
        if(rent_propertyId.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something wrong :3");
            alert.showAndWait();
            
            rent_dateRented.setValue(null);
            rent_dateReturn.setValue(null);
            
        }else{
            
            if(rent_dateReturn.getValue().isAfter(rent_dateRented.getValue())){
                // COUNT THE DAY
                countDate = rent_dateReturn.getValue().compareTo(rent_dateRented.getValue());
            }else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
                // INCREASE OF 1 DAY ONCE THE USER CLICKED THE SAME DAY 
                rent_dateReturn.setValue(rent_dateRented.getValue().plusDays(1));
                
            }
        }
    }
    
    private double totalP;
    public void rentDisplayTotal(){
        rentDate();
        double price = 0;
        String sql = "SELECT price FROM property WHERE property_id = '"
                +rent_propertyId.getSelectionModel().getSelectedItem()+"'";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                price = result.getDouble("price");
            }
            // price * the count day you want to use the property
            totalP = (price * countDate);
            // DISPLAY TOTAL
            rent_total.setText("$" + String.valueOf(totalP));
            
        }catch(Exception e){e.printStackTrace();}
        
    }

    private String[] genderList = {"Male", "Female", "Others"};

    public void rentPropertyGender() {

        List<String> listG = new ArrayList<>();

        for (String data : genderList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);

        rent_gender.setItems(listData);

    }

    public void rentPropertyPropertyId() {

        String sql = "SELECT * FROM property WHERE status = 'Available'";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("property_id"));
            }

            rent_propertyId.setItems(listData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    


    private ObservableList<propertyData> rentPropertyList;

    public void rentPropertyShowListData() {
        rentPropertyList = rentPropertyListData();

        rent_col_propertyId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        rent_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        rent_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        rent_tableView.setItems(rentPropertyList);
    }

    public void displayUsername() {
        String user = FetchData.username;
        // TO SET THE FIRST LETTER TO BIG LETTER
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                // HIDE YOUR DASHBOARD FORM
                logoutBtn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("../Home.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            availableProperty_form.setVisible(false);
            rent_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            availableProperty_btn.setStyle("-fx-background-color:transparent");
            rentProperty_btn.setStyle("-fx-background-color:transparent");

            homeAvailableRentals();
            homeTotalIncome();
            homeTotalCustomers();
            homeIncomeChart();
            homeCustomerChart();
            
        } else if (event.getSource() == availableProperty_btn) {
            home_form.setVisible(false);
            availableProperty_form.setVisible(true);
            rent_form.setVisible(false);

            availableProperty_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            home_btn.setStyle("-fx-background-color:transparent");
            rentProperty_btn.setStyle("-fx-background-color:transparent");

            // TO UPDATE YOUR TABLEVIEW ONCE YOU CLICK THE AVAILABLE CAR NAV BUTTON
            availablePropertyShowListData();
            availablePropertyStatusList();
            availablePropertySearch();

        } else if (event.getSource() == rentProperty_btn) {
            home_form.setVisible(false);
            availableProperty_form.setVisible(false);
            rent_form.setVisible(true);

            rentProperty_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableProperty_btn.setStyle("-fx-background-color:transparent");

            rentPropertyShowListData();
            rentPropertyPropertyId();
            rentPropertyGender();

        }

    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();

        homeAvailableRentals();
        homeTotalIncome();
        homeTotalCustomers();
        homeIncomeChart();
        homeCustomerChart();
        
        
        // TO DISPLAY THE DATA ON THE TABLEVIEW
        availablePropertyShowListData();
        availablePropertyStatusList();
        availablePropertySearch();

        rentPropertyShowListData();
        rentPropertyPropertyId();
        rentPropertyGender();

    }
    public void rentPropertyCity() {
    	String sql = "SELECT * FROM property WHERE status = 'Available'";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("city"));
            }

            rent_City.setItems(listData);

            rentPropertyStreet();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void rentPropertyStreet() {

        String sql = "SELECT * FROM property WHERE city = '"
                + rent_City.getSelectionModel().getSelectedItem() + "'";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("brand"));
            }

            rent_Street.setItems(listData);

            rentPropertyUnit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void rentPropertyUnit() {

        String sql = "SELECT * FROM property WHERE street = '"
                + rent_Street.getSelectionModel().getSelectedItem() + "'";

        connect = DatabaseConnection.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("model"));
            }

            rent_Unit.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
