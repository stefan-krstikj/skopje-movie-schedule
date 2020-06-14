package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;

import java.util.List;

public class TmdbCastRepository {
	private static String TAG = "TmdbCastRepository";

	private TmdbCastDao mTmdbCastDao;
	private static volatile TmdbCastRepository instance;

	public static TmdbCastRepository getInstance(TmdbCastDao tmdbCastDao){
		if(instance == null){
			synchronized (TmdbCastDao.class){
				if(instance == null){
					instance = new TmdbCastRepository(tmdbCastDao);
				}
			}
		}
		return instance;
	}

	public TmdbCastRepository(TmdbCastDao tmdbCastDao) {
		mTmdbCastDao = tmdbCastDao;
	}

	public void insert(TmdbMovieCast tmdbMovieCast){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbCastDao.insert(tmdbMovieCast));
	}

	public LiveData<List<TmdbMovieCast>> getAll(){
		return mTmdbCastDao.getAllCasts();
	}

	public LiveData<List<TmdbMovieCast>> getAllForMovie(Integer movieId){
		return mTmdbCastDao.getCastForMovie(movieId);
	}
}
