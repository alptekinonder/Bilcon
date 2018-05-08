    package com.example.alptekin.bilconnectt;

    /**
     * Created by Alptekin on 28.04.2018.
     */

    public class Complaint extends Post{
        private String image_thumb;
        private String image_url;
        private String date;
        private String numberOfComments;
        private Number numberOfUpVotes;
        private Number numberOfDownVotes;
        public Complaint(){
            //need empty constructor for FireStore Database
        }
        public Complaint(String topic, String Description, String user_id){
            super(topic,Description,user_id);
           numberOfUpVotes = 0;
           numberOfDownVotes = 0;

        }
        public Complaint(String topic, String Description, String user_id,String image_thumb,String image_url,String date){

            this.image_thumb = image_thumb;
            this.image_url = image_url;
            this.date = date;
            numberOfUpVotes = 0;
            numberOfDownVotes = 0;
        }

        public Number getNumberOfUpVotes(){
            return numberOfUpVotes;
        }
        public Number getNumberOfDownVotes(){
            return numberOfDownVotes;
        }

        @Override
        public String getImage_thumb() {
            return image_thumb;
        }

        @Override
        public void setImage_thumb(String image_thumb) {
            this.image_thumb = image_thumb;
        }

        @Override
        public String getImage_url() {
            return image_url;
        }

        @Override
        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        @Override
        public String getDate() {
            return date;
        }

        @Override
        public void setDate(String date) {
            this.date = date;
        }

        public String getNumberOfComments() {
            return numberOfComments;
        }

        public void setNumberOfComments(String numberOfComments) {
            this.numberOfComments = numberOfComments;
        }

        public void setNumberOfUpVotes(Number numberOfUpVotes) {
            this.numberOfUpVotes = numberOfUpVotes;
        }

        public void setNumberOfDownVotes(Number numberOfDownVotes) {
            this.numberOfDownVotes = numberOfDownVotes;
        }
    }

