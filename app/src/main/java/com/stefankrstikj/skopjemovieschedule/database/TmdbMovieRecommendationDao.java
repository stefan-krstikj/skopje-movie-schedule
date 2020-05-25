package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;

import java.util.List;

@Dao
public interface TmdbMovieRecommendationDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(TmdbMovieRecommendation tmdbMovieRecommendation);

	@Query("SELECT * FROM tmdb_movie_recommendation tmr INNER JOIN tmdb_movie_detailed tmd ON tmr.movie_id=tmd.id" +
			" WHERE movie_id == :movie_id")
	LiveData<List<TmdbMovieRecommendation>> getRecommendedMoviesForMovie(Integer movie_id);

	@Query("DELETE FROM tmdb_movie_recommendation")
	void deleteAll();
}
