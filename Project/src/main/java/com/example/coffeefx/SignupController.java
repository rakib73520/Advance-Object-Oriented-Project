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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SignupController {

    @FXML
    private Button Singup;

    @FXML
    private TextField UserFullnameTextField;

    @FXML
    private TextField UserNameField;

    @FXML
    private PasswordField UserPassField;

    @FXML
    private Button imagebutton;

    @FXML
    private Label imagelable;

    @FXML
    private Label errormsg;

    Connection con;
    PreparedStatement pst;

    @FXML
    void onChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            imagelable.setText(file.getAbsolutePath());
        }
    }

    @FXML
    void onSignupButtonClicked(ActionEvent event) {
        String fullname = UserFullnameTextField.getText();
        String username = UserNameField.getText();
        String password = UserPassField.getText();
        String myimage = "file:///";
        myimage+=imagelable.getText();

        if(!Objects.equals(fullname, "") && !Objects.equals(username, "") && !Objects.equals(password, "") &&!Objects.equals(myimage, "file:///")){
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                pst = con.prepareStatement("SELECT * FROM userinfo WHERE username = ? AND password = ?");
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet queryResult = pst.executeQuery();
                if(queryResult.next()){
                    errormsg.setText("Account already exists!");
                }else{
                    pst = con.prepareStatement("INSERT INTO userinfo (fullname, username, password, image, experience) VALUES (?, ?, ?, ?, ?)");
                    pst.setString(1, fullname);
                    pst.setString(2, username);
                    pst.setString(3, password);
                    pst.setString(4, myimage);
                    pst.setInt(5, 0);
                    pst.executeUpdate();
                    errormsg.setText("Account Created!");
                    UserFullnameTextField.setText("");
                    UserNameField.setText("");
                    UserPassField.setText("");
                    imagelable.setText("");

                    pst = con.prepareStatement("SELECT * FROM userinfo WHERE username = ? AND password = ?");
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet queryResult1 = pst.executeQuery();

                    if(queryResult1.next()){
                        String id = queryResult1.getString("id");
                        String img = queryResult1.getString("image");
                        String experience = queryResult1.getString("experience");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                        Parent root = loader.load();
                        HelloController helloController = loader.getController();
                        helloController.showInformation(fullname,img,id,experience);
                        Stage stage = (Stage) Singup.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }

            }catch (Exception a){
                a.printStackTrace();
            }
        }else{
            errormsg.setText("All Fields Are Required!");
        }
    }

}
