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

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbMovieDetailed tmdbMovieDetailed);

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Trending' ORDER BY popularity DESC")
	LiveData<List<TmdbMovieDetailed>> getAllTrendingMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Upcoming' ORDER BY popularity DESC")
	LiveData<List<TmdbMovieDetailed>> getAllUpcomingMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Top Rated' ORDER BY popularity DESC")
	LiveData<List<TmdbMovieDetailed>> getAllTopRatedMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Popular' ORDER BY popularity DESC")
	LiveData<List<TmdbMovieDetailed>> getAllPopularMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Now Playing' ORDER BY popularity DESC")
	LiveData<List<TmdbMovieDetailed>> getAllNowPlayingMovies();

	@Query("SELECT * FROM tmdb_movie_detailed WHERE result_type == 'Query' ORDER BY popularity DESC LIMIT 8")
	LiveData<List<TmdbMovieDetailed>> getAllQueryMovies();

	@Query("DELETE FROM tmdb_movie_detailed")
	void deleteAll();

	@Query("DELETE FROM tmdb_movie_detailed WHERE result_type==:resultType")
	void delete(String resultType);

	@Query("DELETE FROM tmdb_movie_detailed WHERE result_type==:resultType AND title NOT LIKE '%' || :movieTitle || '%'")
	void delete(String resultType, String movieTitle);
}
