package com.stefankrstikj.skopjemovieschedule.ui.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class DiscoverViewModel extends ViewModel  {
    private static String TAG = "DiscoverViewModel";
    private TmdbMovieRepository mTmdbMovieRepository;
    TmdbApiClient mApiClient;

    public DiscoverViewModel(TmdbMovieRepository tmdbMovieRepository, TmdbApiClient tmdbApiClient) {
        mTmdbMovieRepository = tmdbMovieRepository;
        mApiClient = tmdbApiClient;
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
        mApiClient.getTrendingMovies();
        mApiClient.getUpcomingMovies();
        mApiClient.getAllTopRatedMovies();
        mApiClient.getAllPopularMovies();
        mApiClient.getAllNowPlayingMovies();
    }
}
