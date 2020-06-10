package com.stefankrstikj.skopjemovieschedule.database;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;

import java.util.List;

public class TmdbMovieRecommendationRepository {
	private static String TAG = "TmdbMovieRecommendationRepository";

	private TmdbMovieRecommendationDao mTmdbMovieRecommendationDao;
	private static volatile TmdbMovieRecommendationRepository instance;

	public static TmdbMovieRecommendationRepository getInstance(TmdbMovieRecommendationDao tmdbMovieRecommendationDao){
		if(instance == null){
			synchronized (TmdbMovieRecommendationDao.class){
				if(instance == null){
					instance = new TmdbMovieRecommendationRepository(tmdbMovieRecommendationDao);
				}
			}
		}
		return instance;
	}

	private TmdbMovieRecommendationRepository(TmdbMovieRecommendationDao tmdbMovieRecommendationDao) {
		mTmdbMovieRecommendationDao = tmdbMovieRecommendationDao;
	}

	public void insert(TmdbMovieRecommendation tmdbMovieRecommendation){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieRecommendationDao.insert(tmdbMovieRecommendation));
	}

	public LiveData<List<TmdbMovieDetailed>> getAllRecommendationsForMovie(Integer id){
		return mTmdbMovieRecommendationDao.getRecommendedMoviesForMovie(id);
	}

	public void deleteAll(){
		this.mTmdbMovieRecommendationDao.deleteAll();
	}
}
