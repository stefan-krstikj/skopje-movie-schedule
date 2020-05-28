package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "tmdb_movie_detailed")
public class TmdbMovieDetailed {

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private Double mPopularity;

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    private String mVoteCount;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String mVoteAverage;

    @ColumnInfo(name = "video")
    @SerializedName("video")
    private boolean mVideo;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String mPosterPath;

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer mId;

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    private boolean mAdult;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String mOriginalLanguage;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    private String mOriginalTitle;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mTitle;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String mOverview;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String mReleaseDate;

    @ColumnInfo(name = "result_type")
    private String mResultType;

    public TmdbMovieDetailed(Double popularity, String voteCount, String voteAverage, boolean video, String posterPath, @NotNull Integer id, boolean adult, String backdropPath, String originalLanguage, String originalTitle, String title, String overview, String releaseDate, String resultType) {
        mPopularity = popularity;
        mVoteCount = voteCount;
        mVoteAverage = voteAverage;
        mVideo = video;
        mPosterPath = posterPath;
        mId = id;
        mAdult = adult;
        mBackdropPath = backdropPath;
        mOriginalLanguage = originalLanguage;
        mOriginalTitle = originalTitle;
        mTitle = title;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mResultType = resultType;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(String voteCount) {
        mVoteCount = voteCount;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
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
    public Integer getId() {
        return mId;
    }

    public void setId(@NotNull Integer id) {
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

    public String getResultType() {
        return mResultType;
    }

    public void setResultType(String resultType) {
        mResultType = resultType;
    }

    @NonNull
    @Override
    public String toString() {
        return mTitle;
    }
}
