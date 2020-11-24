package com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.genre;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieGenre;

public class TmdbMovieGenreRepository {
	private static String TAG = "TmdbMovieGenreRepository";

	private TmdbMovieGenreDao mTmdbMovieGenreDao;
	private static volatile TmdbMovieGenreRepository instance;

	public static TmdbMovieGenreRepository getInstance(TmdbMovieGenreDao tmdbMovieGenreDao){
		if(instance == null){
			synchronized (TmdbMovieGenreDao.class){
				if(instance == null){
					instance = new TmdbMovieGenreRepository(tmdbMovieGenreDao);
				}
			}
		}
		return instance;
	}

	private TmdbMovieGenreRepository(TmdbMovieGenreDao tmdbMovieGenreDao) {
		mTmdbMovieGenreDao = tmdbMovieGenreDao;
	}

	public void insert(TmdbMovieGenre tmdbMovieGenre){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbMovieGenreDao.insert(tmdbMovieGenre));
	}

	public LiveData<TmdbMovieGenre> getGenre(Integer id){
		return mTmdbMovieGenreDao.getGenreById(id);
	}
}
