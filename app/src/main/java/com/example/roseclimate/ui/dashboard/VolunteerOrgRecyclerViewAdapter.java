package com.example.roseclimate.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roseclimate.R;
import com.example.roseclimate.models.VolunteerOrg;
import com.example.roseclimate.models.VolunteerOrgList;

public class VolunteerOrgRecyclerViewAdapter extends RecyclerView.Adapter<VolunteerOrgRecyclerViewAdapter.ViewHolder>{

    private VolunteerOrgList orgList;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView locationText;
        private TextView tagsText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.org_title);
            locationText = itemView.findViewById(R.id.org_location);
            tagsText = itemView.findViewById(R.id.editTags);

        }

        public TextView getTitleText() {
            return titleText;
        }

        public TextView getLocationText() {
            return locationText;
        }

        public TextView getTagsText() {
            return tagsText;
        }
    }

    public VolunteerOrgRecyclerViewAdapter(VolunteerOrgList orgList) {
        this.orgList = orgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VolunteerOrg volunteerOrg = orgList.getVolunteerOrgList().get(position);
        holder.getTitleText().setText(volunteerOrg.getVolunteerOrg());
        holder.getTagsText().setText(volunteerOrg.getTags());
        holder.getLocationText().setText(volunteerOrg.getLocation());
    }

    @Override
    public int getItemCount() {
        return orgList.getVolunteerOrgList().size();
    }

    public int getItemViewType(final int position) {
        return R.layout.one_org;
    }



}
