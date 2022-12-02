package com.example.hw2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter {

    Context context;
    private Movie[] movieList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView score;
        private final ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            score = view.findViewById(R.id.score);
            image = view.findViewById(R.id.image);
        }
    }

    public MovieListAdapter(Context context, Movie[] movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Movie movie = movieList[position];
        ((ViewHolder)viewHolder).name.setText(movie.name);
        ((ViewHolder)viewHolder).name.setTypeface(null, Typeface.BOLD_ITALIC);
        ((ViewHolder)viewHolder).score.setText(movie.score);
        ((ViewHolder)viewHolder).image.setImageResource(movie.image);
    }

    @Override
    public int getItemCount() {
        return movieList.length;
    }
}
