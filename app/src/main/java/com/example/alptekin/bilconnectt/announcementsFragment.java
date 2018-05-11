package com.example.alptekin.bilconnectt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alptekin on 7.05.2018.
 */

public class announcementsFragment extends Fragment {

    private RecyclerView announcementsRecyclerView;
    private FirebaseFirestore firebaseFirestore;
    private String TAG = "FireLog";
    private List<Announcement> announcementsList;
    private AnnouncementsListAdapter announcementsListAdapter;
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_announcement_fragment,container,false);
        announcementsList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        announcementsListAdapter = new AnnouncementsListAdapter(announcementsList);

        announcementsRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_announcements);
        announcementsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        announcementsRecyclerView.setAdapter(announcementsListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        announcementsRecyclerView.setLayoutManager(llm);



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

        announcementsRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
        //retrieving data from collection

    }
}
