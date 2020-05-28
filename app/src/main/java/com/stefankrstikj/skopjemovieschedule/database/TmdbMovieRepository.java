package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class TmdbMovieRepository {
	private static String TAG = "TmdbMovieRepository";

	private TmdbMovieDao mTmdbMovieDao;
	private static volatile TmdbMovieRepository instance;

	public static TmdbMovieRepository getInstance(TmdbMovieDao tmdbMovieDao){
		if(instance == null){
			synchronized (TmdbMovieDao.class){
				if(instance == null){
					instance = new TmdbMovieRepository(tmdbMovieDao);
				}
			}
		}
		return instance;
	}


	private TmdbMovieRepository(TmdbMovieDao tmdbMovieDao) {
		mTmdbMovieDao = tmdbMovieDao;
	}

	public void insert(TmdbMovieDetailed tmdbMovieDetailed){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieDao.insert(tmdbMovieDetailed));
	}

	public LiveData<List<TmdbMovieDetailed>> getAllTrendingMovies(){
		return mTmdbMovieDao.getAllTrendingMovies();
	}

	public LiveData<List<TmdbMovieDetailed>> getAllUpcomingMovies(){
		return mTmdbMovieDao.getAllUpcomingMovies();
	}
}
