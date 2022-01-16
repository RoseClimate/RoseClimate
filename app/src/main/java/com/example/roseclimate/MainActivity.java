package com.example.roseclimate;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.roseclimate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
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