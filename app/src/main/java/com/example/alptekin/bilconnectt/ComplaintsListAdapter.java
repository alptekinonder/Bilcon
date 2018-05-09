package com.example.alptekin.bilconnectt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ComplaintsListAdapter extends RecyclerView.Adapter<ComplaintsListAdapter.ViewHolder>// BU KODDA HATALI YERLER VAR ELİFE SORUNCA
{                                                                                               //  DÜZELTİLECEK. ÇALIŞMASINI BEKLEMEYİN
    private List<Complaint> complaintList;
    private Context context;

    public ComplaintsListAdapter(List<Complaint> complaintList)
    {
        this.complaintList = complaintList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        public TextView complaintTitle;
        public TextView complaintDescription;
        public TextView timeElapsed;
        public TextView complaintViewCount;
        private TextView voteCount;
        public ImageView complaintImage;

        public ViewHolder (View itemView){
            super(itemView);
            view = itemView;
            complaintTitle = (TextView) view.findViewById(R.id.complaint_title);
            complaintDescription = (TextView) view.findViewById(R.id.complaint_description);
            timeElapsed = (TextView) view.findViewById(R.id.time_elapsed);
            //complaintViewCount = (TextView) view.findViewById(R.id.comp_view_image);------------------------------1
            //voteCount =  (TextView) view.findViewById(R.id.comp_view_count);--------------------------------------2
        }

        public void setComplaintImage( String thumbUri)
        {
            complaintImage =  (ImageView) view.findViewById(R.id.complaint_image);
            RequestOptions requestOptions = new RequestOptions();
            //requestOptions.placeholder(R.layout.complaint_row);--------------------------------------------------3
            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(thumbUri).into(complaintImage);
            //thumbnail(Glide.with(context).load(thumbUri)).-------------------------------------------------------4
        }


    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.complaint_row,parent,false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(ComplaintsListAdapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.complaintTitle.setText(complaintList.get(position).getTopic());
        holder.complaintDescription.setText(complaintList.get(position).getMiniDescription());
        holder.complaintViewCount.setText(complaintList.get(position).getView_number() + "");
        holder.voteCount.setText(complaintList.get(position).getNumberOfUpVotes() + "");
        // holder.timeElapsed.setText(complaintList.get(position).getTopic());
        String post_thumbUri = complaintList.get(position).getImage_thumb();
        holder.setComplaintImage(post_thumbUri);
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}