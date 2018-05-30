package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
    private static final String MOVIE_DB_API_KEY = "subl";

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


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String listSort = sharedPreferences.getString(getString(R.string.sort_key), getString(R.string.MOST_POPULAR));

        Call<FilmsList> call = filmClient.getMovieList(listSort, MOVIE_DB_API_KEY);

        call.enqueue(new Callback<FilmsList>() {
            @Override
            public void onResponse(Call<FilmsList> call, Response<FilmsList> response) {
                List<Films> filmsList = null;

                    try {
                        filmsList = response.body().getFilmsListResults();

                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                mRecyclerView.setAdapter(new FilmRecyclerViewAdapter(filmsList, MainActivity.this));
            }

            @Override
            public void onFailure(Call<FilmsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong: " + t, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.films_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sort_menu:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
