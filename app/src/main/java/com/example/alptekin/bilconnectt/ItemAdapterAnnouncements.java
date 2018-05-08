package com.example.alptekin.bilconnectt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Alptekin on 27.03.2018.
 */

public class ItemAdapterAnnouncements extends BaseAdapter {

    LayoutInflater mInflater;
    String[] announcements;
    String[] vieww;
    String[] description;

    public ItemAdapterAnnouncements(Context c, String[] i, String[] p, String[] d){
        announcements = i;
        vieww = p;
        description = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return announcements.length;
    }

    @Override
    public Object getItem(int i) {
        return announcements[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.my_listview_detail,null);
        TextView announcementView = (TextView) v.findViewById(R.id.Name);
        TextView viewView = (TextView) v.findViewById(R.id.View);
        TextView decriptionView = (TextView) v.findViewById(R.id.Description);
        String name = announcements[i];
        String desc = description[i] ;
        String vie = vieww[i] + "\n";
        announcementView.setText(name);
        decriptionView.setText(desc);
        viewView.setText(vie);
        return v;
    }
}

