package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

public class DetailedTmdbMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModelFactory(TmdbCastRepository tmdbCastRepository, TmdbMovieDetailed movieDetailed) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieDetailed = movieDetailed;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbMovieViewModel detailedTmdbMovieViewModel = new DetailedTmdbMovieViewModel(mTmdbCastRepository, mTmdbMovieDetailed);
		return (T) detailedTmdbMovieViewModel;
	}
}
