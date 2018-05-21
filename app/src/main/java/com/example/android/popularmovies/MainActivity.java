package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mFilmsAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        ArrayList<Films> mFilmsList = new ArrayList<>();

        mFilmsList.add(new Films("Thor Ragnaroc and the little dog from italy that said no way hose I'm not doing this film", 5, "Action Adventure"));
        mFilmsList.add(new Films("Don't Watch this", 1, "Comedy"));
        mFilmsList.add(new Films("Thor Ragnaroc", 5, "Action Adventure"));
        mFilmsList.add(new Films("Don't Watch this", 1, "Comedy"));
        mFilmsList.add(new Films("Thor Ragnaroc and the little dog from italy that said no way hose I'm not doing this film", 5, "Action Adventure"));
        mFilmsList.add(new Films("Don't Watch this", 1, "Comedy"));
        mFilmsList.add(new Films("Thor Ragnaroc", 5, "Action Adventure"));
        mFilmsList.add(new Films("Don't Watch this", 1, "Comedy"));




        mRecyclerView = findViewById(R.id.recycler_view_main_activity);

        mLayoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mFilmsAdapter = new FilmRecyclerViewAdapter(mFilmsList, this);
        mRecyclerView.setAdapter(mFilmsAdapter);

    }
}
