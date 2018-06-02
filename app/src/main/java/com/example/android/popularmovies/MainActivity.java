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

    //Global variables for monitoring sharedpreferences through lifecycle
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set main view type, and layout manager
        final RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = findViewById(R.id.recycler_view_main_activity);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);



        //Create preference listener for settings, recreate main activity on list change
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals(getString(R.string.sort_key))){
                    recreate();
                }
            }
        };

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(prefListener);

        String listSort = mSharedPreferences
                .getString(getString(R.string.sort_key), getString(R.string.MOST_POPULAR));

        //Set actionbar title to film sort from settings
        if (listSort.equals(getString(R.string.MOST_POPULAR))){
            setTitle(getString(R.string.most_popular_films));
        }else if (listSort.equals(getString(R.string.HIGHEST_RATED))){
            setTitle(getString(R.string.top_rated_films));
        }else {
            setTitle(getString(R.string.app_name));
        }


        //create retrofit instance to manage async network calls to theMovieDb server,
        // Gson converter is for handling JSON objects
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //MovieDbClient contains methods for network calls
        MovieDbClient filmClient = retrofit.create(MovieDbClient.class);

        //listsort uses string resource, resource dynamically changes path in @GET method call
        Call<FilmsList> call = filmClient.getMovieList(listSort, getString(R.string.api_key));

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


    //inflate xml resource to create settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.films_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregister pref change listener to avoid memory leak
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(prefListener);
    }
}
