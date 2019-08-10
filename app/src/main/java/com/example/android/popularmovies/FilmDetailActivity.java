package com.example.android.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.TheMovieDBAPI.AppExecutors;
import com.example.android.popularmovies.TheMovieDBAPI.Films;
import com.example.android.popularmovies.TheMovieDBAPI.FilmsDao;
import com.example.android.popularmovies.TheMovieDBAPI.FilmsViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmDetailActivity extends AppCompatActivity{

    private final String BASE_IMAGE_BACKDROP_URL = "https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        ImageView detailFilmImage = findViewById(R.id.detail_film_image);
        TextView detailFilmTitle = findViewById(R.id.detail_film_title);
        TextView detailFilmSynopsisBody = findViewById(R.id.detail_film_synopsis_body);
        TextView detailFilmStarRating = findViewById(R.id.detail_film_rating_star);
        TextView detailFilmReleaseDate = findViewById(R.id.release_date_value);
        final ImageView favoriteBtn = findViewById(R.id.favorite_btn);

        Intent intent = getIntent();
        final Films clickedFilm = intent.getParcelableExtra("Film");

        Picasso.get().load( BASE_IMAGE_BACKDROP_URL + clickedFilm.getBackdropPath())
                .placeholder(R.drawable.placeholder_image)
                .into(detailFilmImage);

        //set activity title to film title
        setTitle(clickedFilm.getFilmTitle());

        detailFilmTitle.setText(clickedFilm.getFilmTitle());
        detailFilmSynopsisBody.setText(clickedFilm.getOverview());
        detailFilmStarRating.setText(String.valueOf(clickedFilm.getVoteAverage()));
        detailFilmReleaseDate.setText(String.valueOf(dateFormatter(clickedFilm.getReleaseDate())));


        LiveData<List<Films>> favFilms;
        final String clickedFilmTitle = clickedFilm.getFilmTitle();
        final FilmsViewModel filmsViewModel = ViewModelProviders.of(this).get(FilmsViewModel.class);
        favFilms = filmsViewModel.getFavoriteFilmsList();
        final FilmsDao mFilmsDao = filmsViewModel.getFilmsDao();


        favFilms.observe(this, new Observer<List<Films>>() {
            @Override
            public void onChanged(@Nullable List<Films> films) {

                if (films.isEmpty()){
                    favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24px);
                }else {

                    for (Films e: films){
                        String name = e.getFilmTitle();
                        if (name.equals(clickedFilmTitle)){
                            favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24px);
                        }else {
                            favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24px);

                        }

                    }

                }
            }
        });


        //Set onclick listener for favorite button, check database for film and set button
        // image according to state

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getsExecutorsInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Films resultFilm = mFilmsDao.searchFavByFilmTitle(clickedFilmTitle);

                        if (resultFilm != null){
                            int id = resultFilm.getId();
                            if (id > 0){
                                filmsViewModel.delete(id);
                            }
                        }else {
                            filmsViewModel.insert(clickedFilm);
                        }
                    }
                });
            }
        });



    }

    protected String dateFormatter(String date){
        return date.replace("-", ".");
    }


}
