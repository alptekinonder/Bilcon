package com.example.alptekin.bilconnectt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TabHost;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    TabHost tabHost;
    TabHost.TabSpec announcementsTab, complaintBoxTab;
    ImageButton sortingButton;
    ImageButton profileButton;
    ImageButton newPostButton;
    ImageButton settingsButton;
    ImageButton notificationsButton;

    private RecyclerView announcementsRecyclerView;
    private FirebaseFirestore firebaseFirestore;
    private String TAG = "FireLog";
    private List<Announcement> announcementsList;
    private AnnouncementsListAdapter announcementsListAdapter;

    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        sortingButton = (ImageButton) findViewById(R.id.sortingButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        newPostButton = (ImageButton) findViewById(R.id.newPostButton);
        settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        notificationsButton = (ImageButton) findViewById(R.id.notificationsButton);

        //Setup the viewpager
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager((mViewPager));


        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomePage.this,NewPost.class);
                startActivity(intent);
                finish();
            }
        });


        /**sortingButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent( HomePage.this,Sorting.class);
        startActivity(intent);
        finish();
        }
        });
         profileButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent( HomePage.this,myProfile.class);
        startActivity(intent);
        finish();
        }
        });

         notificationsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent( HomePage.this,Notifiations.class);
        startActivity(intent);
        finish();
        }
        }); */

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomePage.this, SettingsActivity.class);
                startActivity( intent);
                finish();
            }
        });
        /**
         mySwipeFreshLayout = (SwipeRefreshLayout) findViewById( R.id.mySwipe);
         mySwipeFreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
        //mySwipeFreshLayout.setRefreshing( false);
        }
        }, 4000);
        }
        });
         */
    }
    public void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new announcementsFragment(), "ANNOUNCEMENTS");
        adapter.addFragment(new complaintsFragment(),"COMPLAINT BOX");
        viewPager.setAdapter(adapter);
    }

    private void refreshItems(){

        //...

        announcementsListAdapter = new AnnouncementsListAdapter(announcementsList);
        announcementsRecyclerView.setAdapter(announcementsListAdapter);

        //...

        //   mySwipeFreshLayout.setRefreshing( false);
    }
}