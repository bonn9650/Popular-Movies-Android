package com.example.android.popularmovies;

public class Films {

    private String mFilmTitle;
    private int mFilmRating;
    private String mFilmGenre;

    public Films(String filmTitle, int filmRating, String filmGenre) {
        this.mFilmTitle = filmTitle;
        this.mFilmRating = filmRating;
        this.mFilmGenre = filmGenre;
    }

    public String getmFilmTitle() {
        return mFilmTitle;
    }

    public void setmFilmTitle(String filmTitle) {
        this.mFilmTitle = filmTitle;
    }

    public int getmFilmRating() {
        return mFilmRating;
    }

    public void setmFilmRating(int filmRating) {
        this.mFilmRating = filmRating;
    }

    public String getmFilmGenre() {
        return mFilmGenre;
    }

    public void setmFilmGenre(String filmGenre) {
        this.mFilmGenre = filmGenre;
    }



}
