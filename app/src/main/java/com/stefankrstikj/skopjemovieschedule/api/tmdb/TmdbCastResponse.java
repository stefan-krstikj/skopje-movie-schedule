package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;

import java.util.List;

public class TmdbCastResponse {
	@SerializedName("id")
	private Integer mId;
	@SerializedName("cast")
	private List<TmdbCast> mResults;

	public TmdbCastResponse(Integer id, List<TmdbCast> results) {
		mId = id;
		mResults = results;
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public List<TmdbCast> getResults() {
		return mResults;
	}

	public void setResults(List<TmdbCast> results) {
		mResults = results;
	}

	@NonNull
	@Override
	public String toString() {
		return "id: " + mId;
	}
}
