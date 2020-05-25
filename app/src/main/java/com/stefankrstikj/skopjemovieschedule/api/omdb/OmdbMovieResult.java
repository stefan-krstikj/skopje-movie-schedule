package com.stefankrstikj.skopjemovieschedule.api.omdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class OmdbMovieResult {

    @SerializedName("Title")
    public String mTitle;

    @SerializedName("Year")
    public String mYear;

    @SerializedName("Rated")
    public String mRated;

    @SerializedName("Runtime")
    public String mRuntime;

    @SerializedName("Genre")
    public String mGenre;

    @SerializedName("Director")
    public String mDirector;

    @SerializedName("Writer")
    public String mWriter;

    @SerializedName("Actors")
    public String mActors;

    @SerializedName("Plot")
    public String mPlot;

    @SerializedName("Language")
    public String mLanguage;

    @SerializedName("Country")
    public String mCountry;

    @SerializedName("Awards")
    public String mAwards;

    @SerializedName("Poster")
    public String mPoster;


    public OmdbMovieResult(String title, String year, String rated, String runtime,
                           String genre, String director, String writer, String actors,
                           String plot, String language, String country, String awards, String poster) {
        mTitle = title;
        mYear = year;
        mRated = rated;
        mRuntime = runtime;
        mGenre = genre;
        mDirector = director;
        mWriter = writer;
        mActors = actors;
        mPlot = plot;
        mLanguage = language;
        mCountry = country;
        mAwards = awards;
        mPoster = poster;
    }

    @NonNull
    @Override
    public String toString() {
        return mTitle + " " + mYear + " " + mWriter + " " + mCountry + "\n";
    }
}
