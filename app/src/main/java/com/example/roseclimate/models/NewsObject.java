package com.example.roseclimate.models;

import androidx.annotation.NonNull;

public class NewsObject implements Comparable<NewsObject> {
    private String title;
    private String link;
    private String pubDate;
    private String source;

    public NewsObject(String title, String link, String pubDate, String source) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSource() {
        return source;
    }

    @Override
    public int compareTo(@NonNull NewsObject newsObject){
        return this.getPubDate().compareTo(newsObject.getPubDate());
    }
}
