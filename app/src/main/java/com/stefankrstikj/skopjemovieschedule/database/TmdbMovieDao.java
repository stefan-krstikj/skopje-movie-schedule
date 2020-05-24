package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

import java.util.List;

@Dao
public interface TmdbMovieDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insert(TmdbMovie tmdbMovie);

	@Query("SELECT * FROM tmdb_movie")
	LiveData<List<TmdbMovie>> getAll();

	@Query("DELETE FROM tmdb_movie")
	void deleteAll();
}
