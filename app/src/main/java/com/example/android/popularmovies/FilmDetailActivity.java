package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.TheMovieDBAPI.Films;
import com.squareup.picasso.Picasso;

public class FilmDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView detailFilmImage = findViewById(R.id.detail_film_image);
        TextView detailFilmTitle = findViewById(R.id.detail_film_title);
        TextView detailFilmSynopsisBody = findViewById(R.id.detail_film_synopsis_body);
        TextView detailFilmStarRating = findViewById(R.id.detail_film_rating_star);

        Intent intent = getIntent();
        Films clickedFilm = (Films)intent.getSerializableExtra("Film");

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + clickedFilm.getBackdropPath()).into(detailFilmImage);
        setTitle(clickedFilm.getFilmTitle());

        detailFilmTitle.setText(clickedFilm.getFilmTitle());
        detailFilmSynopsisBody.setText(clickedFilm.getOverview());
        detailFilmStarRating.setText(String.valueOf(clickedFilm.getVoteAverage()));
    }

}
