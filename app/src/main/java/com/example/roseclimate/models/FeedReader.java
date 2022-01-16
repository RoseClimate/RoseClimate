package com.example.roseclimate.models;


import java.net.URL;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class FeedReader {
    private SyndFeed feed;

    public void setFeed(String url) {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> _loadFeed(url));
        try {
            String result = completableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String _loadFeed(String url) {
        boolean ok = false;
        try {
            URL feedURL = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedURL));

            System.out.println(feed);

            ok = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }


        if (!ok) {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }
        return "success";
    }

    public SyndFeed getFeed() {
        return feed;
    }

}
