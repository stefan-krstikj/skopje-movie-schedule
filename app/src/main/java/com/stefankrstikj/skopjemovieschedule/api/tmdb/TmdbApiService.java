package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieGenre;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApiService {
	@GET("trending/movie/week")
	Observable<TmdbMovieResponse> getAllTrendingMovies(
			@Query("api_key") String api_key
	);

	@GET("movie/upcoming")
	Observable<TmdbMovieResponse> getAllUpcomingMovies(
			@Query("api_key") String api_key
	);

	@GET("movie/top_rated")
	Observable<TmdbMovieResponse> getAllTopRatedMovies(
			@Query("api_key") String api_key
	);

	@GET("movie/popular")
	Observable<TmdbMovieResponse> getAllPopularMovies(
			@Query("api_key") String api_key
	);

	@GET("movie/now_playing")
	Observable<TmdbMovieResponse> getAllNowPlayingMovies(
			@Query("api_key") String api_key
	);

	@GET("movie/{movie_id}")
	Observable<TmdbMovieDetailed> getDetailsForMovie(
			@Path("movie_id") Integer id,
			@Query("api_key") String api_key
	);

	@GET("genre/movie/list")
	Observable<List<TmdbMovieGenre>> getAllGenres(
			@Query("api_key") String api_key
	);

	@GET("movie/{movie_id}/credits")
	Observable<TmdbCastResponse> getCastForMovie(
			@Path("movie_id") Integer id,
			@Query("api_key") String api_key
	);

	@GET("movie/{movie_id}/recommendations")
	Observable<TmdbMovieResponse> getRecommendationsForMovie(
			@Path("movie_id") Integer id,
			@Query("api_key") String api_key
	);

	@GET("movie/{movie_id}/reviews")
	Observable<TmdbMovieReviewResponse> getReviewsForMovie(
			@Path("movie_id") Integer id,
			@Query("api_key") String api_key
	);

	@GET("movie/{movie_id}/videos")
	Observable<TmdbMovieVideosResponse> getVideosForMovie(
			@Path("movie_id") Integer id,
			@Query("api_key") String api_key
	);

	@GET("search/movie")
	Observable<TmdbMovieResponse> getMoviesForQuery(
			@Query("query") String query,
			@Query("api_key") String api_key
	);
}
