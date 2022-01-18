package com.example.roseclimate.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roseclimate.Cache;
import com.example.roseclimate.R;
import com.example.roseclimate.databinding.FragmentHomeBinding;
import com.example.roseclimate.models.Feed;
import com.example.roseclimate.models.FeedItem;
import com.example.roseclimate.models.NewsObject;
import com.example.roseclimate.models.PositivityChecker;
import com.example.roseclimate.models.RSSFeedParser;
import com.example.roseclimate.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView newsRecyclerView = null;
    private List<NewsObject> newsObjects = new ArrayList<>();
    private Map<String, String> trustedSources = new HashMap<String, String>();
    private PositivityChecker posCheck;

    private int articleMax = 25;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // generic code
        homeViewModel =
            new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //our code
        posCheck = new PositivityChecker();
        trustedSources.put("NASA", "https://climate.nasa.gov/news/rss.xml");
//        trustedSources.put("The New York Times", "https://rss.nytimes" +
//                ".com/services/xml/rss/nyt/Climate.xml");
        trustedSources.put("The Guardian", "https://www.theguardian" +
            ".com/environment/climate-crisis/rss");
        trustedSources.put("The New York Times", "https://rss.nytimes" +
            ".com/services/xml/rss/nyt/Climate.xml");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        View root = binding.getRoot();
        ProgressBar pb = root.findViewById(R.id.circularProgressIndicator);
        int articleCount = 0;
        if (((Cache) getActivity().getApplication()).getNewsObjects() == null) {
            pb.setVisibility(View.VISIBLE);
            for (Map.Entry<String, String> entry : trustedSources.entrySet()) {
                String source = entry.getKey();
                String rssFeed = entry.getValue();
                RSSFeedParser Parser = new RSSFeedParser(rssFeed);
                Feed feed = Parser.readFeed();
                if (articleCount > articleMax){
                    break;
                }
                for (FeedItem item : feed.getItems()) {
                    if (articleCount > articleMax){
                        break;
                    }
                    if (posCheck.articleIsPositive(item.getGuid())){
                        newsObjects.add(new NewsObject(item.getTitle(), item.getGuid(),
                            item.getPubDate(), source));
                        articleCount++;
                    }

                }
            }
            ((Cache) getActivity().getApplication()).setNewsObjects(newsObjects);
        } else {
            newsObjects = ((Cache) getActivity().getApplication()).getNewsObjects();
        }
        pb.setVisibility(View.GONE);
        setRecyclerView();
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
        Collections.sort(newsObjects, Collections.reverseOrder());
        NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter(newsObjects);
        newsRecyclerView.setAdapter(adapter);
        newsRecyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(ctx, newsRecyclerView ,
                new RecyclerItemClickListener.OnItemClickListener() {
                @Override public void onItemClick(View view, int position) {
                    String url = newsObjects.get(position).getLink();
                    Intent myIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                    Log.d("Opening url", url);
                    assert ctx != null;
                    ctx.startActivity(myIntent);

                }

                @Override public void onLongItemClick(View view, int position) {
                    newsObjects.remove(position);
                    newsRecyclerView.getRecycledViewPool().clear();
                    adapter.notifyDataSetChanged();
                }
            })
        );
    }
//
//    @Override
//    public void newsRecyclerViewListClicked(View v, int position) {
//        Intent myIntent = new Intent(Intent.ACTION_VIEW,
//            Uri.parse(newsObjects.get(position).getLink()));
//        v.getContext().startActivity(myIntent);
//
//    }
}