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
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class DiscoverViewModel extends ViewModel  {
    private static String TAG = "DiscoverViewModel";
    private TmdbMovieRepository mTmdbMovieRepository;
    private TmdbCastRepository mTmdbCastRepository;
    private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
    private TmdbMovieGenreRepository mTmdbMovieGenreRepository;
    private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
    TmdbApiClient mApiClient;

    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository,
                             TmdbCastRepository tmdbCastRepository,
                             TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
                             TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository) {
        mTmdbMovieRepository = tmdbMovieRepository;
        mTmdbCastRepository = tmdbCastRepository;
        mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
        mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
        mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
         mApiClient = new TmdbApiClient(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository, mTmdbMovieGenreRepository, mTmdbMovieReviewRepository);
//        fetchAllMovies();
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

    public void clearAll(String resultType){
        mTmdbMovieRepository.clear(resultType);
    }

    public void fetchTrendingMovies(){
        mApiClient.getTrendingMovies();
    }

    public void fetchUpcomingMovies(){
        mApiClient.getUpcomingMovies();
    }

    public void fetchTopRatedMovies(){
        mApiClient.getAllTopRatedMovies();
    }

    public void fetchPopularMovies(){
        mApiClient.getAllPopularMovies();
    }

    public void fetchNowPlayingMovies(){
        mApiClient.getAllNowPlayingMovies();
    }

    public void fetchAllMovies(){
        Log.v(TAG, "Fetching Movie discovery obs");
//        api.getAllGenres();
        mApiClient.getTrendingMovies();
        mApiClient.getUpcomingMovies();
        mApiClient.getAllTopRatedMovies();
        mApiClient.getAllPopularMovies();
        mApiClient.getAllNowPlayingMovies();
    }
}
