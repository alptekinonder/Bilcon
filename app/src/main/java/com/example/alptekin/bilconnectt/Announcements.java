package com.example.alptekin.bilconnectt;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class Announcements extends AppCompatActivity {

    ListView myListView;
    String[] announcements;
    String[] view;
    String[] descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements);
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        announcements = res.getStringArray(R.array.annoucements);
        view = res.getStringArray(R.array.View);
        descriptions = res.getStringArray(R.array.description);
        ItemAdapterAnnouncements itemAdapter = new ItemAdapterAnnouncements(this, announcements, view,descriptions);
        myListView.setAdapter(itemAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("com.example.alptekin.listview.INDEX",i);
                startActivity(showDetailActivity);
            }
        });
        init();
        goProfile();
        goHomePage();
    }

    public void init(){
        Button logIn = (Button) findViewById(R.id.Menu);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Announcements.this,NewPost.class);
                startActivity(go);
            }
        });
    }
    public void goProfile(){
        Button profile = (Button) findViewById(R.id.profileButton);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Announcements.this,profile.class);
                startActivity(go);
            }
        });
    }
    public void goHomePage(){
        Button goHome = (Button) findViewById(R.id.goHome);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Announcements.this,HomePage.class);
                startActivity(go);
            }
        });
    }

   /* public void goComplaints(){
        Button complaints = (Button) findViewById(R.id.Complaints);
        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            @Override

        });
    }*/
}
