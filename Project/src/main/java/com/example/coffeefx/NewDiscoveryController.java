package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewDiscoveryController implements Initializable {

    @FXML
    private Button backbtn;

    @FXML
    private VBox vbox;

    Connection con;
    PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Discovery> discoveries = new ArrayList<>(discoveries());

        for(int i=0;i<discoveries.size();i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("extranewdiscovery.fxml"));

            try{
                AnchorPane anchorpane = loader.load();
                ExtraNewDiscoveryController extranewdiscoverycontroller = loader.getController();
                extranewdiscoverycontroller.setData(discoveries.get(i));

                Button deleteBtn = extranewdiscoverycontroller.getDeleteButton();
                Button addBtn = extranewdiscoverycontroller.getAddButton();
                String id = discoveries.get(i).getId();
                String userid = discoveries.get(i).getUserid();
                String place_name = discoveries.get(i).getPlacename();
                String place_location = discoveries.get(i).getPlacelocation();
                String place_description = discoveries.get(i).getPlacedescription();
                String place_image = discoveries.get(i).getPlaceimage();

                deleteBtn.setOnAction(event -> {
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                        pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                        pst.setString(1, userid);
                        ResultSet queryResult = pst.executeQuery();
                        if(queryResult.next()){
                            int experience = queryResult.getInt("experience");
                            experience-=40;

                            pst = con.prepareStatement("DELETE FROM discovery WHERE id = ?");
                            pst.setString(1, id);
                            int rowsAffected = pst.executeUpdate();
                            if (rowsAffected > 0) {
                                pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");

                                pst.setInt(1, experience);
                                pst.setString(2, userid);
                                pst.executeUpdate();

                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Deleted Successfully");
                                successAlert.showAndWait();

                                FXMLLoader newLoader = new FXMLLoader(getClass().getResource("newdiscovery.fxml"));
                                Parent newRoot = newLoader.load();
                                Stage stage = (Stage) deleteBtn.getScene().getWindow();
                                Scene newScene = new Scene(newRoot);
                                stage.setScene(newScene);
                                stage.show();
                            } else {
                                Alert failAlert = new Alert(Alert.AlertType.INFORMATION);
                                failAlert.setTitle("Failed");
                                failAlert.setHeaderText(null);
                                failAlert.setContentText("Something went wrong");
                                failAlert.showAndWait();
                            }
                        }

                        con.close();
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                });

                addBtn.setOnAction(event -> {
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                        pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                        pst.setString(1, userid);
                        ResultSet queryResult = pst.executeQuery();
                        if(queryResult.next()){
                            int experience = queryResult.getInt("experience");
                            experience+=70;

                            pst = con.prepareStatement("DELETE FROM discovery WHERE id = ?");
                            pst.setString(1, id);
                            int rowsAffected = pst.executeUpdate();
                            if (rowsAffected > 0) {
                                pst = con.prepareStatement("INSERT INTO places (place_name, place_location, place_description, place_image) VALUES (?, ?, ?, ?)");
                                pst.setString(1, place_name);
                                pst.setString(2, place_location);
                                pst.setString(3, place_description);
                                pst.setString(4, place_image);
                                pst.executeUpdate();

                                pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");

                                pst.setInt(1, experience);
                                pst.setString(2, userid);
                                pst.executeUpdate();

                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Added Successfully");
                                successAlert.showAndWait();

                                FXMLLoader newLoader = new FXMLLoader(getClass().getResource("newdiscovery.fxml"));
                                Parent newRoot = newLoader.load();
                                Stage stage = (Stage) addBtn.getScene().getWindow();
                                Scene newScene = new Scene(newRoot);
                                stage.setScene(newScene);
                                stage.show();
                            } else {
                                Alert failAlert = new Alert(Alert.AlertType.INFORMATION);
                                failAlert.setTitle("Failed");
                                failAlert.setHeaderText(null);
                                failAlert.setContentText("Something went wrong");
                                failAlert.showAndWait();
                            }
                        }

                        con.close();
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                });

                vbox.getChildren().add(anchorpane);
            }catch (Exception a){
                a.printStackTrace();
            }
        }
    }

    private List<Discovery> discoveries(){
        List<Discovery> ls = new ArrayList<>();

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
            pst = con.prepareStatement("SELECT * FROM discovery");
            ResultSet queryResult = pst.executeQuery();

            while (queryResult.next()) {
                String place_name = queryResult.getString("place_name");
                String place_location = queryResult.getString("place_location");
                String place_description = queryResult.getString("place_description");
                String place_image = queryResult.getString("place_image");
                String user_name = queryResult.getString("user_name");
                String user_id = queryResult.getString("user_id");
                String place_id = queryResult.getString("id");

                Discovery discovery = new Discovery();
                discovery.setPlacename(place_name);
                discovery.setPlacelocation(place_location);
                discovery.setPlacedescription(place_description);
                discovery.setPlaceimage(place_image);
                discovery.setUsername(user_name);
                discovery.setId(place_id);
                discovery.setUserid(user_id);

                ls.add(discovery);
            }
        }catch (Exception a){
            a.printStackTrace();
        }

        return ls;
    }

    @FXML
    void onBackButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminhome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }
}
