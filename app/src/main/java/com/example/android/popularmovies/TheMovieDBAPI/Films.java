package com.example.android.popularmovies.TheMovieDBAPI;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


//implements serializble to create reference for serializable intent,
//Films object is be passed in place of parcelable
public class Films implements Serializable {

    @SerializedName("vote_count") private int voteCount;
    @SerializedName("id") private int id;
    @SerializedName("vote_average") double voteAverage;
    @SerializedName("title") String filmTitle;
    @SerializedName("poster_path") String posterPath;
    @SerializedName("backdrop_path") String backdropPath;
    @SerializedName("genre_ids") int[] genreIDs;
    @SerializedName("overview") String overview;


    public Films(int voteCount, int id, double voteAverage, String filmTitle, String posterPath, String backdropPath, int[] genreIDs, String overview) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.filmTitle = filmTitle;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.genreIDs = genreIDs;
        this.overview = overview;
    }

    //Getters
    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
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

    public String getBackdropPath() {
        return backdropPath;
    }

    public int[] getGenreIDs() {
        return genreIDs;
    }

    public String getOverview() {
        return overview;
    }
}
