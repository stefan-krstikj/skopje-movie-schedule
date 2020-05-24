package com.stefankrstikj.skopjemovieschedule.api_response.omdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbAPIInterface {

    @GET("?")
    Call<OmdbMovieResult> getMovieByTitleAndYear(
            @Query("apikey") String apiKey,
            @Query("t") String title,
            @Query("y") String year,
            @Query("plot") String plot
    );

    @GET("?")
    Call<OmdbMovieResult> getMovieByTitle(
            @Query("apikey") String apiKey,
            @Query("t") String title,
            @Query("plot") String plot
    );

}
