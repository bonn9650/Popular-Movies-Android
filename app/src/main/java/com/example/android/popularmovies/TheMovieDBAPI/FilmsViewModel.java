package com.example.android.popularmovies.TheMovieDBAPI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class FilmsViewModel extends AndroidViewModel {

    private FavoriteFilmsRepository mRepository;
    private LiveData<List<Films>> mFavoriteFilmsList;
    private FilmsDatabase filmsDatabase = FilmsDatabase.getInstance(getApplication());


    private FilmsDao mFilmsDao = filmsDatabase.filmsDao();

    public LiveData<List<Films>> getFavoriteFilmsList() {
        return mFavoriteFilmsList;
    }

    public FilmsViewModel(@NonNull Application application) {
        super(application);

        mRepository = new FavoriteFilmsRepository(application);
        mFavoriteFilmsList = mRepository.getFavoriteFilms();

    }

    public void insert(Films films){
        mRepository.insert(films);
    }

    public void delete(int filmKey){
        mRepository.delete(filmKey);
    }

    public FilmsDao getFilmsDao() {
        return mFilmsDao;
    }

}
