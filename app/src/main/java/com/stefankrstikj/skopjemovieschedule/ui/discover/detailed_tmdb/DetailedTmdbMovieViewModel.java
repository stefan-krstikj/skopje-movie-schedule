package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.List;

public class DetailedTmdbMovieViewModel extends ViewModel {
	private static String TAG = "DetailedTmdbMovieViewModel";

	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;
	private TmdbMovieGenreRepository mTmdbMovieGenreRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;

	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModel(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
									  TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieDetailed tmdbMovieDetailed) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieDetailed = tmdbMovieDetailed;
		fetchRecommendedMovies();
	}

	public LiveData<List<TmdbCast>> getTmdbMovieCastForMovie(){
		return mTmdbCastRepository.getAllForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieReview>> getTmdbMovieReviewsForMovie(){
		return mTmdbMovieReviewRepository.getReviewsForMovie(mTmdbMovieDetailed.getId());
	}

	public LiveData<List<TmdbMovieDetailed>> getTmdbMovieRecommendationsForMovie(){
		return mTmdbMovieRecommendationRepository.getAllRecommendationsForMovie(mTmdbMovieDetailed.getId());
	}

	void fetchRecommendedMovies(){
		Log.v(TAG, "getting recommended for: " + mTmdbMovieDetailed.getTitle());
		TmdbApiClient api = new TmdbApiClient(mTmdbMovieRepository, mTmdbCastRepository, mTmdbMovieRecommendationRepository, mTmdbMovieGenreRepository, mTmdbMovieReviewRepository);
		api.getMovieRecommendations(mTmdbMovieDetailed.getId());
	}
}
