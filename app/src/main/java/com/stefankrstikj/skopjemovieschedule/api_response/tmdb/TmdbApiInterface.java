package com.stefankrstikj.skopjemovieschedule.api_response.tmdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApiInterface {

    @GET("discover/movie/?sort_by=popularity.desc")
    Call<TmdbMovieDiscoveryResponse> getAllPopularMovies(
            @Query("api_key") String api_key
    );
}
