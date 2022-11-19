package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class SideActivity2 extends AppCompatActivity {

    private final ListItem[] movieList = {
            new ListItem("The Shawshank Redemption", R.drawable.shawshank_redemption),
            new ListItem("The Godfather",R.drawable.the_godfather),
            new ListItem("The Dark Knight",R.drawable.the_dark_knight),
            new ListItem("The Godfather Part II",R.drawable.the_godfather_2),
            new ListItem("12 Angry Men",R.drawable.angry_men),
            new ListItem("Schindler's List",R.drawable.schindler_list),
            new ListItem("The Lord of the Rings: The Return of the King",R.drawable.lotr_the_return_of_the_king),
            new ListItem("Pulp Fiction",R.drawable.pulp_fiction),
            new ListItem("The Lord of the Rings: The Fellowship of the Ring",R.drawable.lotr_the_fellowship_of_the_ring),
            new ListItem("The Good, the Bad and the Ugly",R.drawable.the_good_the_bad)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(this, movieList));
    }
}