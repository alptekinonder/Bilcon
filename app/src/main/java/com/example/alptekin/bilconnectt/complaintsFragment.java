package com.example.alptekin.bilconnectt;

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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alptekin on 9.05.2018.
 */

public class complaintsFragment extends Fragment {
    private RecyclerView complaintRecyclerView;
    private FirebaseFirestore firebaseFirestore;
    private String TAG = "FireLog";
    private List<Complaint> complaintsList;
    private ComplaintsListAdapter complaintsListAdapter;

    public complaintsFragment(){ }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_complaints_fragment, container, false);
        complaintsList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        complaintsListAdapter = new ComplaintsListAdapter( complaintsList);

        complaintRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewComplaint);
        complaintRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        complaintRecyclerView.setAdapter(complaintsListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        complaintRecyclerView.setLayoutManager(llm);


        firebaseFirestore.collection("Complaints").addSnapshotListener( getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                //if there is any error
                if (e != null) {
                    Log.d(TAG, e.getMessage());
                }
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        String cTopic = doc.getDocument().getString("Topic");
                        String cDescription = doc.getDocument().getString("Description");
                        String cDate        = doc.getDocument().getString("Date");
                        String cImageThumb  = doc.getDocument().getString("image_thumb");
                        String cImageUrl    = doc.getDocument().getString("image_url");
                        String cNumberOfDownVotes = doc.getDocument().getString("numberOfDownVotes");
                        String cNumberOfUpVotes = doc.getDocument().getString("numberOfUpVotes");
                        String cUserId = doc.getDocument().getString("user_id");


                        //Log.d(TAG, complaint.getTopic());
                        complaintsList.add( new Complaint(cTopic, cDescription, cUserId, cImageThumb, cImageUrl, cDate));
                    }
                }

            }
        });

        return view;
        //retrieving data from collection
    }

}