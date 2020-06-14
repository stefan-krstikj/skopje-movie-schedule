package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import com.google.gson.annotations.SerializedName;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;

import java.util.List;

public class TmdbMovieVideosResponse {
	@SerializedName("id")
	private Integer mId;

	@SerializedName("results")
	private List<TmdbMovieVideo> mResponse;

	public TmdbMovieVideosResponse(Integer id, List<TmdbMovieVideo> response) {
		mId = id;
		mResponse = response;
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public List<TmdbMovieVideo> getResponse() {
		return mResponse;
	}

	public void setResponse(List<TmdbMovieVideo> response) {
		mResponse = response;
	}
}
