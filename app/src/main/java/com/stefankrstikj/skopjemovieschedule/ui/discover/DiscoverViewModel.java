package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class DiscoverViewModel extends ViewModel  {
    private static String TAG = "DiscoverViewModel";
    private TmdbMovieRepository mTmdbMovieRepository;
    private TmdbCastRepository mTmdbCastRepository;
    private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
    private MutableLiveData<List<TmdbMovieDetailed>> mData;


    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository,
                             TmdbCastRepository tmdbCastRepository,
                             TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository) {
        mTmdbMovieRepository = tmdbMovieRepository;
        mTmdbCastRepository = tmdbCastRepository;
        mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
        fetchMovieDiscovery();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllTrendingMovies() {
        return this.mTmdbMovieRepository.getAllTrendingMovies();
    }

    public LiveData<List<TmdbMovieDetailed>> getAllUpcomingMovies(){
        return this.mTmdbMovieRepository.getAllUpcomingMovies();
    }

    private void fetchMovieDiscovery(){
        Log.v(TAG, "Fetching Movie discovery obs");
        TmdbApiClient api = new TmdbApiClient(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository);
        api.getTrendingMovies();
        api.getUpcomingMovies();
    }
}
