package com.stefankrstikj.skopjemovieschedule.ui.discover;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.cast.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.TmdbMovieRepository;

public class DiscoverViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbApiClient mApiClient;

	public DiscoverViewModelFactory(TmdbMovieRepository tmdbMovieRepository, TmdbApiClient tmdbApiClient) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mApiClient = tmdbApiClient;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DiscoverViewModel discoverViewModel = new DiscoverViewModel(mTmdbMovieRepository, mApiClient);
		return (T) discoverViewModel;
	}
}
