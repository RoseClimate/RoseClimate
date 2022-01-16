package com.example.roseclimate.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roseclimate.R;
import com.example.roseclimate.databinding.FragmentDashboardBinding;
import com.example.roseclimate.models.VolunteerOrg;
import com.example.roseclimate.models.VolunteerOrgList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private RecyclerView volunteerOrgRecyclerView;
    private VolunteerOrgList orgList = new VolunteerOrgList();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
            new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        VolunteerOrg org1 = new VolunteerOrg("spec", "https://spec.bc.ca/volunteer/", "Vancouver");
        VolunteerOrg org2 = new VolunteerOrg("HUB Cycling", "https://www.idealist.org/en/volunteer-opportunity/07e6f31503814b82bf74076ac4148409-youth-advisory-committee-member-hub-cycling-vancouver?utm_source=volunteer-match&amp;utm_medium=feed&amp;utm_campaign=listings-api", "Vancouver");
        orgList.addVolunteerOrg(org1);
        orgList.addVolunteerOrg(org2);
        setVolunteerOrgRecyclerView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setVolunteerOrgRecyclerView() {
        Context ctx = getActivity();
        volunteerOrgRecyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.v_recycler);
        volunteerOrgRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        volunteerOrgRecyclerView.setAdapter(new VolunteerOrgRecyclerViewAdapter(orgList));
    }
}