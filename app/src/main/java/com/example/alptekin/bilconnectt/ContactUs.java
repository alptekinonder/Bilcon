package com.example.alptekin.bilconnectt;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Date;

public class ContactUs extends AppCompatActivity {
    private ImageButton buttonBack;
    private TextView sendText;
    private ImageButton sendButton;
    private EditText topicText;
    private EditText detailsText;
    private TextView note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        sendButton  = (ImageButton) findViewById( R.id.send_it);
        buttonBack  = (ImageButton) findViewById( R.id.backButton);
        sendText    = (TextView) findViewById( R.id.send);
        topicText   = (EditText) findViewById( R.id.topic_text);
        detailsText = (EditText) findViewById( R.id.details_text);
        note        = (TextView) findViewById( R.id.contact_us_note);

        //FireBase operations

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ContactUs.this, SettingsActivity.class);
                startActivity( intent);
                finish();
            }
        });

    }

    //Methods
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
