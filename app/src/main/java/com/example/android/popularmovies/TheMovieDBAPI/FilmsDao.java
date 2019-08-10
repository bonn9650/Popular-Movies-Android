package com.example.android.popularmovies.TheMovieDBAPI;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.popularmovies.FilmDetailActivity;

@Dao
public interface FilmsDao {

    @Query("SELECT * FROM FILMSTABLE ORDER BY `id` ASC")
    LiveData<List<Films>> loadAllFilms();

    @Query("SELECT * FROM FILMSTABLE WHERE filmTitle = :clickedFilmTitle")
    Films searchFavByFilmTitle(String clickedFilmTitle);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(Films films);

    @Query("DELETE FROM FILMSTABLE WHERE `id` = :id")
    void deleteFilm(int id);

}

