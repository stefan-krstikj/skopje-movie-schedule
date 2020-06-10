package com.stefankrstikj.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.List;

@Dao
public interface TmdbMovieReviewDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbMovieReview tmdbMovieReview);

	@Query("SELECT * FROM tmdb_movie_review WHERE movie_id=:movieId")
	public LiveData<List<TmdbMovieReview>> getMovieReviewsForMovie(int movieId);
}
