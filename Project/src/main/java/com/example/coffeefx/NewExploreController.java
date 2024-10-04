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

public class NewExploreController {

    @FXML
    private Button backbtn;

    @FXML
    private TextArea description;

    @FXML
    private Label errormsg;

    @FXML
    private Label fullName;

    @FXML
    private Label fullName1;

    @FXML
    private Label fullName11;

    @FXML
    private Label fullName111;

    @FXML
    private Button imagebutton;

    @FXML
    private Label imagelable;

    @FXML
    private TextField locationx;

    @FXML
    private TextField name;

    Connection con;
    PreparedStatement pst;

    private String user_id = "";
    private String user_name = "";
    private String user_image = "";
    private String user_experience = "";

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
                pst = con.prepareStatement("INSERT INTO discovery (place_name, place_location, place_description, place_image, user_id, user_name) VALUES (?, ?, ?, ?, ?, ?)");
                pst.setString(1, place_name);
                pst.setString(2, place_location);
                pst.setString(3, place_description);
                pst.setString(4, place_image);
                pst.setString(5, user_id);
                pst.setString(6, user_name);
                pst.executeUpdate();
                errormsg.setText("Your Request Submitted!");
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

    public void showInformationNewExplore(String user_name,String user_image, String user_id, String user_experience){
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_image = user_image;
        this.user_experience = user_experience;
    }

    public void onBackButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myexploration.fxml"));
            Parent root = loader.load();
            MyExplorationController myexplorationcontroller = loader.getController();
            myexplorationcontroller.showInformation1(user_name,user_image,user_id,user_experience);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }

}

