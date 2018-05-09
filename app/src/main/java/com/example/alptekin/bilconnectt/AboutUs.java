package com.example.alptekin.bilconnectt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
    private TextView aboutUs_title;
    private Button aboutUs_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutUs_title  = (TextView) findViewById( R.id.aboutUs_title);
        aboutUs_goBack = (Button) findViewById( R.id.about_us_go_back);

        aboutUs_goBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goSettings = new Intent( AboutUs.this, SettingsActivity.class);
                startActivity( goSettings);
                finish();
            }
        });
    }
}
