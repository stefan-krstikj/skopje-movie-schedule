package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;

import java.util.List;

@Dao
public interface TmdbCastDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbMovieCast cast);

	@Query("SELECT * FROM tmdb_movie_cast")
	LiveData<List<TmdbMovieCast>> getAllCasts();

	@Query("SELECT * FROM tmdb_movie_cast WHERE movie_id == :movieId")
	LiveData<List<TmdbMovieCast>> getCastForMovie(Integer movieId);

	@Query("DELETE FROM tmdb_movie_cast")
	void deleteAll();
}
