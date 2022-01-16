package com.example.roseclimate.ui.home;

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

import com.example.roseclimate.R;
import com.example.roseclimate.databinding.FragmentHomeBinding;
import com.example.roseclimate.models.PositivityChecker;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
            new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView1 = binding.volunteerText;
        TextView textView2 = binding.newsText;
        textView1.setText("Hi");
        textView2.setText("Hi2");

        PositivityChecker posCheck = new PositivityChecker();
        String articleNeg = "https://www.theguardian.com/environment/2022/jan/15/global-heating" +
            "-linked-early-birth-damage-babies-health";
        String articlePos = "https://climate.nasa.gov/ask-nasa-climate/3075/nasa-technologies" +
            "-spin" +
            "-off-to-fight-climate-change/";
        textView1.setText(posCheck.articleIsPositive(articlePos).toString());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}