package com.example.android.popularmovies.TheMovieDBAPI;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;
public class FavoriteFilmsRepository {


    private FilmsDao mFilmsDao;
    private LiveData<List<Films>> mFavoriteFilms;
    String TAG = "TAG";

    public FavoriteFilmsRepository(Application application){
        FilmsDatabase filmsDatabase = FilmsDatabase.getInstance(application);
        mFilmsDao = filmsDatabase.filmsDao();
        mFavoriteFilms = mFilmsDao.loadAllFilms();
    }

    public LiveData<List<Films>> getFavoriteFilms(){
        Log.d(TAG, "DATA QUERIED");
        return mFavoriteFilms;
    }

    public void insert(final Films films){
        AppExecutors.getsExecutorsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "DATA QUERIED");

                mFilmsDao.insertFilm(films);
            }
        });
    }

    public void delete(final int id){
        AppExecutors.getsExecutorsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "DATA QUERIED");

                mFilmsDao.deleteFilm(id);
            }
        });
    }

}
