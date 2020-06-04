package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class DiscoverViewModel extends ViewModel  {
    private static String TAG = "DiscoverViewModel";
    private TmdbMovieRepository mTmdbMovieRepository;
    private TmdbCastRepository mTmdbCastRepository;
    private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
    private TmdbMovieGenreRepository mTmdbMovieGenreRepository;
    private MutableLiveData<List<TmdbMovieDetailed>> mData;


    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository,
                             TmdbCastRepository tmdbCastRepository,
                             TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
                             TmdbMovieGenreRepository tmdbMovieGenreRepository) {
        mTmdbMovieRepository = tmdbMovieRepository;
        mTmdbCastRepository = tmdbCastRepository;
        mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
        mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
        fetchMovieDiscovery();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllTrendingMovies() {
        return this.mTmdbMovieRepository.getAllTrendingMovies();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllUpcomingMovies(){
        return this.mTmdbMovieRepository.getAllUpcomingMovies();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllTopRatedMovies(){
        return this.mTmdbMovieRepository.getAllTopRatedMovies();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllPopularMovies(){
        return this.mTmdbMovieRepository.getAllPopularMovies();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllNowPlayingMovies(){
        return this.mTmdbMovieRepository.getAllNowPlayingMovies();
    }

    private void fetchMovieDiscovery(){
        Log.v(TAG, "Fetching Movie discovery obs");
        TmdbApiClient api = new TmdbApiClient(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository, mTmdbMovieGenreRepository);
//        api.getAllGenres();
        api.getTrendingMovies();
        api.getUpcomingMovies();
        api.getAllTopRatedMovies();
        api.getAllPopularMovies();
        api.getAllNowPlayingMovies();
    }
}
