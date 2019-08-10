package com.example.android.popularmovies;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.TheMovieDBAPI.FavoriteFilmsRepository;
import com.example.android.popularmovies.TheMovieDBAPI.Films;
import com.example.android.popularmovies.TheMovieDBAPI.FilmsViewModel;

import java.util.List;

public class FavoriteFilmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_favorite);

        LiveData<List<Films>>  mFavoriteFilms;

        FilmsViewModel filmsViewModel = ViewModelProviders.of(this).get(FilmsViewModel.class);

        mFavoriteFilms = filmsViewModel.getFavoriteFilmsList();

        final TextView noFavorites = findViewById(R.id.no_favorites_tv);
        final RecyclerView recyclerview = findViewById(R.id.recycler_view_favorite_films_activity);

        mFavoriteFilms.observe(this, new Observer<List<Films>>(){
            @Override
            public void onChanged(@Nullable List<Films> films) {

                if (films.isEmpty()){
                    //set main view to show textview for empty list
                    noFavorites.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);

                }else {

                    //set main view type, and layout manager
                    noFavorites.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);

                    final RecyclerView mRecyclerView;
                    RecyclerView.LayoutManager mLayoutManager;
                    mRecyclerView = findViewById(R.id.recycler_view_favorite_films_activity);
                    mLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(new FilmRecyclerViewAdapter(films, FavoriteFilmsActivity.this));
                }
            }
        });
    }
}
