package com.stefankrstikj.skopjemovieschedule.api_response.tmdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

import java.util.List;

public class TmdbMovieDiscoveryResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("results")
    List<TmdbMovie> mResults;

    public TmdbMovieDiscoveryResponse(int page, int totalResults, int totalPages, List<TmdbMovie> results) {
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

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public List<TmdbMovie> getResults() {
        return mResults;
    }

    public void setResults(List<TmdbMovie> results) {
        mResults = results;
    }
}
