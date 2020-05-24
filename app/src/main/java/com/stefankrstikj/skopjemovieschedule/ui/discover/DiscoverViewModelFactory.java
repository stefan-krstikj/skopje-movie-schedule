package com.stefankrstikj.skopjemovieschedule.ui.discover;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;

public class DiscoverViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbMovieRepository mTmdbMovieRepository;

	public DiscoverViewModelFactory(TmdbMovieRepository tmdbMovieRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DiscoverViewModel discoverViewModel = new DiscoverViewModel(mTmdbMovieRepository);
		return (T) discoverViewModel;
	}
}
