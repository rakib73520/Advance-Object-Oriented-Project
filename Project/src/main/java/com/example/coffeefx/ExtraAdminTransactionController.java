package com.example.coffeefx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ExtraAdminTransactionController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button approvebtn;

    @FXML
    private Label placename;

    @FXML
    private Button rejectbtn;

    @FXML
    private Label title1;

    @FXML
    private Label title2;

    @FXML
    private Label title3;

    @FXML
    private Label transaction;

    @FXML
    private Label username;

    Connection con;
    PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Transaction transactions){
        placename.setText(transactions.getPlacename());
        transaction.setText(transactions.getWay());
        username.setText(transactions.getUsername());
    }

    public Button getRejectButton() {
        return rejectbtn;
    }

    public Button getApproveButton() {
        return approvebtn;
    }

}

