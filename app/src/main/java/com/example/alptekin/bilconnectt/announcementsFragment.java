package com.example.alptekin.bilconnectt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alptekin on 7.05.2018.
 */

public class announcementsFragment extends Fragment {
    @Nullable
    private RecyclerView recyclerView;
    private AnnouncementsListAdapter announcementsListAdapter;
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_announcement_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_announcements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(announcementsListAdapter);
        return view;
    }
}
