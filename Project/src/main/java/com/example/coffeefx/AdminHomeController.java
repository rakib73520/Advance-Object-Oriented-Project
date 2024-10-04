package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

public class AdminHomeController {

    @FXML
    private TextArea description;

    @FXML
    private Label errormsg;

    @FXML
    private Button imagebutton;

    @FXML
    private Label imagelable;

    @FXML
    private TextField locationx;

    @FXML
    private TextField name;

    @FXML
    private Button logoutButton;

    @FXML
    private Button discoverybtn;

    @FXML
    private Button transactionbtn;

    Connection con;
    PreparedStatement pst;

    @FXML
    void onAddPlace(ActionEvent event) {
        String place_name = name.getText();
        String place_location = locationx.getText();
        String place_description = description.getText();
        String place_image = "file:///";
        place_image+=imagelable.getText();

        if(!Objects.equals(place_name, "") && !Objects.equals(place_location, "") && !Objects.equals(place_description, "") && !Objects.equals(place_image, "file:///")){
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                pst = con.prepareStatement("INSERT INTO places (place_name, place_location, place_description, place_image) VALUES (?, ?, ?, ?)");
                pst.setString(1, place_name);
                pst.setString(2, place_location);
                pst.setString(3, place_description);
                pst.setString(4, place_image);
                pst.executeUpdate();
                errormsg.setText("Place Added Successfully!");
                name.setText("");
                locationx.setText("");
                description.setText("");
                imagelable.setText("");
            }catch (Exception a){
                a.printStackTrace();
            }
        }else{
            errormsg.setText("All Fields Are Required!");
        }
    }

    @FXML
    void onChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            imagelable.setText(file.getAbsolutePath());
        }
    }

    public void onLogoutButtonAction(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    void onDiscoveryButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newdiscovery.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) discoverybtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    void onTransactionButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admintransaction.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) transactionbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}


