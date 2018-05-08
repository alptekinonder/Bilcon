package com.example.alptekin.bilconnectt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alptekin on 29.04.2018.
 */

public class AnnouncementsListAdapter  extends RecyclerView.Adapter<AnnouncementsListAdapter.ViewHolder>{
    private List<Announcement> announcementList;
    public AnnouncementsListAdapter(List<Announcement> announcementList){
        this.announcementList = announcementList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public TextView topic;
        public TextView description;
        public TextView comments;
        public TextView viewNumber;

        public ViewHolder (View itemView){
            super(itemView);
            mview = itemView;
            topic = (TextView) mview.findViewById(R.id.topicText);
            description = (TextView) mview.findViewById(R.id.descriptionText);
            comments = (TextView) mview.findViewById(R.id.comment_count);
            viewNumber =  (TextView) mview.findViewById(R.id.view_count);

        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnnouncementsListAdapter.ViewHolder holder, int position) {
        holder.topic.setText(announcementList.get(position).getTopic());
        holder.description.setText(announcementList.get(position).getDescription());
        holder.viewNumber.setText(announcementList.get(position).getView_number() + "");
        holder.comments.setText(announcementList.get(position).getComment_number() + "");
        holder.topic.setText(announcementList.get(position).getTopic());
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }
}
