package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "tmdb_movie")
public class TmdbMovie {

    @SerializedName("popularity")
    private String mPopularity;

    @SerializedName("vote_count")
    private String mVoteCount;

    @SerializedName("video")
    private boolean mVideo;

    @SerializedName("poster_path")
    private String mPosterPath;

    @PrimaryKey
    @NotNull
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

    @SerializedName("title")
    private String mTitle;

    @SerializedName("vote_average")
    private String mVoteAverage;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;

    public TmdbMovie(String popularity, String voteCount, boolean video, String posterPath, String id, boolean adult, String backdropPath, String originalLanguage, String originalTitle, String title, String voteAverage, String overview, String releaseDate) {
        mPopularity = popularity;
        mVoteCount = voteCount;
        mVideo = video;
        mPosterPath = posterPath;
        mId = id;
        mAdult = adult;
        mBackdropPath = backdropPath;
        mOriginalLanguage = originalLanguage;
        mOriginalTitle = originalTitle;
        mTitle = title;
        mVoteAverage = voteAverage;
        mOverview = overview;
        mReleaseDate = releaseDate;
    }

    public String getPopularity() {
        return mPopularity;
    }

    public void setPopularity(String popularity) {
        mPopularity = popularity;
    }

    public String getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(String voteCount) {
        mVoteCount = voteCount;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    @NotNull
    public String getId() {
        return mId;
    }

    public void setId(@NotNull String id) {
        mId = id;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    @NonNull
    @Override
    public String toString() {
        return mTitle;
    }
}
