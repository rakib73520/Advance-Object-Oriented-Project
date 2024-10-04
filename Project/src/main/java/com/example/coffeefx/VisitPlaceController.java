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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.css.Rect;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class VisitPlaceController implements Initializable {

    @FXML
    private Button backbtn;

    @FXML
    private Rectangle placeimage;

    @FXML
    private Label placelocation;

    @FXML
    private Label placename;

    @FXML
    private Label placedescription;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    private String placeid;
    private String user_name;
    private String user_image;
    private String user_id;
    private String user_experience;


    Connection con;
    PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void init1(String uname,String uimage,String uid,String uexperience,String pid,String pname,String pimage,String plocation,String pdescription){
        List<Blog> blogs = new ArrayList<>(blogs());
        for (int i = 0; i < blogs.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visitplaceblog.fxml"));
            try {
                AnchorPane anchorpane = loader.load();
                VisitPlaceBlogController visitplaceblogcontroller = loader.getController();
                visitplaceblogcontroller.setData(blogs.get(i));

                String userid = blogs.get(i).getUserid();
                String id = blogs.get(i).getId();
                int likecount = Integer.parseInt(blogs.get(i).getLike());
                int unlikecount = Integer.parseInt(blogs.get(i).getUnlike());

                Circle likebtn = visitplaceblogcontroller.getLikeButton();
                Circle unlikebtn = visitplaceblogcontroller.getUnlikeButton();
                Image likebtnimage = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/like.png");
                Image unlikebtnimage = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/unlike.jpg");
                likebtn.setFill(new ImagePattern(likebtnimage));
                unlikebtn.setFill(new ImagePattern(unlikebtnimage));

                likebtn.setOnMouseClicked(event -> {
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                        pst = con.prepareStatement("SELECT * FROM likeunlike WHERE blog_id = ? AND my_id = ?");
                        pst.setString(1, id);
                        pst.setString(2, uid);
                        ResultSet queryResult = pst.executeQuery();

                        if(queryResult.next()){
                            String do_unlike = queryResult.getString("do_unlike");
                            String likeunlike_id = queryResult.getString("id");
                            if(do_unlike.equals("1")){
                                pst = con.prepareStatement("SELECT * FROM blog WHERE id = ?");
                                pst.setString(1, id);
                                ResultSet queryResult1 = pst.executeQuery();
                                if(queryResult1.next()){
                                    int likeCount = queryResult1.getInt("like_count");
                                    int unlikeCount = queryResult1.getInt("unlike_count");
                                    likeCount+=1;
                                    unlikeCount-=1;

                                    pst = con.prepareStatement("UPDATE blog SET like_count = ?, unlike_count = ? WHERE id = ?");
                                    pst.setInt(1, likeCount);
                                    pst.setInt(2, unlikeCount);
                                    pst.setString(3, id);
                                    pst.executeUpdate();

                                    pst = con.prepareStatement("UPDATE likeunlike SET do_like = ?, do_unlike = ? WHERE id = ?");
                                    pst.setString(1, "1");
                                    pst.setString(2, "0");
                                    pst.setString(3, likeunlike_id);
                                    pst.executeUpdate();

                                    pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                                    pst.setString(1, userid);
                                    ResultSet queryResult2 = pst.executeQuery();
                                    if(queryResult2.next()){
                                        int experience = queryResult2.getInt("experience");
                                        experience+=3;
                                        pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");
                                        pst.setInt(1, experience);
                                        pst.setString(2, userid);
                                        pst.executeUpdate();

                                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                                        Parent root = loader1.load();
                                        VisitPlaceController visitplacecontroller = loader1.getController();
                                        visitplacecontroller.showInformationVisitPlace(uname, uimage, uid, uexperience, pid, pname, pimage, plocation, pdescription);
                                        Window window = likebtn.getScene().getWindow();
                                        if (window instanceof Stage) {
                                            Stage stage = (Stage) window;
                                            stage.setScene(new Scene(root));
                                            stage.show();
                                        }

                                    }
                                }
                            }
                        }else{
                            pst = con.prepareStatement("SELECT * FROM blog WHERE id = ?");
                            pst.setString(1, id);
                            ResultSet queryResult1 = pst.executeQuery();
                            if(queryResult1.next()){
                                int likeCount = queryResult1.getInt("like_count");
                                likeCount+=1;
                                pst = con.prepareStatement("UPDATE blog SET like_count = ? WHERE id = ?");
                                pst.setInt(1, likeCount);
                                pst.setString(2, id);
                                pst.executeUpdate();

                                pst = con.prepareStatement("INSERT INTO likeunlike (blog_id, my_id, do_like, do_unlike) VALUES (?, ?, ?, ?)");
                                pst.setString(1,  id);
                                pst.setString(2, uid);
                                pst.setString(3, "1");
                                pst.setString(4, "0");
                                pst.executeUpdate();

                                pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                                pst.setString(1, userid);
                                ResultSet queryResult2 = pst.executeQuery();
                                if(queryResult2.next()){
                                    int experience = queryResult2.getInt("experience");
                                    experience+=3;
                                    pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");
                                    pst.setInt(1, experience);
                                    pst.setString(2, userid);
                                    pst.executeUpdate();

                                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                                    Parent root = loader1.load();
                                    VisitPlaceController visitplacecontroller = loader1.getController();
                                    visitplacecontroller.showInformationVisitPlace(uname, uimage, uid, uexperience, pid, pname, pimage, plocation, pdescription);
                                    Window window = likebtn.getScene().getWindow();
                                    if (window instanceof Stage) {
                                        Stage stage = (Stage) window;
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                    }
                                }

                            }
                        }

                        con.close();
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                });

                unlikebtn.setOnMouseClicked(event -> {
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore","root","");
                        pst = con.prepareStatement("SELECT * FROM likeunlike WHERE blog_id = ? AND my_id = ?");
                        pst.setString(1, id);
                        pst.setString(2, uid);
                        ResultSet queryResult = pst.executeQuery();

                        if(queryResult.next()){
                            String do_like = queryResult.getString("do_like");
                            String likeunlike_id = queryResult.getString("id");
                            if(do_like.equals("1")){
                                pst = con.prepareStatement("SELECT * FROM blog WHERE id = ?");
                                pst.setString(1, id);
                                ResultSet queryResult1 = pst.executeQuery();
                                if(queryResult1.next()){
                                    int likeCount = queryResult1.getInt("like_count");
                                    int unlikeCount = queryResult1.getInt("unlike_count");
                                    likeCount-=1;
                                    unlikeCount+=1;

                                    pst = con.prepareStatement("UPDATE blog SET like_count = ?, unlike_count = ? WHERE id = ?");
                                    pst.setInt(1, likeCount);
                                    pst.setInt(2, unlikeCount);
                                    pst.setString(3, id);
                                    pst.executeUpdate();

                                    pst = con.prepareStatement("UPDATE likeunlike SET do_like = ?, do_unlike = ? WHERE id = ?");
                                    pst.setString(1, "0");
                                    pst.setString(2, "1");
                                    pst.setString(3, likeunlike_id);
                                    pst.executeUpdate();

                                    pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                                    pst.setString(1, userid);
                                    ResultSet queryResult2 = pst.executeQuery();
                                    if(queryResult2.next()){
                                        int experience = queryResult2.getInt("experience");
                                        experience-=3;
                                        pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");
                                        pst.setInt(1, experience);
                                        pst.setString(2, userid);
                                        pst.executeUpdate();

                                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                                        Parent root = loader1.load();
                                        VisitPlaceController visitplacecontroller = loader1.getController();
                                        visitplacecontroller.showInformationVisitPlace(uname, uimage, uid, uexperience, pid, pname, pimage, plocation, pdescription);
                                        Window window = likebtn.getScene().getWindow();
                                        if (window instanceof Stage) {
                                            Stage stage = (Stage) window;
                                            stage.setScene(new Scene(root));
                                            stage.show();
                                        }

                                    }
                                }
                            }
                        }else{
                            pst = con.prepareStatement("SELECT * FROM blog WHERE id = ?");
                            pst.setString(1, id);
                            ResultSet queryResult1 = pst.executeQuery();
                            if(queryResult1.next()){
                                int unlikeCount = queryResult1.getInt("unlike_count");
                                unlikeCount+=1;
                                pst = con.prepareStatement("UPDATE blog SET unlike_count = ? WHERE id = ?");
                                pst.setInt(1, unlikeCount);
                                pst.setString(2, id);
                                pst.executeUpdate();

                                pst = con.prepareStatement("INSERT INTO likeunlike (blog_id, my_id, do_like, do_unlike) VALUES (?, ?, ?, ?)");
                                pst.setString(1,  id);
                                pst.setString(2, uid);
                                pst.setString(3, "0");
                                pst.setString(4, "1");
                                pst.executeUpdate();

                                pst = con.prepareStatement("SELECT experience FROM userinfo WHERE id = ?");
                                pst.setString(1, userid);
                                ResultSet queryResult2 = pst.executeQuery();
                                if(queryResult2.next()){
                                    int experience = queryResult2.getInt("experience");
                                    experience-=3;
                                    pst = con.prepareStatement("UPDATE userinfo SET experience = ? WHERE id = ?");
                                    pst.setInt(1, experience);
                                    pst.setString(2, userid);
                                    pst.executeUpdate();

                                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("visitplace.fxml"));
                                    Parent root = loader1.load();
                                    VisitPlaceController visitplacecontroller = loader1.getController();
                                    visitplacecontroller.showInformationVisitPlace(uname, uimage, uid, uexperience, pid, pname, pimage, plocation, pdescription);
                                    Window window = likebtn.getScene().getWindow();
                                    if (window instanceof Stage) {
                                        Stage stage = (Stage) window;
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                    }

                                }
                            }
                        }

                        con.close();
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                });

                vbox2.getChildren().add(anchorpane);
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }

    public void init() {
        List<Transaction> transactions = new ArrayList<>(transactions());
        for (int i = 0; i < transactions.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visittransaction.fxml"));
            try {
                AnchorPane anchorpane = loader.load();
                VisitTransactionController visittransactioncontroller = loader.getController();
                visittransactioncontroller.setData(transactions.get(i));
                Circle verifyCircle = visittransactioncontroller.getVerify();
                String verify = transactions.get(i).getVerify();
                if(verify.equals("1")){
                    Image image = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/right.png");
                    verifyCircle.setFill(new ImagePattern(image));
                }else{
                    Image image = new Image("file:///C:/Users/Lenovo/IdeaProjects/coffeeFx/src/main/resources/com/example/image/wait.png");
                    verifyCircle.setFill(new ImagePattern(image));
                }
                vbox1.getChildren().add(anchorpane);
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }

    private List<Transaction> transactions() {
        List<Transaction> ls = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore", "root", "");
            pst = con.prepareStatement("SELECT * FROM transaction WHERE place_id = ? ORDER BY verify DESC");
            pst.setString(1, placeid);
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
        } catch (Exception a) {
            a.printStackTrace();
        }
        return ls;
    }

    private List<Blog> blogs() {
        List<Blog> ls = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/easy_explore", "root", "");
            pst = con.prepareStatement("SELECT * FROM blog WHERE place_id = ? ORDER BY like_count DESC");
            pst.setString(1, placeid);
            ResultSet queryResult = pst.executeQuery();

            while (queryResult.next()) {
                String id = queryResult.getString("id");
                String place_id = queryResult.getString("place_id");
                String myblog = queryResult.getString("myblog");
                String myblog_image = queryResult.getString("myblog_image");
                String myblog_datetime = queryResult.getString("myblog_datetime");
                String user_name = queryResult.getString("user_name");
                String user_id = queryResult.getString("user_id");
                String user_image = queryResult.getString("user_image");
                String like_count = queryResult.getString("like_count");
                String unlike_count = queryResult.getString("unlike_count");

                Blog blog = new Blog();
                blog.setId(id);
                blog.setPlaceid(place_id);
                blog.setBlog(myblog);
                blog.setBlogdatetime(myblog_datetime);
                blog.setBlogimage(myblog_image);
                blog.setUserid(user_id);
                blog.setUserimage(user_image);
                blog.setUsername(user_name);
                blog.setLike(like_count);
                blog.setUnlike(unlike_count);

                ls.add(blog);
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return ls;
    }

    @FXML
    void onBackButtonClicked(ActionEvent event) {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("myexploration.fxml"));
            Parent root = loader.load();
            MyExplorationController myexplorationcontroller = loader.getController();
            myexplorationcontroller.showInformation1(user_name,user_image,user_id,user_experience);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }

    public void showInformationVisitPlace(String userName, String userImage, String userId, String userExperience, String placeId, String placeName, String placeImage, String placeLocation, String placeDescription) {
        this.user_name = userName;
        this.user_image = userImage;
        this.user_id = userId;
        this.user_experience = userExperience;
        this.placeid = placeId;

        placename.setText(placeName);
        Image placeimg = new Image(placeImage);
        placeimage.setFill(new ImagePattern(placeimg));
        placelocation.setText(placeLocation);
        placedescription.setText(placeDescription);
        init();
        init1(user_name,user_image,user_id,user_experience,placeId,placeName,placeImage,placeLocation,placeDescription);
    }
}
