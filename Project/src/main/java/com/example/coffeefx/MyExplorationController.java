package com.example.coffeefx;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyExplorationController implements Initializable{

    @FXML
    private Rectangle badgeimg;

    @FXML
    private Label badgename;

    @FXML
    private Label e1;

    @FXML
    private Label e2;

    @FXML
    private Label e3;

    @FXML
    private Label e4;

    @FXML
    private Label e5;

    @FXML
    private Label e6;

    @FXML
    private Label e7;

    @FXML
    private Label e8;

    @FXML
    private Label e9;

    @FXML
    private Label exp;

    @FXML
    private Rectangle flag1;

    @FXML
    private Rectangle flag2;

    @FXML
    private Rectangle flag3;

    @FXML
    private Rectangle flag4;

    @FXML
    private Rectangle flag5;

    @FXML
    private Rectangle flag6;

    @FXML
    private Rectangle flag7;

    @FXML
    private Rectangle flag8;

    @FXML
    private Rectangle flag9;

    @FXML
    private Circle me;

    @FXML
    private Circle placeimg1;

    @FXML
    private Circle placeimg2;

    @FXML
    private Circle placeimg3;

    @FXML
    private Circle placeimg4;

    @FXML
    private Circle placeimg5;

    @FXML
    private Circle placeimg6;

    @FXML
    private Circle placeimg7;

    @FXML
    private Circle placeimg8;

    @FXML
    private Circle placeimg9;

    @FXML
    private Label placename1;

    @FXML
    private Label placename2;

    @FXML
    private Label placename3;

    @FXML
    private Label placename4;

    @FXML
    private Label placename5;

    @FXML
    private Label placename6;

    @FXML
    private Label placename7;

    @FXML
    private Label placename8;

    @FXML
    private Label placename9;

    @FXML
    private Circle topimg;

    @FXML
    private Label topname;

    @FXML
    private Label v1;

    @FXML
    private Label v2;

    @FXML
    private Label v3;

    @FXML
    private Label v4;

    @FXML
    private Label v5;

    @FXML
    private Label v6;

    @FXML
    private Label v7;

    @FXML
    private Label v8;

    @FXML
    private Label v9;

    @FXML
    private Line line1;

    @FXML
    private Line line2;

    @FXML
    private Line line3;

    @FXML
    private Line line4;

    @FXML
    private Line line5;

    @FXML
    private Line line6;

    @FXML
    private Line line7;

    @FXML
    private Line line8;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    @FXML
    private AnchorPane pane5;

    @FXML
    private AnchorPane pane6;

    @FXML
    private AnchorPane pane7;

    @FXML
    private AnchorPane pane8;

    @FXML
    private AnchorPane pane9;

    @FXML
    private Button backbtn;

    @FXML
    private Button gobtn;

    private String me_fullname = "";
    private String me_image = "";
    private String me_id = "";
    private String me_experience = "";


    Image img = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/flag.jpg");

    Connection con;
    PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String[]> placeList = new ArrayList<>();

            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                pst = con.prepareStatement("SELECT * FROM places");
                ResultSet queryResult = pst.executeQuery();

                pst = con.prepareStatement("SELECT * FROM userinfo WHERE experience = (SELECT MAX(experience) FROM userinfo)");
                ResultSet queryResult1 = pst.executeQuery();
                if(queryResult1.next()){
                    topname.setText(queryResult1.getString("fullname"));
                    Image temptopimg = new Image(queryResult1.getString("image"));
                    topimg.setFill(new ImagePattern(temptopimg));
                }

                int count = 0;

                while (queryResult.next()) {

                    String place_name = queryResult.getString("place_name");
                    String place_image = queryResult.getString("place_image");
                    String place_location = queryResult.getString("place_location");
                    String place_description = queryResult.getString("place_description");
                    String place_id = queryResult.getString("id");

                    String[] rowData = {place_id, place_name, place_image, place_location, place_description};
                    placeList.add(rowData);
                    count++;
                }

                if(count == 0){
                    pane1.setOpacity(0);
                    pane2.setOpacity(0);
                    pane3.setOpacity(0);
                    pane4.setOpacity(0);
                    pane5.setOpacity(0);
                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line1.setOpacity(0);
                    line2.setOpacity(0);
                    line3.setOpacity(0);
                    line4.setOpacity(0);
                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                }else if(count == 1){
                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    pane2.setOpacity(0);
                    pane3.setOpacity(0);
                    pane4.setOpacity(0);
                    pane5.setOpacity(0);
                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line1.setOpacity(0);
                    line2.setOpacity(0);
                    line3.setOpacity(0);
                    line4.setOpacity(0);
                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);

                }else if(count == 2){
                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    pane3.setOpacity(0);
                    pane4.setOpacity(0);
                    pane5.setOpacity(0);
                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line2.setOpacity(0);
                    line3.setOpacity(0);
                    line4.setOpacity(0);
                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                }else if(count == 3){
                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    pane4.setOpacity(0);
                    pane5.setOpacity(0);
                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line3.setOpacity(0);
                    line4.setOpacity(0);
                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);

                }else if(count == 4){
                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    pane5.setOpacity(0);
                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line4.setOpacity(0);
                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);

                }else if(count == 5){

                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    String[] row5 = placeList.get(4);
                    Image tempimg5 = new Image(row5[2]);
                    placename5.setText(row5[1]);
                    placeimg5.setFill(new ImagePattern(tempimg5));

                    pane6.setOpacity(0);
                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line5.setOpacity(0);
                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);
                    e5.setOpacity(0);
                    v5.setOpacity(0);

                }else if(count == 6){

                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    String[] row5 = placeList.get(4);
                    Image tempimg5 = new Image(row5[2]);
                    placename5.setText(row5[1]);
                    placeimg5.setFill(new ImagePattern(tempimg5));

                    String[] row6 = placeList.get(5);
                    Image tempimg6 = new Image(row6[2]);
                    placename6.setText(row6[1]);
                    placeimg6.setFill(new ImagePattern(tempimg6));

                    pane7.setOpacity(0);
                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line6.setOpacity(0);
                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);
                    e5.setOpacity(0);
                    v5.setOpacity(0);
                    e6.setOpacity(0);
                    v6.setOpacity(0);

                }else if(count == 7){

                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    String[] row5 = placeList.get(4);
                    Image tempimg5 = new Image(row5[2]);
                    placename5.setText(row5[1]);
                    placeimg5.setFill(new ImagePattern(tempimg5));

                    String[] row6 = placeList.get(5);
                    Image tempimg6 = new Image(row6[2]);
                    placename6.setText(row6[1]);
                    placeimg6.setFill(new ImagePattern(tempimg6));

                    String[] row7 = placeList.get(6);
                    Image tempimg7 = new Image(row7[2]);
                    placename7.setText(row7[1]);
                    placeimg7.setFill(new ImagePattern(tempimg7));

                    pane8.setOpacity(0);
                    pane9.setOpacity(0);

                    line7.setOpacity(0);
                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);
                    e5.setOpacity(0);
                    v5.setOpacity(0);
                    e6.setOpacity(0);
                    v6.setOpacity(0);
                    e7.setOpacity(0);
                    v7.setOpacity(0);

                }else if(count == 8){

                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    String[] row5 = placeList.get(4);
                    Image tempimg5 = new Image(row5[2]);
                    placename5.setText(row5[1]);
                    placeimg5.setFill(new ImagePattern(tempimg5));

                    String[] row6 = placeList.get(5);
                    Image tempimg6 = new Image(row6[2]);
                    placename6.setText(row6[1]);
                    placeimg6.setFill(new ImagePattern(tempimg6));

                    String[] row7 = placeList.get(6);
                    Image tempimg7 = new Image(row7[2]);
                    placename7.setText(row7[1]);
                    placeimg7.setFill(new ImagePattern(tempimg7));

                    String[] row8 = placeList.get(7);
                    Image tempimg8 = new Image(row8[2]);
                    placename8.setText(row8[1]);
                    placeimg8.setFill(new ImagePattern(tempimg8));

                    pane9.setOpacity(0);

                    line8.setOpacity(0);

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);
                    e5.setOpacity(0);
                    v5.setOpacity(0);
                    e6.setOpacity(0);
                    v6.setOpacity(0);
                    e7.setOpacity(0);
                    v7.setOpacity(0);
                    e8.setOpacity(0);
                    v8.setOpacity(0);

                }else if(count >= 9){

                    String[] row1 = placeList.get(0);
                    Image tempimg1 = new Image(row1[2]);
                    placename1.setText(row1[1]);
                    placeimg1.setFill(new ImagePattern(tempimg1));

                    String[] row2 = placeList.get(1);
                    Image tempimg2 = new Image(row2[2]);
                    placename2.setText(row2[1]);
                    placeimg2.setFill(new ImagePattern(tempimg2));

                    String[] row3 = placeList.get(2);
                    Image tempimg3 = new Image(row3[2]);
                    placename3.setText(row3[1]);
                    placeimg3.setFill(new ImagePattern(tempimg3));

                    String[] row4 = placeList.get(3);
                    Image tempimg4 = new Image(row4[2]);
                    placename4.setText(row4[1]);
                    placeimg4.setFill(new ImagePattern(tempimg4));

                    String[] row5 = placeList.get(4);
                    Image tempimg5 = new Image(row5[2]);
                    placename5.setText(row5[1]);
                    placeimg5.setFill(new ImagePattern(tempimg5));

                    String[] row6 = placeList.get(5);
                    Image tempimg6 = new Image(row6[2]);
                    placename6.setText(row6[1]);
                    placeimg6.setFill(new ImagePattern(tempimg6));

                    String[] row7 = placeList.get(6);
                    Image tempimg7 = new Image(row7[2]);
                    placename7.setText(row7[1]);
                    placeimg7.setFill(new ImagePattern(tempimg7));

                    String[] row8 = placeList.get(7);
                    Image tempimg8 = new Image(row8[2]);
                    placename8.setText(row8[1]);
                    placeimg8.setFill(new ImagePattern(tempimg8));

                    String[] row9 = placeList.get(8);
                    Image tempimg9 = new Image(row9[2]);
                    placename9.setText(row9[1]);
                    placeimg9.setFill(new ImagePattern(tempimg9));

                    e1.setOpacity(0);
                    v1.setOpacity(0);
                    e2.setOpacity(0);
                    v2.setOpacity(0);
                    e3.setOpacity(0);
                    v3.setOpacity(0);
                    e4.setOpacity(0);
                    v4.setOpacity(0);
                    e5.setOpacity(0);
                    v5.setOpacity(0);
                    e6.setOpacity(0);
                    v6.setOpacity(0);
                    e7.setOpacity(0);
                    v7.setOpacity(0);
                    e8.setOpacity(0);
                    v8.setOpacity(0);
                    e9.setOpacity(0);
                    v9.setOpacity(0);
                }

            }
            catch(Exception a)
            {
                a.printStackTrace();
            }

        placeimg1.setOnMouseClicked(event -> {
            e1.setOpacity(1);
            v1.setOpacity(1);
        });
        placeimg2.setOnMouseClicked(event -> {
            e2.setOpacity(1);
            v2.setOpacity(1);
        });
        placeimg3.setOnMouseClicked(event -> {
            e3.setOpacity(1);
            v3.setOpacity(1);
        });
        placeimg4.setOnMouseClicked(event -> {
            e4.setOpacity(1);
            v4.setOpacity(1);
        });
        placeimg5.setOnMouseClicked(event -> {
            e5.setOpacity(1);
            v5.setOpacity(1);
        });
        placeimg6.setOnMouseClicked(event -> {
            e6.setOpacity(1);
            v6.setOpacity(1);
        });
        placeimg7.setOnMouseClicked(event -> {
            e7.setOpacity(1);
            v7.setOpacity(1);
        });
        placeimg8.setOnMouseClicked(event -> {
            e8.setOpacity(1);
            v8.setOpacity(1);
        });
        placeimg9.setOnMouseClicked(event -> {
            e9.setOpacity(1);
            v9.setOpacity(1);
        });

        e1.setOnMouseClicked(event -> {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(me);
            translate.setDuration(Duration.millis(1000));
            translate.setByY(-80);
            translate.play();

            me.toFront();
            me.setManaged(false);

            translate.setOnFinished(event1 -> {
                String[] now = placeList.get(0);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e1.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v1.setOnMouseClicked(event -> {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(me);
            translate.setDuration(Duration.millis(1000));
            translate.setByY(-80);
            translate.play();

            me.toFront();
            me.setManaged(false);

            translate.setOnFinished(event1 -> {
                String[] now = placeList.get(0);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v1.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e2.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(700));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1000));
            translateRight.setByX(170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight.setOnFinished(event1 -> {
                String[] now = placeList.get(1);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e2.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v2.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(700));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1000));
            translateRight.setByX(170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight.setOnFinished(event1 -> {
                String[] now = placeList.get(1);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v2.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e3.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(700));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1600));
            translateRight.setByX(340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight.setOnFinished(event1 -> {
                String[] now = placeList.get(2);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e3.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v3.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(700));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1600));
            translateRight.setByX(340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight.setOnFinished(event1 -> {
                String[] now = placeList.get(2);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v3.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e4.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(500));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1300));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(600));
            translateUp1.setByY(-125);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateUp1.setOnFinished(event1 -> {
                String[] now = placeList.get(3);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e4.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v4.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(500));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(1300));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(600));
            translateUp1.setByY(-125);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateUp1.setOnFinished(event1 -> {
                String[] now = placeList.get(3);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v4.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e5.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(300));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(800));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(350));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(450));
            translateLeft.setByX(-170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateLeft.setOnFinished(event1 -> {
                String[] now = placeList.get(4);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e5.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v5.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(300));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(800));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(350));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(450));
            translateLeft.setByX(-170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateLeft.setOnFinished(event1 -> {
                String[] now = placeList.get(4);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v5.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e6.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(300));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(700));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(350));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(700));
            translateLeft.setByX(-340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateLeft.setOnFinished(event1 -> {
                String[] now = placeList.get(5);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e6.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v6.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(300));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(700));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(350));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(700));
            translateLeft.setByX(-340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateLeft.setOnFinished(event1 -> {
                String[] now = placeList.get(5);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v6.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e7.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(200));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(600));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(700));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(400));
            translateUp2.setByY(-130);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateUp2.setOnFinished(event1 -> {
                String[] now = placeList.get(6);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e7.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v7.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(200));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(600));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(700));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(400));
            translateUp2.setByY(-130);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateUp2.setOnFinished(event1 -> {
                String[] now = placeList.get(6);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v7.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e8.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(250));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(550));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(600));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(300));
            translateUp2.setByY(-130);

            TranslateTransition translateRight1 = new TranslateTransition();
            translateRight1.setNode(me);
            translateRight1.setDuration(Duration.millis(400));
            translateRight1.setByX(170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2, translateRight1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight1.setOnFinished(event1 -> {
                String[] now = placeList.get(7);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e8.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v8.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(250));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(550));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(600));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(300));
            translateUp2.setByY(-130);

            TranslateTransition translateRight1 = new TranslateTransition();
            translateRight1.setNode(me);
            translateRight1.setDuration(Duration.millis(400));
            translateRight1.setByX(170);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2, translateRight1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight1.setOnFinished(event1 -> {
                String[] now = placeList.get(7);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v8.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        e9.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(200));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(500));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(600));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(300));
            translateUp2.setByY(-130);

            TranslateTransition translateRight1 = new TranslateTransition();
            translateRight1.setNode(me);
            translateRight1.setDuration(Duration.millis(650));
            translateRight1.setByX(340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2, translateRight1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight1.setOnFinished(event1 -> {
                String[] now = placeList.get(8);
                String place_id = now[0];
                String place_name = now[1];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("contribution.fxml"));
                    Parent root = loader.load();
                    ContributionController contributioncontroller = loader.getController();
                    contributioncontroller.showInformationContribution(me_fullname, me_image, me_id, me_experience, place_id, place_name);
                    Window window = e9.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        v9.setOnMouseClicked(event -> {
            TranslateTransition translateUp = new TranslateTransition();
            translateUp.setNode(me);
            translateUp.setDuration(Duration.millis(200));
            translateUp.setByY(-80);

            TranslateTransition translateRight = new TranslateTransition();
            translateRight.setNode(me);
            translateRight.setDuration(Duration.millis(500));
            translateRight.setByX(340);

            TranslateTransition translateUp1 = new TranslateTransition();
            translateUp1.setNode(me);
            translateUp1.setDuration(Duration.millis(300));
            translateUp1.setByY(-125);

            TranslateTransition translateLeft = new TranslateTransition();
            translateLeft.setNode(me);
            translateLeft.setDuration(Duration.millis(600));
            translateLeft.setByX(-340);

            TranslateTransition translateUp2 = new TranslateTransition();
            translateUp2.setNode(me);
            translateUp2.setDuration(Duration.millis(300));
            translateUp2.setByY(-130);

            TranslateTransition translateRight1 = new TranslateTransition();
            translateRight1.setNode(me);
            translateRight1.setDuration(Duration.millis(650));
            translateRight1.setByX(340);

            SequentialTransition sequentialTransition = new SequentialTransition(translateUp, translateRight, translateUp1,translateLeft, translateUp2, translateRight1);
            sequentialTransition.play();

            me.toFront();
            me.setManaged(false);

            translateRight1.setOnFinished(event1 -> {
                String[] now = placeList.get(8);
                String place_id = now[0];
                String place_name = now[1];
                String place_image = now[2];
                String place_location = now[3];
                String place_description = now[4];

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                    Parent root = loader.load();
                    VisitPlaceController visitplacecontroller = loader.getController();
                    visitplacecontroller.showInformationVisitPlace(me_fullname, me_image, me_id, me_experience, place_id, place_name, place_image, place_location, place_description);
                    Window window = v9.getScene().getWindow();
                    if (window instanceof Stage) {
                        Stage stage = (Stage) window;
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
        });

        System.out.println("got it bro");



    }

    public void showInformation1(String user_fullname,String user_image, String user_id, String user_experience){
        this.me_image = user_image;
        this.me_id = user_id;
        this.me_fullname = user_fullname;
        this.me_experience = user_experience;

        exp.setText(me_experience);
        Image meimg = new Image(me_image);
        me.setFill(new ImagePattern(meimg));

        if(Integer.parseInt(me_experience)<100){
            badgename.setText("Newbie");
            Image bimg = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/newbie.PNG");
            badgeimg.setFill(new ImagePattern(bimg));
        }else if(Integer.parseInt(me_experience)<200){
            badgename.setText("Pathfinder");
            Image bimg = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/pathfinder.jpg");
            badgeimg.setFill(new ImagePattern(bimg));
        }else{
            badgename.setText("Trailblazer");
            Image bimg = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/trailblazer.jpg");
            badgeimg.setFill(new ImagePattern(bimg));
        }



        List<String[]> placeList1 = new ArrayList<>();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore", "root", "");
            pst = con.prepareStatement("SELECT * FROM places");
            ResultSet queryResult = pst.executeQuery();
            int count = 0;
            while (queryResult.next()) {
                String place_id = queryResult.getString("id");
                String[] rowData = {place_id};
                placeList1.add(rowData);
                count++;
            }

            if(count == 1){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

            }else if(count == 2){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }
            }else if(count == 3){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }
            }else if(count == 4){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }
            }else if(count == 5){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }

                String[] now4 = placeList1.get(4);

                if(checkBlog(now4[0], me_id) || checkTransaction(now4[0], me_id)){
                    flag5.setFill(new ImagePattern(img));
                }else{
                    flag5.setOpacity(0);
                }
            }else if(count == 6){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }

                String[] now4 = placeList1.get(4);

                if(checkBlog(now4[0], me_id) || checkTransaction(now4[0], me_id)){
                    flag5.setFill(new ImagePattern(img));
                }else{
                    flag5.setOpacity(0);
                }

                String[] now5 = placeList1.get(5);

                if(checkBlog(now5[0], me_id) || checkTransaction(now5[0], me_id)){
                    flag6.setFill(new ImagePattern(img));
                }else{
                    flag6.setOpacity(0);
                }
            }else if(count == 7){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }

                String[] now4 = placeList1.get(4);

                if(checkBlog(now4[0], me_id) || checkTransaction(now4[0], me_id)){
                    flag5.setFill(new ImagePattern(img));
                }else{
                    flag5.setOpacity(0);
                }

                String[] now5 = placeList1.get(5);

                if(checkBlog(now5[0], me_id) || checkTransaction(now5[0], me_id)){
                    flag6.setFill(new ImagePattern(img));
                }else{
                    flag6.setOpacity(0);
                }

                String[] now6 = placeList1.get(6);

                if(checkBlog(now6[0], me_id) || checkTransaction(now6[0], me_id)){
                    flag7.setFill(new ImagePattern(img));
                }else{
                    flag7.setOpacity(0);
                }
            }else if(count == 8){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }

                String[] now4 = placeList1.get(4);

                if(checkBlog(now4[0], me_id) || checkTransaction(now4[0], me_id)){
                    flag5.setFill(new ImagePattern(img));
                }else{
                    flag5.setOpacity(0);
                }

                String[] now5 = placeList1.get(5);

                if(checkBlog(now5[0], me_id) || checkTransaction(now5[0], me_id)){
                    flag6.setFill(new ImagePattern(img));
                }else{
                    flag6.setOpacity(0);
                }

                String[] now6 = placeList1.get(6);

                if(checkBlog(now6[0], me_id) || checkTransaction(now6[0], me_id)){
                    flag7.setFill(new ImagePattern(img));
                }else{
                    flag7.setOpacity(0);
                }

                String[] now7 = placeList1.get(7);

                if(checkBlog(now7[0], me_id) || checkTransaction(now7[0], me_id)){
                    flag8.setFill(new ImagePattern(img));
                }else{
                    flag8.setOpacity(0);
                }
            }else if(count >= 9){
                String[] now = placeList1.get(0);

                if(checkBlog(now[0], me_id) || checkTransaction(now[0], me_id)){
                    flag1.setFill(new ImagePattern(img));
                }else{
                    flag1.setOpacity(0);
                }

                String[] now1 = placeList1.get(1);

                if(checkBlog(now1[0], me_id) || checkTransaction(now1[0], me_id)){
                    flag2.setFill(new ImagePattern(img));
                }else{
                    flag2.setOpacity(0);
                }

                String[] now2 = placeList1.get(2);

                if(checkBlog(now2[0], me_id) || checkTransaction(now2[0], me_id)){
                    flag3.setFill(new ImagePattern(img));
                }else{
                    flag3.setOpacity(0);
                }

                String[] now3 = placeList1.get(3);

                if(checkBlog(now3[0], me_id) || checkTransaction(now3[0], me_id)){
                    flag4.setFill(new ImagePattern(img));
                }else{
                    flag4.setOpacity(0);
                }

                String[] now4 = placeList1.get(4);

                if(checkBlog(now4[0], me_id) || checkTransaction(now4[0], me_id)){
                    flag5.setFill(new ImagePattern(img));
                }else{
                    flag5.setOpacity(0);
                }

                String[] now5 = placeList1.get(5);

                if(checkBlog(now5[0], me_id) || checkTransaction(now5[0], me_id)){
                    flag6.setFill(new ImagePattern(img));
                }else{
                    flag6.setOpacity(0);
                }

                String[] now6 = placeList1.get(6);

                if(checkBlog(now6[0], me_id) || checkTransaction(now6[0], me_id)){
                    flag7.setFill(new ImagePattern(img));
                }else{
                    flag7.setOpacity(0);
                }

                String[] now7 = placeList1.get(7);

                if(checkBlog(now7[0], me_id) || checkTransaction(now7[0], me_id)){
                    flag8.setFill(new ImagePattern(img));
                }else{
                    flag8.setOpacity(0);
                }

                String[] now8 = placeList1.get(8);

                if(checkBlog(now8[0], me_id) || checkTransaction(now8[0], me_id)){
                    flag9.setFill(new ImagePattern(img));
                }else{
                    flag9.setOpacity(0);
                }

            }

        }catch (Exception a){
            a.printStackTrace();
        }

    }

    public boolean checkBlog(String place_id, String user_id){
        boolean found = false;
        try{
            pst = con.prepareStatement("SELECT * FROM blog WHERE place_id = ? AND user_id = ?");
            pst.setString(1, place_id);
            pst.setString(2, user_id);
            ResultSet queryResult1 = pst.executeQuery();
            if(queryResult1.next()){
                found = true;
            }
        }catch (Exception a){
            a.printStackTrace();
        }
        return found;
    }

    public boolean checkTransaction(String place_id, String user_id){
        boolean found = false;
        try{
            pst = con.prepareStatement("SELECT * FROM transaction WHERE place_id = ? AND user_id = ?");
            pst.setString(1, place_id);
            pst.setString(2, user_id);
            ResultSet queryResult2 = pst.executeQuery();
            if(queryResult2.next()){
                found = true;
            }
        }catch (Exception a){
            a.printStackTrace();
        }
        return found;
    }

    public void onBackButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            HelloController helloController = loader.getController();
            helloController.showInformation(me_fullname,me_image,me_id,me_experience);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }


    public void onGoButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newexplore.fxml"));
            Parent root = loader.load();
            NewExploreController newexplorecontroller = loader.getController();
            newexplorecontroller.showInformationNewExplore(me_fullname,me_image,me_id,me_experience);
            Stage stage = (Stage) gobtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }
}