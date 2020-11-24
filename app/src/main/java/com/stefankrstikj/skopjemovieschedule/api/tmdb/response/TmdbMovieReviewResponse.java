package com.stefankrstikj.skopjemovieschedule.api.tmdb.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.List;

public class TmdbMovieReviewResponse {
	@SerializedName("id")
	private Integer mId;

	@SerializedName("page")
	private Integer mPage;

	@SerializedName("results")
	private List<TmdbMovieReview> mResults;

	@SerializedName("total_pages")
	private Integer mTotalPages;

	@SerializedName("total_results")
	private Integer mTotalResults;

	public TmdbMovieReviewResponse(Integer id, Integer page, List<TmdbMovieReview> results, Integer totalPages, Integer totalResults) {
		mId = id;
		mPage = page;
		mResults = results;
		mTotalPages = totalPages;
		mTotalResults = totalResults;
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public Integer getPage() {
		return mPage;
	}

	public void setPage(Integer page) {
		mPage = page;
	}

	public List<TmdbMovieReview> getResults() {
		return mResults;
	}

	public void setResults(List<TmdbMovieReview> results) {
		mResults = results;
	}

	public Integer getTotalPages() {
		return mTotalPages;
	}

	public void setTotalPages(Integer totalPages) {
		mTotalPages = totalPages;
	}

	public Integer getTotalResults() {
		return mTotalResults;
	}

	public void setTotalResults(Integer totalResults) {
		mTotalResults = totalResults;
	}

	@NonNull
	@Override
	public String toString() {
		return "id: " + mId;
	}
}
