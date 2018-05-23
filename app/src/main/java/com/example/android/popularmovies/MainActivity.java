package com.example.android.popularmovies;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.popularmovies.TheMovieDBAPI.Films;
import com.example.android.popularmovies.TheMovieDBAPI.FilmsList;
import com.example.android.popularmovies.TheMovieDBAPI.MovieDbClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org";

    //initialize MOVIE_DB_API_KEY to your themoviebd.org API Key
    private static final String MOVIE_DB_API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        mRecyclerView = findViewById(R.id.recycler_view_main_activity);

        mLayoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(mLayoutManager);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        MovieDbClient filmClient = retrofit.create(MovieDbClient.class);

        Call<FilmsList> call = filmClient.popularMoviesList(MOVIE_DB_API_KEY);

        call.enqueue(new Callback<FilmsList>() {
            @Override
            public void onResponse(Call<FilmsList> call, Response<FilmsList> response) {
                List<Films> filmsList = response.body().getFilmsListResults();
                mRecyclerView.setAdapter(new FilmRecyclerViewAdapter(filmsList, MainActivity.this));
                Log.d("LIST OF FILMS", filmsList.toString());

            }

            @Override
            public void onFailure(Call<FilmsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong: " + t, Toast.LENGTH_LONG).show();
            }
        });


    }
}
