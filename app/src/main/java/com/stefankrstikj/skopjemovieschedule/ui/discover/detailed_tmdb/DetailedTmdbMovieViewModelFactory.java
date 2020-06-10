package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

public class DetailedTmdbMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
	private TmdbMovieGenreRepository mTmdbMovieGenreRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModelFactory(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
											 TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieDetailed tmdbMovieDetailed) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbMovieViewModel detailedTmdbMovieViewModel = new DetailedTmdbMovieViewModel(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository,
				mTmdbMovieGenreRepository, mTmdbMovieReviewRepository, mTmdbMovieDetailed);
		return (T) detailedTmdbMovieViewModel;
	}
}
