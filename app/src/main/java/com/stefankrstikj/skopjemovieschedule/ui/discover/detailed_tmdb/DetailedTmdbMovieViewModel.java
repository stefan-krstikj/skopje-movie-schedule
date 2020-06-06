package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

public class DetailedTmdbMovieViewModel extends ViewModel {
	private static String TAG = "DetailedTmdbMovieViewModel";

	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieDetailed mTmdbMovieDetailed;

	public DetailedTmdbMovieViewModel(TmdbCastRepository tmdbCastRepository, TmdbMovieDetailed movieDetailed) {
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieDetailed = movieDetailed;
	}

	public LiveData<List<TmdbCast>> getTmdbMovieCastForMovie(){
		return mTmdbCastRepository.getAllForMovie(mTmdbMovieDetailed.getId());
	}
}
