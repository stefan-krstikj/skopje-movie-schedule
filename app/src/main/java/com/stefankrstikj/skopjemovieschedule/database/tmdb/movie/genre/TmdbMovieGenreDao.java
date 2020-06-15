package com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.genre;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieGenre;

import java.util.List;

@Dao
public interface TmdbMovieGenreDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	 void insert(TmdbMovieGenre tmdbMovieGenre);

	@Query("SELECT * FROM tmdb_genre WHERE id = :id")
	LiveData<TmdbMovieGenre> getGenreById(Integer id);

	@Query("SELECT * FROM tmdb_genre")
	LiveData<List<TmdbMovieGenre>> getAllGenres();
}
