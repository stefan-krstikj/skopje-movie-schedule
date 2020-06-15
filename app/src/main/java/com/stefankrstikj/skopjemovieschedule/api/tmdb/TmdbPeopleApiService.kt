package com.stefankrstikj.skopjemovieschedule.api.tmdb

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbPeopleApiService {

    @GET("person/{person_id}")
    fun getDetailsForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )

    @GET("person/{person_id}/movie_credits")
    fun getMovieCreditsForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )

    @GET("person/{person_id}/tv_credits")
    fun getTvShowCreditsForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )

    @GET("person/{person_id}/combined_credits")
    fun getCombinedCreditsForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )

    @GET("person/{person_id}/external_ids")
    fun getExternalIdsForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )

    @GET("person/{person_id}/images")
    fun getImagesForPerson(
            @Path("person_id") person_id: String,
            @Query("api_key") api_key: String
    )
}