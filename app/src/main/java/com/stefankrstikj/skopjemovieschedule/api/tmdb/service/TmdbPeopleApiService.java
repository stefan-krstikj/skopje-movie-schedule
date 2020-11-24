package com.stefankrstikj.skopjemovieschedule.api.tmdb.service;

import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbPeopleApiService {
	@GET("person/{person_id}")
	Observable<TmdbPerson> getDetailsForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);

	@GET("person/{person_id}/movie_credits")
	void getMovieCreditsForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);

	@GET("person/{person_id}/tv_credits")
	void getTvShowCreditsForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);

	@GET("person/{person_id}/combined_credits")
	void getCombinedCreditsForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);

	@GET("person/{person_id}/external_ids")
	void getExternalIdsForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);

	@GET("person/{person_id}/images")
	void getImagesForPerson(
			@Path("person_id") Integer person_id,
			@Query("api_key") String api_key
	);
}
