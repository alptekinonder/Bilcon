package com.example.alptekin.bilconnectt;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setContentView(R.layout.activity_detail);
        //Make this activity pop up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height * 0.8));

        //
        Intent in = getIntent();
        TextView topicView = (TextView) findViewById(R.id.Topic);
        TextView descriptionView = (TextView) findViewById(R.id.Description);
        TextView viewView = (TextView) findViewById(R.id.ViewSoFar);

        int index = in.getIntExtra("com.example.alptekin.listview.INDEX" , -1);
        Resources res = getResources();
        String topic = res.getStringArray(R.array.annoucements)[index];
        String description = res.getStringArray(R.array.description)[index];
        String View = res.getStringArray(R.array.View)[index];
        topicView.setText(topic);
        descriptionView.setText(description);
        viewView.setText(View);
    }
}
