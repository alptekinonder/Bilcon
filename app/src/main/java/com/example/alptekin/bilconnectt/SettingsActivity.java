package com.example.alptekin.bilconnectt;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class SettingsActivity extends Activity {

    //Properties
    private ImageView     settings_background;
    private Button        settings_go_changePassword;
    private Button        settings_go_contactUs;
    private Button        settings_go_aboutUs;
    private ToggleButton  settings_notifications;
    private TextView      settings_title;
    private Button        settings_goBack;
    private FirebaseAuth  mAuth;
    private Button        signOut;
    //the Variables about notifications

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_title             = (TextView) findViewById( R.id.about_title);
        settings_background        = (ImageView) findViewById( R.id.settings_background);
        settings_go_aboutUs        = (Button) findViewById( R.id.settings_goAboutUsButton);
        settings_go_contactUs      = (Button) findViewById( R.id.settings_goContactUsButton);
        settings_go_changePassword = (Button) findViewById( R.id.settings_goCahangePasswordButton);
        settings_notifications     = (ToggleButton) findViewById( R.id.settings_setNotifications);
        settings_goBack            = (Button) findViewById( R.id.settings_goBackButton);
        mAuth                      = FirebaseAuth.getInstance();
        signOut                    = (Button) findViewById( R.id.sign_out);

        //setting Notifications
        settings_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( settings_notifications.isChecked()){ //To do...

                    Toast.makeText( getApplicationContext(), "You have set the notifications on!", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText( getApplicationContext(), "You have set the notifications off!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //Other go Buttons
        settings_go_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent goAboutUs = new Intent( Settings.this, AboutUs.class);
                //startActivity( goAboutUs);
                //finish();
            }
        });

        settings_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent goBack = new Intent( Settings.this, HomePage.class);
                //startActivity( goBack);
                //finish();
            }
        });

        settings_go_contactUs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent goContactUs = new Intent( Settings.this, ContactUs.class);
                //startActivity( goContactUs);
                //finish();
            }
        });

        settings_go_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changePassword();

            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent( SettingsActivity.this, LoginActivity.class);
                startActivity( intent);
                finish();
            }
        });

    }

    //Methods
    private void changePassword(){
        final String email = mAuth.getCurrentUser().getEmail().toString();

        FirebaseAuth.getInstance().sendPasswordResetEmail( email ).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( @NonNull Task<Void> task)
            {
                if ( task.isSuccessful() )
                {
                    Toast.makeText(SettingsActivity.this, "An email send to " + email + " to reset password.", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String error = task.getException().getMessage();
                    Toast.makeText(SettingsActivity.this, "error: " + error, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
