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
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbMovieRecommendation tmdbMovieRecommendation);

//	@Query("select * from tmdb_movie_detailed where id in" +
//			"(SELECT tmr.movie_id FROM tmdb_movie_recommendation tmr INNER JOIN tmdb_movie_detailed tmd ON tmr.source_movie_id == tmd.id" +
//			" WHERE tmr.source_movie_id == 33888)" +
//			" AND result_type == 2)"
	@Query("SELECT * FROM tmdb_movie_detailed WHERE id in (SELECT movie_id FROM tmdb_movie_recommendation tmr INNER JOIN tmdb_movie_detailed tmd ON tmr.source_movie_id==tmd.id " +
			"WHERE tmr.source_movie_id==:movie_id) " +
			"AND result_type=='Recommendation'")
	LiveData<List<TmdbMovieDetailed>> getRecommendedMoviesForMovie(Integer movie_id);

	@Query("DELETE FROM tmdb_movie_recommendation")
	void deleteAll();
}
