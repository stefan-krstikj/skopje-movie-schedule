package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;

public class DetailedTmdbMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;

	public DetailedTmdbMovieViewModelFactory(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbMovieViewModel detailedTmdbMovieViewModel = new DetailedTmdbMovieViewModel(mTmdbMovieRepository, mTmdbCastRepository);
		return (T) detailedTmdbMovieViewModel;
	}
}
