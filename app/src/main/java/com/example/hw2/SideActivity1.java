package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.annotation.SuppressLint;

public class SideActivity1 extends AppCompatActivity {

    private final Movie[] movieList = {
            new Movie("The Shawshank Redemption", "9.2", R.drawable.shawshank_redemption),
            new Movie("The Godfather", "9.2", R.drawable.the_godfather),
            new Movie("The Dark Knight", "9.0", R.drawable.the_dark_knight),
            new Movie("The Godfather Part II", "9.0", R.drawable.the_godfather_2),
            new Movie("12 Angry Men", "9.0", R.drawable.angry_men),
            new Movie("Schindler's List", "8.9", R.drawable.schindler_list),
            new Movie("The Lord of the Rings: The Return of the King", "8.9", R.drawable.lotr_the_return_of_the_king),
            new Movie("Pulp Fiction", "8.8", R.drawable.pulp_fiction),
            new Movie("The Lord of the Rings: The Fellowship of the Ring", "8.8", R.drawable.lotr_the_fellowship_of_the_ring),
            new Movie("The Good, the Bad and the Ugly", "8.8", R.drawable.the_good_the_bad)
    };

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(this, movieList));
    }
}