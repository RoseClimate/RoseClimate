package com.example.roseclimate.models;

import java.net.URL;

public class VolunteerOrg {
    private String orgName;
    private URL vurl;
    private String description;
    private String location;


    public VolunteerOrg(String org, URL url, String descrip, String location) {
        this.orgName = org;
        this.vurl = url;
        this.description = descrip;
        this.location = location;
    }

    public String getVolunteerOrg() {
        return orgName;
    }

    public String getDescription() {
        return description;
    }

    public URL getVolunteerURL() {
        return vurl;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "VolunteerOrg{" +
                "orgName='" + orgName + '\'' +
                ", vurl=" + vurl +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
