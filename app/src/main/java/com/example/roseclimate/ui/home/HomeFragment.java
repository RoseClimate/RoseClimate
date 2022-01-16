package com.example.roseclimate.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roseclimate.R;
import com.example.roseclimate.databinding.FragmentHomeBinding;
import com.example.roseclimate.models.NewsObject;
import com.example.roseclimate.models.PositivityChecker;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView newsRecyclerView = null;
    private List<NewsObject> newsObjects = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
            new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        TextView textView1 = binding.volunteerText;
//        TextView textView2 = binding.newsText;
//        textView1.setText("Hi");
//        textView2.setText("Hi2");

        PositivityChecker posCheck = new PositivityChecker();
        String articleNeg = "https://www.theguardian.com/environment/2022/jan/15/global-heating" +
            "-linked-early-birth-damage-babies-health";
        String articlePos = "https://climate.nasa.gov/ask-nasa-climate/3075/nasa-technologies" +
            "-spin" +
            "-off-to-fight-climate-change/";
//        textView1.setText(posCheck.articleIsPositive(articlePos).toString());
        NewsObject newsObject1 = new NewsObject("NASA Technologies Spin off to Fight Climate " +
            "Change", "https://climate.nasa.gov/ask-nasa-climate/3075/nasa-technologies-spin-off" +
            "-to-fight-climate-change/",
            "April 21, 2021", "NASA");
        NewsObject newsObject2 = new NewsObject("NASA Technologies Punt off to Fight Climate " +
            "Change", "https://climate.nasa.gov/ask-nasa-climate/3075/nasa-technologies-spin-off" +
            "-to-fight-climate-change/",
            "April 21, 2021", "NASA");
        newsObjects.add(newsObject1);
        newsObjects.add(newsObject2);
        setRecyclerView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setRecyclerView() {
        Context ctx = getActivity();
        newsRecyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.lesson_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        newsRecyclerView.setAdapter(new NewsRecyclerViewAdapter(newsObjects));
    }
}