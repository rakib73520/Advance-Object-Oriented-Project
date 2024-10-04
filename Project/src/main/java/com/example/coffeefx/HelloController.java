package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.*;

public class HelloController {
    @FXML
    private Button logoutButton;
    @FXML
    private Label fullName;
    @FXML
    private Button exploreButton;

    String user_fullname = "";
    String user_image = "";
    String user_id = "";
    String user_experience = "";

    public void onLogoutButtonAction(ActionEvent e){
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
    public void onExploreButtonAction(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myexploration.fxml"));
            Parent root = loader.load();
            MyExplorationController myexplorationcontroller = loader.getController();
            myexplorationcontroller.showInformation1(user_fullname,user_image,user_id,user_experience);
            Stage stage = (Stage) exploreButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void showInformation(String fullname, String image, String id, String experience){
        fullName.setText(fullname);
        user_fullname = fullname;
        user_image = image;
        user_id = id;
        user_experience = experience;
    }
}