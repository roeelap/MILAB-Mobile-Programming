package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.annotation.SuppressLint;

public class SideActivity1 extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] itemList = new String[100];
        for (int i=0; i < 100; i++) {
            itemList[i] = String.format("item %d", i);
        }
        recyclerView.setAdapter(new ListAdapter(itemList));
    }
}