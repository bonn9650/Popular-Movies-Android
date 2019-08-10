package com.example.android.popularmovies.TheMovieDBAPI;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.example.android.popularmovies.R;

@Database(entities = Films.class, version = 1, exportSchema = false)
public abstract class FilmsDatabase extends RoomDatabase {

    private static final String LOG_TAG = FilmsDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String FILMS_DATABASE_NAME = "Films_Database";
    private static FilmsDatabase sFilmsDatabase;

    public static FilmsDatabase getInstance(final Context context){
        if (sFilmsDatabase == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new Database for Films");
                sFilmsDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        FilmsDatabase.class, FilmsDatabase.FILMS_DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG, "Getting database of films");
        return sFilmsDatabase;
    }

    public abstract FilmsDao filmsDao();
}
