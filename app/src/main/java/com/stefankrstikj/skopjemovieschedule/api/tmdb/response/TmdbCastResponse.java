package com.stefankrstikj.skopjemovieschedule.api.tmdb.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;

import java.util.List;

public class TmdbCastResponse {
	@SerializedName("id")
	private Integer mId;
	@SerializedName("cast")
	private List<TmdbMovieCast> mResults;

	public TmdbCastResponse(Integer id, List<TmdbMovieCast> results) {
		mId = id;
		mResults = results;
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public List<TmdbMovieCast> getResults() {
		return mResults;
	}

	public void setResults(List<TmdbMovieCast> results) {
		mResults = results;
	}

	@NonNull
	@Override
	public String toString() {
		return "id: " + mId;
	}
}
