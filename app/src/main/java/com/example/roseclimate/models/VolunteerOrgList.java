package com.example.roseclimate.models;

import androidx.annotation.NonNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VolunteerOrgList implements Iterable<VolunteerOrg>{
    private List<VolunteerOrg> volunteerOrgList;

    public void addVolunteerOrg(VolunteerOrg volunteerOrg) {
        volunteerOrgList.add(volunteerOrg);
    }

    public VolunteerOrgList() {
        volunteerOrgList = new ArrayList<>();
    }

    public List<VolunteerOrg> getVolunteerOrgList() {
        return volunteerOrgList;
    }

    public List<VolunteerOrg> sortByLocation(String location) {
        List<VolunteerOrg> locationList = new ArrayList<>();
        for (VolunteerOrg org: volunteerOrgList) {
            if (org.getLocation() == location) {
                locationList.add(org);
            }
        }
        return locationList;

    }

    @NonNull
    @Override
    public Iterator<VolunteerOrg> iterator() {
        return volunteerOrgList.iterator();
    }
}
