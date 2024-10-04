package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExtraNewDiscoveryController implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button deletebtn;

    @FXML
    private Label placedescription;

    @FXML
    private Rectangle placeimage;

    @FXML
    private Label placelocation;

    @FXML
    private Label placename;

    @FXML
    private Label title1;

    @FXML
    private Label title2;

    @FXML
    private Label title3;

    @FXML
    private Label title4;

    @FXML
    private Label username;


    Connection con;
    PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Discovery discovery){
        placename.setText(discovery.getPlacename());
        placelocation.setText(discovery.getPlacelocation());
        placedescription.setText(discovery.getPlacedescription());
        Image img = new Image(discovery.getPlaceimage());
        placeimage.setFill(new ImagePattern(img));
        username.setText(discovery.getUsername());
    }

    public Button getDeleteButton() {
        return deletebtn;
    }

    public Button getAddButton() {
        return addbtn;
    }

}

