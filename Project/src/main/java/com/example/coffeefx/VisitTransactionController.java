package com.example.coffeefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class VisitTransactionController {

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private Circle userimage;

    @FXML
    private Label username;

    @FXML
    private Circle verify;

    @FXML
    private Label way;

    public void setData(Transaction transaction){
        username.setText(transaction.getUsername());
        Image img = new Image(transaction.getUserimage());
        userimage.setFill(new ImagePattern(img));
        way.setText(transaction.getWay());
    }

    public Circle getVerify() {
        return verify;
    }

}
