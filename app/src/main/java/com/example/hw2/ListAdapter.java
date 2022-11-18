package com.example.hw2;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {

    private String[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(TextView view) {
            super(view);
            textView = view;
        }
    }

    public ListAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        String textForDisplay = dataSet[position];
        ((ViewHolder)viewHolder).textView.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
