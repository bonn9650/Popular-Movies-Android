package com.example.android.popularmovies.TheMovieDBAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmsList {
    @SerializedName("results")
    private List<Films> filmsListResults;


    public List<Films> getFilmsListResults() {
        return filmsListResults;
    }
}
