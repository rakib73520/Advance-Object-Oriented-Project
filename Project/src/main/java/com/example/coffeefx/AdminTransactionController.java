package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTransactionController implements Initializable {

    @FXML
    private Button backbtn;

    @FXML
    private VBox vbox;

    Connection con;
    PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Transaction> transactions = new ArrayList<>(transactions());

        for(int i=0;i<transactions.size();i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("extraadmintransaction.fxml"));

            try{
                AnchorPane anchorpane = loader.load();
                ExtraAdminTransactionController extraadmintransactioncontroller = loader.getController();
                extraadmintransactioncontroller.setData(transactions.get(i));

                Button deleteBtn = extraadmintransactioncontroller.getRejectButton();
                Button addBtn = extraadmintransactioncontroller.getApproveButton();
                String id = transactions.get(i).getId();
                String userid = transactions.get(i).getUserid();

                deleteBtn.setOnAction(event -> {
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                        pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                        pst.setString(1, userid);
                        ResultSet queryResult = pst.executeQuery();
                        if(queryResult.next()){
                            int experience = queryResult.getInt("experience");
                            experience-=15;

                            pst = con.prepareStatement("DELETE FROM transaction WHERE id = ?");
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

                                FXMLLoader newLoader = new FXMLLoader(getClass().getResource("admintransaction.fxml"));
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
                            experience+=30;

                            pst = con.prepareStatement("UPDATE transaction SET verify = '1' WHERE id = ?");
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
                                successAlert.setContentText("Added Successfully");
                                successAlert.showAndWait();

                                FXMLLoader newLoader = new FXMLLoader(getClass().getResource("admintransaction.fxml"));
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

    private List<Transaction> transactions(){
        List<Transaction> ls = new ArrayList<>();

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
            pst = con.prepareStatement("SELECT * FROM transaction WHERE verify = '0'");
            ResultSet queryResult = pst.executeQuery();

            while (queryResult.next()) {
                String id = queryResult.getString("id");
                String way = queryResult.getString("way");
                String place_id = queryResult.getString("place_id");
                String place_name = queryResult.getString("place_name");
                String user_image = queryResult.getString("user_image");
                String user_name = queryResult.getString("user_name");
                String user_id = queryResult.getString("user_id");
                String verify = queryResult.getString("verify");

                Transaction transactionsnow = new Transaction();
                transactionsnow.setId(id);
                transactionsnow.setWay(way);
                transactionsnow.setPlaceid(place_id);
                transactionsnow.setPlacename(place_name);
                transactionsnow.setUsername(user_name);
                transactionsnow.setUserid(user_id);
                transactionsnow.setUserimage(user_image);
                transactionsnow.setVerify(verify);

                ls.add(transactionsnow);
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

