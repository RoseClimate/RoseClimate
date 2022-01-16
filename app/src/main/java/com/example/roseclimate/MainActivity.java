package com.example.roseclimate;

import android.os.Bundle;
import android.widget.TextView;

import com.example.roseclimate.models.Feed;
import com.example.roseclimate.models.FeedItem;
import com.example.roseclimate.models.FeedReader;
import com.example.roseclimate.models.NewsObject;
import com.example.roseclimate.models.RSSFeedParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.roseclimate.databinding.ActivityMainBinding;
import com.example.roseclimate.models.PositivityChecker;

import java.util.ArrayList;
//import com.ibm.watson.natural_language_understanding.v1.model.Feed;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        RSSFeedParser NasaParser = new RSSFeedParser("https://climate.nasa.gov/news/rss.xml");

        ArrayList<NewsObject> newsObjectArray = new ArrayList<>();

        Feed nasaFeed = NasaParser.readFeed();
        System.out.println(nasaFeed);
        for (FeedItem item : nasaFeed.getItems()) {
            System.out.println(item);
            newsObjectArray.add(new NewsObject(item.getTitle(), item.getGuid(),
                    item.getPubDate(), "NASA"));
        }

        RSSFeedParser NYParser = new RSSFeedParser(
                "https://rss.nytimes.com/services/xml/rss/nyt/Climate.xml");

        Feed nyFeed = NYParser.readFeed();
        System.out.println(nyFeed);
        for (FeedItem item : nyFeed.getItems()) {
            System.out.println(item);
            newsObjectArray.add(new NewsObject(item.getTitle(), item.getGuid(),
                    item.getPubDate(), "The New York Times"));
        }

    }

}