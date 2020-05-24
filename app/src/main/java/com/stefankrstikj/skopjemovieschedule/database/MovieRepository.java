package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.models.Movie;

public class MovieRepository {
    private static String TAG = "MovieRepository";

    private MovieDao mMovieDao;
    private static volatile MovieRepository instance;

    MovieRepository(MovieDao movieDao){
        this.mMovieDao = movieDao;
    }

    public static MovieRepository getInstance(MovieDao movieDao){
        if(instance == null){
            synchronized(MovieRepository.class) {
                if (instance == null)
                    instance = new MovieRepository(movieDao);
            }
        }
        return instance;
    }

    public LiveData<List<Movie>> getAllMovies(){
        return mMovieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getAllComingSoonMovies(){
        return mMovieDao.getAllComingSoonMovies();
    }

    public void insert(final Movie movie){
            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mMovieDao.insert(movie);
                }
            });
    }

    public void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.deleteAll();
            }
        });
    }
}
