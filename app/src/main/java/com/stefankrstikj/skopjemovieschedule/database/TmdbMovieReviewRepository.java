package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.List;

public class TmdbMovieReviewRepository {
	private static String TAG = "TmdbMovieReviewRepository";

	private TmdbMovieReviewDao mTmdbMovieReviewDao;
	private static volatile TmdbMovieReviewRepository instance;

	public static TmdbMovieReviewRepository getInstance(TmdbMovieReviewDao tmdbMovieReviewDao){
		if(instance==null){
			synchronized (TmdbMovieReviewDao.class){
				if(instance == null){
					instance = new TmdbMovieReviewRepository(tmdbMovieReviewDao);
				}
			}
		}

		return instance;
	}

	private TmdbMovieReviewRepository(TmdbMovieReviewDao tmdbMovieReviewDao) {
		mTmdbMovieReviewDao = tmdbMovieReviewDao;
	}

	public void insert(TmdbMovieReview tmdbMovieReview){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieReviewDao.insert(tmdbMovieReview));
	}

	public LiveData<List<TmdbMovieReview>> getReviewsForMovie(int movieId){
		return mTmdbMovieReviewDao.getMovieReviewsForMovie(movieId);
	}

}
