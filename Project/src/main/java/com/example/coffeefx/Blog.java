package com.example.coffeefx;

public class Blog {
    private String id;
    private String place_id;
    private String blog;
    private String blog_image;
    private String blog_datetime;
    private String username;
    private String userid;
    private String userimage;
    private String like;
    private String unlike;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceid() {
        return place_id;
    }

    public void setPlaceid(String place_id) {
        this.place_id = place_id;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getBlogimage() {
        return blog_image;
    }

    public void setBlogimage(String blog_image) {
        this.blog_image = blog_image;
    }

    public String getBlogdatetime() {
        return blog_datetime;
    }

    public void setBlogdatetime(String blog_datetime) {
        this.blog_datetime = blog_datetime;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getUnlike() {
        return unlike;
    }

    public void setUnlike(String unlike) {
        this.unlike = unlike;
    }
}
