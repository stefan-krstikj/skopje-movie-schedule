package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

public class DetailedTmdbMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModelFactory(TmdbCastRepository tmdbCastRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieDetailed tmdbMovieDetailed) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbMovieViewModel detailedTmdbMovieViewModel = new DetailedTmdbMovieViewModel(mTmdbCastRepository, mTmdbMovieReviewRepository, mTmdbMovieDetailed);
		return (T) detailedTmdbMovieViewModel;
	}
}
