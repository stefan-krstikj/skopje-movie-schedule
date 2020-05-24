package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

import java.util.List;

public class TmdbMovieRepository {
	private static String TAG = "TmdbMovieDiscoverRepository";

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

	public void insert(TmdbMovie tmdbMovie){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieDao.insert(tmdbMovie));
	}

	public LiveData<List<TmdbMovie>> getAll(){
		return mTmdbMovieDao.getAll();
	}
}
