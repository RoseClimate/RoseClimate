package com.example.roseclimate;

import android.app.Application;

import com.example.roseclimate.models.NewsObject;

import java.util.List;

public class Cache extends Application {
    private List<NewsObject> newsObjects;

    public List<NewsObject> getNewsObjects() {
        return newsObjects;
    }
    public Cache(){
        newsObjects = null;
    }
    public void setNewsObjects(List<NewsObject> newNews) {
        this.newsObjects = newNews;
    }
}
