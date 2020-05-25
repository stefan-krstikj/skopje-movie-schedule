package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApiService {

	@GET("trending/movie/week")
	Call<TmdbMovieResponse> getAllPopularMovies(
			@Query("api_key") String api_key
	);

	@GET("trending/movie/week")
	Observable<TmdbMovieResponse> getAllPopularMoviesObs(
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
}
