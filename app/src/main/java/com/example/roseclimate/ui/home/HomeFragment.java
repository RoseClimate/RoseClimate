package com.example.roseclimate.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roseclimate.R;
import com.example.roseclimate.databinding.FragmentHomeBinding;
import com.example.roseclimate.models.Feed;
import com.example.roseclimate.models.FeedItem;
import com.example.roseclimate.models.NewsObject;
import com.example.roseclimate.models.PositivityChecker;
import com.example.roseclimate.models.RSSFeedParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView newsRecyclerView = null;
    private List<NewsObject> newsObjects = new ArrayList<>();
    private Map<String, String> trustedSources = new HashMap<String, String>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // generic code
        homeViewModel =
            new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //our code
        PositivityChecker posCheck = new PositivityChecker();
        trustedSources.put("NASA", "https://climate.nasa.gov/news/rss.xml");
        trustedSources.put("The New York Times", "https://rss.nytimes" +
                ".com/services/xml/rss/nyt/Climate.xml");

        for (Map.Entry<String, String> entry : trustedSources.entrySet()) {
            String source = entry.getKey();
            String rssFeed = entry.getValue();
            RSSFeedParser Parser = new RSSFeedParser(rssFeed);
            Feed feed = Parser.readFeed();
            for (FeedItem item : feed.getItems()) {
                System.out.println(item);
                newsObjects.add(new NewsObject(item.getTitle(), item.getLink(),
                    item.getPubDate(), source));
            }
        }
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
        newsRecyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.news_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        newsRecyclerView.setAdapter(new NewsRecyclerViewAdapter(newsObjects));
    }
}