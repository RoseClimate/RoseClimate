package com.example.roseclimate.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roseclimate.R;
import com.example.roseclimate.models.NewsObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<NewsObject> articles;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView sourceText;
        private TextView dateText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.news_article_title);
            sourceText = itemView.findViewById(R.id.source_text);
            dateText = itemView.findViewById(R.id.editTextDate);
        }

        public TextView getTitleText() {
            return titleText;
        }

        public TextView getSourceText() {
            return sourceText;
        }

        public TextView getDateText() {
            return dateText;
        }
    }

    public NewsRecyclerViewAdapter(List<NewsObject> articles) {
       this.articles = articles;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsObject newsObject = articles.get(position);
        holder.getTitleText().setText(newsObject.getTitle());
        holder.getDateText().setText(newsObject.getPubDate());
        holder.getSourceText().setText(newsObject.getSource());
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.news_recycler_item;
    }
}

