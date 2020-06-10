package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;

import java.util.List;

@Dao
public interface TmdbCastDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbCast cast);

	@Query("SELECT * FROM tmdb_cast")
	LiveData<List<TmdbCast>> getAllCasts();

	@Query("SELECT * FROM tmdb_cast WHERE movie_id == :movieId")
	LiveData<List<TmdbCast>> getCastForMovie(Integer movieId);

	@Query("DELETE FROM tmdb_cast")
	void deleteAll();
}
