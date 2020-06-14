package com.stefankrstikj.skopjemovieschedule.ui.discover.search;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;
import java.util.Observable;

public class DiscoverSearchResultsViewModel extends ViewModel {
	private static String TAG = "DiscoverSearchResultsViewModel";

	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbApiClient mApiClient;

	public DiscoverSearchResultsViewModel(TmdbMovieRepository tmdbMovieRepository, TmdbApiClient apiClient) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mApiClient = apiClient;
	}

	public LiveData<List<TmdbMovieDetailed>> getQueryMovies(){
		return mTmdbMovieRepository.getAllQueryMovies();
	}

	public void fetchQueryMovies(String query){
		mTmdbMovieRepository.clear("Query", query);
		mApiClient.getAllMoviesForQuery(query);
	}
}
