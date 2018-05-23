package com.example.android.popularmovies.TheMovieDBAPI;

import com.google.gson.annotations.SerializedName;

public class Films {

    @SerializedName("vote_count") private String voteCount;
    @SerializedName("id") private String id;
    @SerializedName("vote_average") double voteAverage;
    @SerializedName("title") String filmTitle;
    @SerializedName("poster_path") String posterPath;


    public Films(String voteCount, String id, double voteAverage, String filmTitle, String posterPath) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.filmTitle = filmTitle;
        this.posterPath = posterPath;
    }


    public String getVoteCount() {
        return voteCount;
    }

    public String getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
