package com.stefankrstikj.skopjemovieschedule.ui.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.asynctask.TmdbAsyncTask;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

import java.util.List;

public class DiscoverViewModel extends ViewModel  {
    private static String TAG = "DiscoverViewModel";
    private TmdbMovieRepository mTmdbMovieRepository;

    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository) {
        mTmdbMovieRepository = tmdbMovieRepository;
    }

    public void insert(TmdbMovie tmdbMovie){
        this.mTmdbMovieRepository.insert(tmdbMovie);
    }

    LiveData<List<TmdbMovie>> getAllPopularMovies() {
        fetchMovieDiscovery();
        return this.mTmdbMovieRepository.getAll();
    }

    private void fetchMovieDiscovery(){
        TmdbAsyncTask tmdbAsyncTask = new TmdbAsyncTask(mTmdbMovieRepository);
        tmdbAsyncTask.execute();
    }
}
