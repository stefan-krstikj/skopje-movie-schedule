package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.util.Log;

import androidx.lifecycle.LiveData;
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

    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository,
                             TmdbCastRepository tmdbCastRepository,
                             TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository) {
        mTmdbMovieRepository = tmdbMovieRepository;
        mTmdbCastRepository = tmdbCastRepository;
        mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
    }

    LiveData<List<TmdbMovieDetailed>> getAllPopularMovies() {
        fetchMovieDiscovery();
        return this.mTmdbMovieRepository.getAll();
    }

    private void fetchMovieDiscovery(){
        Log.v(TAG, "Fetching Movie discovery obs");
        TmdbApiClient api = new TmdbApiClient(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository);
        api.getPopularMovies();
    }
}
