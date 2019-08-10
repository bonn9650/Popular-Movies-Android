package com.example.android.popularmovies.TheMovieDBAPI;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors sExecutorsInstance;
    private final Executor diskIO;

    public AppExecutors(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public static AppExecutors getsExecutorsInstance() {
        if (sExecutorsInstance == null){
            synchronized (LOCK){
                sExecutorsInstance = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return sExecutorsInstance;
    }

}

