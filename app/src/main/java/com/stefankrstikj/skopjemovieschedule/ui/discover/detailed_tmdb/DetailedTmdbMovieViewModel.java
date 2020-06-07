package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.List;

public class DetailedTmdbMovieViewModel extends ViewModel {
	private static String TAG = "DetailedTmdbMovieViewModel";

	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModel(TmdbCastRepository tmdbCastRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieDetailed tmdbMovieDetailed) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
	}

	public LiveData<List<TmdbCast>> getTmdbMovieCastForMovie(){
		return mTmdbCastRepository.getAllForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieReview>> getTmdbMovieReviewsForMovie(){
		return mTmdbMovieReviewRepository.getReviewsForMovie(mTmdbMovieDetailed.getId());
	}
}
