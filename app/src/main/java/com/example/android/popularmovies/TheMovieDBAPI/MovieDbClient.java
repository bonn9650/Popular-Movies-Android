package com.example.android.popularmovies.TheMovieDBAPI;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbClient {

    @GET("/3/movie/{path}")
    Call<FilmsList> getMovieList(@Path("path") String path, @Query("api_key") String api_key);

}
