package com.example.alptekin.bilconnectt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if ( currentUser == null)
        {
            sendToLogin();
        }
        else
        {
            sendToHomePage();
        }
    }

    private void sendToHomePage() {
        Intent intent = new Intent( MainActivity.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void sendToLogin()
    {
        Intent intent = new Intent( MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

