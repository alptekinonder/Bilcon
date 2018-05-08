package com.example.alptekin.bilconnectt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private String TAG = "FireLog";
    private List<Announcement> announcementList;
    private AnnouncementsListAdapter announcementsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        firestore = FirebaseFirestore.getInstance();
        announcementList = new ArrayList<>();
        announcementsListAdapter = new AnnouncementsListAdapter(announcementList);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(announcementsListAdapter);
        //retriving data from announcements collection
        firestore.collection("Announcements").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                //if there is any error
                if(e != null){
                    Log.d(TAG, e.getMessage());
                }
                //it will get each snapshot and stroe it in doc variable
                //if there is any change, it will only get the
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    Announcement announcement = doc.getDocument().toObject(Announcement.class);
                    Log.d(TAG, announcement.getTopic());
                    announcementList.add(announcement);
                }
            }
        });
    }
}
