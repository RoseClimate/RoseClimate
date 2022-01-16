package com.example.roseclimate.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;

public class NewsObject implements Comparable<NewsObject>, Serializable {
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
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

        Date thisdate = null;
        Date otherdate = null;
        try {
            thisdate = sdf.parse(this.getPubDate());
            otherdate = sdf.parse(newsObject.getPubDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return thisdate.compareTo(otherdate);
    }

    @Override
    public String toString() {
        return "NewsObject [title=" + title + ", link="+ link + ", pubDate=" + pubDate + ", " +
            "source" + "=" + source+"]";
    }
}
