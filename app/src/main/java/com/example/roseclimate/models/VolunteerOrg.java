package com.example.roseclimate.models;

import java.net.URL;

public class VolunteerOrg {
    private final String orgName;
    private final String vurl;
    private final String location;


    public VolunteerOrg(String org, String url, String location) {
        this.orgName = org;
        this.vurl = url;
        this.location = location;
    }

    public String getVolunteerOrg() {
        return orgName;
    }


    public String getVolunteerURL() {
        return vurl;
    }

    public String getLocation() {
        return location;
    }


}

