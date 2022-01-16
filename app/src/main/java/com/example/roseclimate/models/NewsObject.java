package com.example.roseclimate.models;

public class NewsObject {
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
}
