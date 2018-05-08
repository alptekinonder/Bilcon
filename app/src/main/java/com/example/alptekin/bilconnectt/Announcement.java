package com.example.alptekin.bilconnectt;

/**
 * Created by Alptekin on 21.04.2018.
 */

public class Announcement extends Post{

    private String image_thumb;
    private String image_url;
    private String date;
    private String comment_number;

    public Announcement(){
        //need empty constructor for FireStore Database
    }
    public Announcement(String topic, String Description, String user_id){
        super(topic,Description,user_id);
        comment_number = "0";

    }
    public Announcement(String topic, String Description, String user_id,String image_thumb,String image_url,String date){
        super(topic,Description,user_id);
        this.image_thumb = image_thumb;
        this.image_url = image_url;
        this.date = date;
        comment_number = "0";
    }
    public Announcement(String topic, String Description, String user_id,String image_thumb,String image_url,String date, String comment_number){
        super(topic,Description,user_id);
        this.image_thumb = image_thumb;
        this.image_url = image_url;
        this.date = date;
        this.comment_number = comment_number;
    }


    public String getComment_number(){
        return comment_number;
    }

    @Override
    public String getImage_thumb() {
        return image_thumb;
    }

    @Override
    public String getImage_url() {
        return image_url;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    @Override
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment_number(String comment_number) {
        this.comment_number = comment_number;
    }
}