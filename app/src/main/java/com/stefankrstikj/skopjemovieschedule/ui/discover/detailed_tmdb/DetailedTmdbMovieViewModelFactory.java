package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

public class DetailedTmdbMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieVideoRepository mTmdbMovieVideoRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;
	private TmdbApiClient mApiClient;
	public DetailedTmdbMovieViewModelFactory(TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
											 TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository,
											 TmdbMovieDetailed tmdbMovieDetailed, TmdbApiClient tmdbApiClient) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieVideoRepository = tmdbMovieVideoRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
		mApiClient = tmdbApiClient;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbMovieViewModel detailedTmdbMovieViewModel = new DetailedTmdbMovieViewModel(mTmdbCastRepository, mTmdbMovieRecommendationRepository,
				mTmdbMovieReviewRepository, mTmdbMovieVideoRepository, mTmdbMovieDetailed, mApiClient);
		return (T) detailedTmdbMovieViewModel;
	}
}
