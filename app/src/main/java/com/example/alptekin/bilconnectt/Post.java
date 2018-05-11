package com.example.alptekin.bilconnectt;

/**
 * Created by Alptekin on 28.04.2018.
 */

public class Post {
    private String topic;
    private String description;
    private String user_id;
    private String image_thumb;
    private String image_url;
    private String date;
    private String view_number;
    private String postId;
    private String profileThumbnail;
    public Post(){
        //need empty constructor for FireStore Database
    }
    public Post(String topic, String Description, String user_id, String postId, String profileThumbnail){
        this.topic = topic;
        this.user_id = user_id;
        this.description = Description;
        this.postId = postId;
        this.profileThumbnail = profileThumbnail;
        view_number = "0";
        image_thumb = "";
        image_url = "";

    }
    public String getMiniDescription()
    {
        if( description != null){
            if(description.length() <= 140)
            {
                return description;
            }else {
                return description.substring(0,137) + "...";
            }
        }
        return "";
    }
    public Post(String topic, String Description, String user_id,String image_thumb,String image_url,String date){
        this.topic = topic;
        this.user_id = user_id;
        this.description = Description;
        this.image_thumb = image_thumb;
        this.image_url = image_url;
        this.date = date;
        view_number = "0";
    }

    public Post(String topic, String Description, String user_id,String image_thumb,String image_url,String date,String view_number){
        this.topic = topic;
        this.user_id = user_id;
        this.description = Description;
        this.image_thumb = image_thumb;
        this.image_url = image_url;
        this.date = date;
        this.view_number = view_number;
    }

    public String getTopic(){ return topic; }
    public String getDescription(){return description;}
    public String getUser_id(){ return user_id; }
    public String getImage_thumb(){ return image_thumb; }
    public String getImage_url(){ return image_url; }
    public String getDate(){ return date; }
    public String getView_number(){return view_number;}
    public void setImage_thumb(String image_thumb){
        this.image_thumb = image_thumb;
    }
    public void setImage_url(String image_url){ this.image_url = image_url; }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setView_number(String view_number) {
        this.view_number = view_number;
    }

    public String getPostId() {
        return postId;
    }

    public String getProfileThumbnail() {
        return profileThumbnail;
    }

    public void setProfileThumbnail(String profileThumbnail) {
        this.profileThumbnail = profileThumbnail;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}