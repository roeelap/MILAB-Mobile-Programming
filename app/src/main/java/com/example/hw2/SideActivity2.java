package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class SideActivity2 extends AppCompatActivity {

    private final Movie[] movieList = {
            new Movie("1. Disaster Movie", "2.1", R.drawable.disaster_movie),
            new Movie("2. Manos: The Hands of Fate", "2.2", R.drawable.manos),
            new Movie("3. Birdemic: Shock and Terror", "2.3", R.drawable.birdemic),
            new Movie("4. Superbabies: Baby Geniuses 2", "2.3", R.drawable.superbabies),
            new Movie("5. The Hottie & the Nottie", "2.4", R.drawable.the_hottie),
            new Movie("6. House of the Dead", "2.4", R.drawable.house_of_the_dead),
            new Movie("7. Son of the Mask", "2.5", R.drawable.son_of_the_mask),
            new Movie("8. Saving Christmas", "2.5", R.drawable.saving_christmas),
            new Movie("9. Epic Movie", "2.5", R.drawable.epic_movie),
            new Movie("10. Battlefield Earth", "2.6", R.drawable.battlefield_earth)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(this, movieList));
    }
}