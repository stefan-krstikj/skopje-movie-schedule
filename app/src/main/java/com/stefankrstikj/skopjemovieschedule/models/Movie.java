package com.stefankrstikj.skopjemovieschedule.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "movie")
public class Movie implements Serializable {
    private static String TAG = "Movie";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_title")
    @SerializedName("Title")
    private String mMovieTitle;

    @ColumnInfo(name = "movie_title_mkd")
    private String mMovieTitleMKD;

    @ColumnInfo(name = "movie_display_title")
    private String mMovieDisplayTitle;

    @ColumnInfo(name = "genres_mkd")
    private String mGenresMKD;

    @ColumnInfo(name = "details_url")
    private String mDetailsURL;

    @ColumnInfo(name = "poster")
    private String mPosterURL;

    @ColumnInfo(name = "year")
    private String mYear;

    @ColumnInfo(name = "rated")
    private String mRated;

    @ColumnInfo(name = "runtime")
    private String mRuntime;

    @ColumnInfo(name = "genres")
    private String mGenre;

    @ColumnInfo(name = "director")
    private String mDirector;

    @ColumnInfo(name = "writers")
    private String mWriter;

    @ColumnInfo(name = "actors")
    private String mActors;

    @ColumnInfo(name = "plot")
    private String mPlot;

    @ColumnInfo(name = "language")
    private String mLanguage;

    @ColumnInfo(name = "country")
    private String mCountry;

    @ColumnInfo(name = "status") // true = in theaters, false = coming soon
    private int mStatus;

    @ColumnInfo(name = "theater_name")
    private String mTheaterName;

    @ColumnInfo(name = "projection_start")
    private String mProjectionStart;

    public Movie(@NonNull String mMovieTitle, String mMovieTitleMKD, String mGenresMKD, String mDetailsURL, String mMovieDisplayTitle, String mProjectionStart) {
        this.mMovieTitle = mMovieTitle;
        this.mMovieTitleMKD = mMovieTitleMKD;
        this.mMovieDisplayTitle = mMovieDisplayTitle;
        this.mGenresMKD = mGenresMKD;
        this.mDetailsURL = mDetailsURL;
        this.mProjectionStart = mProjectionStart;
    }

    public void fillOmdbInfo(String mMovieTitle, String mYear, String mRuntime, String mRated, String mDirector, String mGenre,
                             String mWriter, String mActors, String mPlot, String mLanguage, String mCountry,
                             String mTheaterName, String mPosterURL){
        this.mMovieTitle = mMovieTitle;
        this.mYear = mYear;
        this.mRuntime = mRuntime;
        this.mRated = mRated;

        if(mDirector != null){
            String[] split = mDirector.split(", ");
            if(split.length > 2){
                this.mDirector = split[0] + ", " +split[1];
            }else
                this.mDirector = mDirector;
        }
        if(mGenre != null){
            String[] split = mGenre.split(", ");
            Log.v(TAG, "mGenre: " + mGenre);
            if(split.length > 2){
                this.mGenre = split[0] + ", " + split[1];
                Log.v(TAG, "Split: " + this.mGenre);
            }else{
                this.mGenre = mGenre;
            }
        }

        this.mGenre = mGenre;
        this.mWriter = mWriter;
        this.mActors = mActors;
        this.mPlot = mPlot;
        this.mLanguage = mLanguage;
        this.mCountry = mCountry;
        //todo: fix mPosterURL
        this.mPosterURL = mPosterURL;
        this.mTheaterName = mTheaterName;
    }

    public void setStatus(int status){
        this.mStatus = status;
    }

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        Movie.TAG = TAG;
    }

    @NonNull
    public String getMovieTitle() {
        return mMovieTitle;
    }

    public void setMovieTitle(@NonNull String movieTitle) {
        mMovieTitle = movieTitle;
    }

    public String getMovieTitleMKD() {
        return mMovieTitleMKD;
    }

    public void setMovieTitleMKD(String movieTitleMKD) {
        mMovieTitleMKD = movieTitleMKD;
    }

    public String getMovieDisplayTitle() {
        return mMovieDisplayTitle;
    }

    public void setMovieDisplayTitle(String movieDisplayTitle) {
        mMovieDisplayTitle = movieDisplayTitle;
    }

    public String getGenresMKD() {
        return mGenresMKD;
    }

    public void setGenresMKD(String genresMKD) {
        mGenresMKD = genresMKD;
    }

    public String getDetailsURL() {
        return mDetailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        mDetailsURL = detailsURL;
    }

    public String getPosterURL() {
        return mPosterURL;
    }

    public void setPosterURL(String posterURL) {
        mPosterURL = posterURL;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getRated() {
        return mRated;
    }

    public void setRated(String rated) {
        mRated = rated;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public void setRuntime(String runtime) {
        mRuntime = runtime;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        mActors = actors;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getTheaterName() {
        return mTheaterName;
    }

    public void setTheaterName(String theaterName) {
        mTheaterName = theaterName;
    }

    public String getProjectionStart() {
        return mProjectionStart;
    }

    public void setProjectionStart(String projectionStart) {
        mProjectionStart = projectionStart;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title:" + mMovieTitle + " mGenresMKD:" + mGenresMKD+ "\n";
    }
    
}
