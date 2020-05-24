package com.stefankrstikj.skopjemovieschedule.api_response.tmdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TmdbMovieDiscoveryResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("results")
    List<TmdbMovieDiscoveryResult> mResults;

    public TmdbMovieDiscoveryResponse(int page, int totalResults, int totalPages, List<TmdbMovieDiscoveryResult> results) {
        mPage = page;
        mTotalResults = totalResults;
        mTotalPages = totalPages;
        mResults = results;
    }

    @NonNull
    @Override
    public String toString() {
        return "page:" + mPage + ", total_results:" + mTotalResults + ", total_pages: " + mTotalPages + ", results: " + mResults.toString();
    }
}
