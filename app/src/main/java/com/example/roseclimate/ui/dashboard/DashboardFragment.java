package com.example.roseclimate.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.example.roseclimate.ui.RecyclerItemClickListener;

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

        VolunteerOrg org1 = new VolunteerOrg("spec", "https://spec.bc.ca/volunteer/", "Vancouver", "Food, Energy, Gardening, Waste");
        VolunteerOrg org2 = new VolunteerOrg("HUB Cycling", "https://www.idealist.org/en/volunteer-opportunity/07e6f31503814b82bf74076ac4148409-youth-advisory-committee-member-hub-cycling-vancouver?utm_source=volunteer-match&amp;utm_medium=feed&amp;utm_campaign=listings-api", "Vancouver", "Transportation");
        VolunteerOrg org3 = new VolunteerOrg("BEST", "https://www.best.bc.ca/support-best/volunteer", "Vancouver", "Transportation");
        VolunteerOrg org4 = new VolunteerOrg("Coastal First Nations", "https://coastalfirstnations.ca/our-communities/careers/", "Vancouver", "Preservation, Education");
        VolunteerOrg org5 = new VolunteerOrg("David Suzuki Foundation", "https://davidsuzuki.org/take-action/act-locally/", "Vancouver", "Community Engagement, Activism, Habitat");
        VolunteerOrg org6 = new VolunteerOrg("Earthsave Canada", "https://www.earthsave.ca/how-to-help/join-us/", "Vancouver", "Food, Education");
        VolunteerOrg org7 = new VolunteerOrg("EYA", "https://eya.ca/volunteer/", "Vancouver", "Education, Community Engagement");
        VolunteerOrg org8 = new VolunteerOrg("Robin B. Clark Inc.", "http://www.rbc.bc.ca/contact-us-naturalresourceconsultants/", "Vancouver", "Forestry, Ecosystems");
        VolunteerOrg org9 = new VolunteerOrg("FarmFolk CityFolk", "https://farmfolkcityfolk.ca/get-involved/volunteer/", "Vancouver", "Agriculture");
        VolunteerOrg org10 = new VolunteerOrg("Fored BC", "https://www.foredbc.org/common/main.cfm?ind=3&sin=3&ssi=0", "Vancouver", "Sustainability, Community Engagement, Education");
        VolunteerOrg org11 = new VolunteerOrg("Greenpeace", "https://www.greenpeace.org/canada/en/act/", "Canada", "Education");
        VolunteerOrg org12 = new VolunteerOrg("MGABC", "http://mgabc.org/chapters", "British Columbia", "Gardening");
        VolunteerOrg org13 = new VolunteerOrg("Pacific Salmon Foundation", "https://psf.ca/volunteer/", "Vancouver", "Conservation");
        VolunteerOrg org14 = new VolunteerOrg("Stanley Park Ecology Society", "https://stanleyparkecology.ca/about/volunteer/", "Vancouver", "Conservation, Education");
        VolunteerOrg org15 = new VolunteerOrg("Wilderness Committee", "https://www.wildernesscommittee.org/volunteer", "Vancouver", "Wilderness. Preservation");


        orgList.addVolunteerOrg(org1);
        orgList.addVolunteerOrg(org2);
        orgList.addVolunteerOrg(org3);
        orgList.addVolunteerOrg(org4);
        orgList.addVolunteerOrg(org5);
        orgList.addVolunteerOrg(org6);
        orgList.addVolunteerOrg(org7);
        orgList.addVolunteerOrg(org8);
        orgList.addVolunteerOrg(org9);
        orgList.addVolunteerOrg(org10);
        orgList.addVolunteerOrg(org11);
        orgList.addVolunteerOrg(org12);
        orgList.addVolunteerOrg(org13);
        orgList.addVolunteerOrg(org14);
        orgList.addVolunteerOrg(org15);



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
        VolunteerOrgRecyclerViewAdapter adapter = new VolunteerOrgRecyclerViewAdapter(orgList);
        volunteerOrgRecyclerView.setAdapter(adapter);
        volunteerOrgRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ctx, volunteerOrgRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String url = orgList.getVolunteerOrgList().get(position).getVolunteerURL();
                        Intent myIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(url));
                        Log.d("Opening url", url);
                        assert ctx != null;
                        ctx.startActivity(myIntent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        orgList.getVolunteerOrgList().remove(position);
                        volunteerOrgRecyclerView.getRecycledViewPool().clear();
                        adapter.notifyDataSetChanged();
                    }

                })
        );
    }


}