package com.example.roseclimate;

import android.os.Bundle;
import android.widget.TextView;

import com.example.roseclimate.models.FeedReader;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.roseclimate.databinding.ActivityMainBinding;
import com.example.roseclimate.models.PositivityChecker;

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
        TextView textView1 = findViewById(R.id.volunteerText);
        TextView textView2 = findViewById(R.id.newsText);
        textView1.setText("Hi");
        textView2.setText("Hi2");

        PositivityChecker posCheck = new PositivityChecker();
        String articleNeg = "https://www.theguardian.com/environment/2022/jan/15/global-heating" +
            "-linked-early-birth-damage-babies-health";
        String articlePos = "https://climate.nasa.gov/ask-nasa-climate/3075/nasa-technologies" +
            "-spin" +
            "-off-to-fight-climate-change/";
        textView1.setText(posCheck.articleIsPositive(articlePos).toString());
    }

}