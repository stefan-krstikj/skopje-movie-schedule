package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;

import java.util.List;

@Dao
public interface TmdbMovieVideoDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbMovieVideo tmdbMovieVideo);

	@Query("SELECT * FROM tmdb_movie_videos WHERE movie_id==:movieId")
	LiveData<List<TmdbMovieVideo>> getAllVideosForMovie(Integer movieId);
}
