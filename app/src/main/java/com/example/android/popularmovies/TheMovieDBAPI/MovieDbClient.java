package com.example.android.popularmovies.TheMovieDBAPI;

import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbClient {

    @GET("/3/movie/popular")
    Call<FilmsList> popularMoviesList(@Query("api_key") String api_key);
}
