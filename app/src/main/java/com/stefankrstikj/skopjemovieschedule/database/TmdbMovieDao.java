package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

import java.util.List;

@Dao
public interface TmdbMovieDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(TmdbMovieDetailed tmdbMovieDetailed);

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Trending'")
	LiveData<List<TmdbMovieDetailed>> getAllTrendingMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Upcoming'")
	LiveData<List<TmdbMovieDetailed>> getAllUpcomingMovies();

	@Query("DELETE FROM tmdb_movie_detailed")
	void deleteAll();
}
