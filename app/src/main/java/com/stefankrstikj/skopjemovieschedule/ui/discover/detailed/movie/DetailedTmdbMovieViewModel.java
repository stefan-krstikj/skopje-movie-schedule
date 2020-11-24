package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.cast.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.recommendation.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.review.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.video.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;

import java.util.List;

public class DetailedTmdbMovieViewModel extends ViewModel {
	private static String TAG = "DetailedTmdbMovieViewModel";

	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieVideoRepository mTmdbMovieVideoRepository;

	private TmdbMovieDetailed mTmdbMovieDetailed;
	private TmdbApiClient mApiClient;

	public DetailedTmdbMovieViewModel(TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
									  TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository,
									  TmdbMovieDetailed tmdbMovieDetailed, TmdbApiClient tmdbApiClient) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieVideoRepository = tmdbMovieVideoRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
		mApiClient = tmdbApiClient;
		fetchRecommendedMovies();
	}

	public LiveData<List<TmdbMovieCast>> getTmdbMovieCastForMovie(){
		return mTmdbCastRepository.getAllCastForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieReview>> getTmdbMovieReviewsForMovie(){
		return mTmdbMovieReviewRepository.getReviewsForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieDetailed>> getTmdbMovieRecommendationsForMovie(){
		return mTmdbMovieRecommendationRepository.getAllRecommendationsForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieVideo>> getTmdbdVideosForMovie(){
		return mTmdbMovieVideoRepository.getAllVideosForMovie(mTmdbMovieDetailed.getId());
	}

	void fetchRecommendedMovies(){
		Log.v(TAG, "getting recommended for: " + mTmdbMovieDetailed.getTitle());
		mApiClient.getMovieRecommendations(mTmdbMovieDetailed.getId());
	}
}
