package com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.video;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;

import java.util.List;

public class TmdbMovieVideoRepository {

	private static String TAG = "TmdbMovieReviewRepository";

	private TmdbMovieVideoDao mTmdbMovieVideoDao;

	private static volatile TmdbMovieVideoRepository instance;

	public static TmdbMovieVideoRepository getInstance(TmdbMovieVideoDao tmdbMovieVideoDao){
		if(instance == null){
			synchronized (TmdbMovieVideoDao.class){
				if(instance == null){
					instance = new TmdbMovieVideoRepository(tmdbMovieVideoDao);
				}
			}
		}
		return instance;
	}

	private TmdbMovieVideoRepository(TmdbMovieVideoDao tmdbMovieVideoDao) {
		mTmdbMovieVideoDao = tmdbMovieVideoDao;
	}

	public void insert(TmdbMovieVideo tmdbMovieVideo){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieVideoDao.insert(tmdbMovieVideo));
	}

	public LiveData<List<TmdbMovieVideo>> getAllVideosForMovie(Integer id){
		return mTmdbMovieVideoDao.getAllVideosForMovie(id);
	}
}
