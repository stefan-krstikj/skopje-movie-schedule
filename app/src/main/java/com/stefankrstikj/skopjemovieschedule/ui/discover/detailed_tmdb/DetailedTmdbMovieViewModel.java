package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;

import java.util.List;

public class DetailedTmdbMovieViewModel extends ViewModel {
	private static String TAG = "DetailedTmdbMovieViewModel";

	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;

	public DetailedTmdbMovieViewModel(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
	}

	LiveData<List<TmdbCast>> getTmdbMovieCastForMovie(Integer movieId){
		return mTmdbCastRepository.getAllForMovie(movieId);
	}
}
