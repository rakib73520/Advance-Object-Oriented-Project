package com.example.coffeefx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

public class VisitPlaceBlogController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Label myblog;

    @FXML
    private Rectangle blogimage;

    @FXML
    private Label datetime;

    @FXML
    private Circle like;

    @FXML
    private Label likecount;

    @FXML
    private Circle unlike;

    @FXML
    private Label unlikecount;

    @FXML
    private Circle userimage;

    @FXML
    private Label username;

    public void setData(Blog blog){
        username.setText(blog.getUsername());
        myblog.setText(blog.getBlog());
        Image img = new Image(blog.getUserimage());
        userimage.setFill(new ImagePattern(img));
        datetime.setText(blog.getBlogdatetime());
        Image img1 = new Image(blog.getBlogimage());
        blogimage.setFill(new ImagePattern(img1));
        likecount.setText(blog.getLike());
        unlikecount.setText(blog.getUnlike());
    }

    public Circle getLikeButton() {
        return like;
    }

    public Circle getUnlikeButton() {
        return unlike;
    }

}

