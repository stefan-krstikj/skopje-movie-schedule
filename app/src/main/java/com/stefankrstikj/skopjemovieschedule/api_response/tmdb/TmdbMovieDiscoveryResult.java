package com.stefankrstikj.skopjemovieschedule.api_response.tmdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TmdbMovieDiscoveryResult {

    @SerializedName("popularity")
    private String mPopularity;

    @SerializedName("vote_count")
    private String mVoteCount;

    @SerializedName("video")
    private boolean mVideo;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("id")
    private String mId;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("genre_ids")
    private List<String> mGenreIds;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("vote_average")
    private String mVoteAverage;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;

    public TmdbMovieDiscoveryResult(String popularity, String voteCount, boolean video, String posterPath, String id, boolean adult, String backdropPath, String originalLanguage, String originalTitle, List<String> genreIds, String title, String voteAverage, String overview, String releaseDate) {
        mPopularity = popularity;
        mVoteCount = voteCount;
        mVideo = video;
        mPosterPath = posterPath;
        mId = id;
        mAdult = adult;
        mBackdropPath = backdropPath;
        mOriginalLanguage = originalLanguage;
        mOriginalTitle = originalTitle;
        mGenreIds = genreIds;
        mTitle = title;
        mVoteAverage = voteAverage;
        mOverview = overview;
        mReleaseDate = releaseDate;
    }

    @NonNull
    @Override
    public String toString() {
        return mTitle;
    }
}
