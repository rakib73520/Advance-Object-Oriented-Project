package com.example.coffeefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContributionController {

    @FXML
    private Button addbtn;

    @FXML
    private Button backbtn;

    @FXML
    private TextArea blog;

    @FXML
    private Label errormsg;

    @FXML
    private Label errormsg1;

    @FXML
    private Label fullName11;

    @FXML
    private Label fullName111;

    @FXML
    private Label fullName1111;

    @FXML
    private Label fullName11111;

    @FXML
    private Button imagebutton;

    @FXML
    private Label imagelable;

    @FXML
    private TextField initiallocation;

    @FXML
    private TextField steps;

    @FXML
    private Button uploadbtn;

    private String user_fullname;
    private String user_image;
    private String user_id;
    private String user_experience;
    private String place_id;
    private String place_name;

    Connection con;
    PreparedStatement pst;

    @FXML
    void onAddButtonClicked(ActionEvent event) {
        int numSteps = 0;
        String tempNumSteps = steps.getText();
        String initialLocation = initiallocation.getText();
        StringBuilder transactionDetails = new StringBuilder();

        try {
            numSteps = Integer.parseInt(tempNumSteps);
        } catch (NumberFormatException a) {
        }

        final String[] lastLocation = {initialLocation}; // Initialize last location with the initial location

        final int[] stepCount = {1};
        final AtomicBoolean allInputsGiven = new AtomicBoolean(true);

        System.out.println("number : " + numSteps);
        System.out.println("location : " + initialLocation);

        if(numSteps > 0 && !Objects.equals(initialLocation, "")){
            for (int i = 1; i <= numSteps; i++) {
                Dialog<String[]> dialog = new Dialog<>();
                dialog.setTitle("Step " + i + " Details");
                dialog.setHeaderText("Enter details for Step " + i);

                TextField destination = new TextField();
                destination.setPromptText("Destination Location");

                TextField vehicle = new TextField();
                vehicle.setPromptText("Vehicle Used");

                dialog.getDialogPane().setContent(new VBox(10, new Label("Destination:"), destination,
                        new Label("Vehicle:"), vehicle));

                ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);

                final int stepNumber = stepCount[0];
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == buttonTypeOk) {
                        return new String[]{destination.getText(), vehicle.getText()};
                    }
                    return null;
                });

                int finalNumSteps = numSteps;
                dialog.showAndWait().ifPresent(result -> {
                    if (result[0].isEmpty() || result[1].isEmpty()) {
                        allInputsGiven.set(false);
                    }
                    transactionDetails.append(stepNumber).append(". ")
                            .append(lastLocation[0]).append(" ---> ")
                            .append(result[0]).append(" (by ").append(result[1]).append(")");
                    if (stepCount[0] != finalNumSteps) {
                        transactionDetails.append("  ");
                    }
                    lastLocation[0] = result[0]; // Update the last location for the next step
                    stepCount[0]++;

                });
            }

            if (allInputsGiven.get()) {

                String transactionString = transactionDetails.toString();
                System.out.println(transactionString);

                try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                    pst = con.prepareStatement("INSERT INTO transaction (way, place_id, place_name, user_id, user_name, user_image, verify) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    pst.setString(1, transactionString);
                    pst.setString(2, place_id);
                    pst.setString(3, place_name);
                    pst.setString(4, user_id);
                    pst.setString(5, user_fullname);
                    pst.setString(6, user_image);
                    pst.setString(7, "0");
                    pst.executeUpdate();
                    errormsg1.setText("Transaction Updated");
                }catch (Exception a){
                    a.printStackTrace();
                }
            } else {
                errormsg1.setText("Transaction Update Failed");
            }
        }else{
            errormsg1.setText("All Fields Required");
        }

        steps.setText("");
        initiallocation.setText("");

    }

    @FXML
    void onBackButtonClicked(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myexploration.fxml"));
            Parent root = loader.load();
            MyExplorationController myexplorationcontroller = loader.getController();
            myexplorationcontroller.showInformation1(user_fullname,user_image,user_id,user_experience);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
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

    @FXML
    void onUploadButtonClicked(ActionEvent event) {
        String myblog = blog.getText();
        String myblogimage = "file:///";
        myblogimage+=imagelable.getText();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = currentDateTime.format(formatter);

        if(!Objects.equals(myblog, "") && !Objects.equals(myblogimage, "file:///")){
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                pst = con.prepareStatement("INSERT INTO blog (place_id, myblog, myblog_image, myblog_datetime, user_id, user_name, user_image) VALUES (?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, place_id);
                pst.setString(2, myblog);
                pst.setString(3, myblogimage);
                pst.setString(4, datetime);
                pst.setString(5, user_id);
                pst.setString(6, user_fullname);
                pst.setString(7, user_image);
                pst.executeUpdate();
                errormsg.setText("Blog Uploaded!");
                blog.setText("");
                imagelable.setText("");
            }catch (Exception a){
                a.printStackTrace();
            }
        }else{
            errormsg.setText("All Fields Are Required!");
        }
    }

    public void showInformationContribution(String meFullname, String meImage, String meId, String meExperience, String placeId, String placeName) {
        this.user_fullname = meFullname;
        this.user_image = meImage;
        this.user_id = meId;
        this.user_experience = meExperience;
        this.place_id = placeId;
        this.place_name = placeName;
    }
}

