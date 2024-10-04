package com.example.coffeefx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class DesignController {
    @FXML
    private TextField UserTextField;

    @FXML
    private TextField UserPassField;

    @FXML
    private Label labelID;

    @FXML
    private Button loginButton;

    @FXML
    private Button Singup;

    Connection con;
    PreparedStatement pst;

    public void loginButtonOnAction (ActionEvent e) {
        if (UserTextField.getText().isBlank() == false && UserPassField.getText().isBlank() == false) {
            validLogin();
        } else {
            labelID.setText("Please enter user name and password!");
        }
    }

    public void validLogin()
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
            pst = con.prepareStatement("SELECT * FROM userinfo WHERE username = '"+UserTextField.getText()+"' AND password='"+UserPassField.getText()+"'");
            ResultSet queryResult = pst.executeQuery();

            String fullname = "";
            String image = "";
            String id = "";
            String experience = "";

            while (queryResult.next()) {
                fullname = queryResult.getString("fullname");
                image = queryResult.getString("image");
                id = queryResult.getString("id");
                experience = queryResult.getString("experience");
            }

            if (id != "") {
                labelID.setText("Welcome...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                HelloController helloController = loader.getController();
                helloController.showInformation(fullname,image,id,experience);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }else if(Objects.equals(UserTextField.getText(), "admin") && Objects.equals(UserPassField.getText(), "admin")){
                labelID.setText("Welcome...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminhome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }else {
                labelID.setText("Invalid Login! please try again");
            }

        }
        catch(Exception a)
        {
            a.printStackTrace();
        }
    }

    public void singupButtonOnAction (ActionEvent e) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) Singup.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }
}