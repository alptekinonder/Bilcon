package com.example.alptekin.bilconnectt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        /*Bu kısım bitecek salı akşamına, takılmayın
        announcementsFragment announcementsFragment = new announcementsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.tabcontent,announcementsFragment );
*/

        sortingButton = (ImageButton) findViewById(R.id.sortingButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        newPostButton = (ImageButton) findViewById(R.id.newPostButton);
        settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        notificationsButton = (ImageButton) findViewById(R.id.notificationsButton);



        announcementsList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        announcementsListAdapter = new AnnouncementsListAdapter(announcementsList);


        announcementsRecyclerView = (RecyclerView) findViewById(R.id.announcements_list);
        announcementsRecyclerView.setHasFixedSize(true); // for speed purpose
        announcementsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        announcementsRecyclerView.setAdapter(announcementsListAdapter);

        //retrieving data from collection
        firebaseFirestore.collection("Announcements").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                //if there is any error
                if( e != null)
                {
                    Log.d(TAG, e.getMessage());
                }
                for(DocumentChange doc: documentSnapshots.getDocumentChanges())
                {
                    Announcement announcement = doc.getDocument().toObject(Announcement.class);
                    Log.d(TAG, announcement.getTopic());
                    announcementsList.add(announcement);
                }

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomePage.this, SettingsActivity.class);
                startActivity( intent);
                finish();
            }
        });

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomePage.this, NewPost.class);
                startActivity( intent);
                finish();
            }
        });


    }
}
