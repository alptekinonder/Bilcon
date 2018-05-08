package com.example.alptekin.bilconnectt;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Alptekin on 22.04.2018.
 */

public class FireBaseConnector {
    private DatabaseReference mRef;
    public FireBaseConnector(){
    }
    public void postAnnouncement(String topic, String description, String user){
        String id = "1";
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bilconnect-c2053.firebaseio.com/");
        mRef.child("Announcements").child(id).setValue(new Announcement(topic, description,user));
        mRef.child("UserPosts").child("Announcements").child(id).setValue(id);
    }
}
