package com.stefankrstikj.skopjemovieschedule.ui.discover.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;

import java.util.Observer;

public class DiscoverSearchResultsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbApiClient mApiClient;

	public DiscoverSearchResultsViewModelFactory(TmdbMovieRepository tmdbMovieRepository, TmdbApiClient apiClient) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mApiClient = apiClient;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DiscoverSearchResultsViewModel discoverSearchResultsViewModel = new DiscoverSearchResultsViewModel(mTmdbMovieRepository, mApiClient);
		return (T) discoverSearchResultsViewModel;
	}
}
