package mk.ukim.finki.skopjemovieschedule.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "movie")
public class Movie implements Serializable {
    // todo: make private
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_title")
    @SerializedName("Title")
    public String mMovieTitle;

    @ColumnInfo(name = "movie_title_mkd")
    public String mMovieTitleMKD;

    @ColumnInfo(name = "movie_display_title")
    public String mMovieDisplayTitle;

    @ColumnInfo(name = "genres_mkd")
    public String mGenresMKD;

    @ColumnInfo(name = "cineplexx_url")
    public String mCineplexxURL;

    @ColumnInfo(name = "poster")
    public String mPosterURL;

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    public String mYear;

    @ColumnInfo(name = "rated")
    @SerializedName("Rated")
    public String mRated;

    @ColumnInfo(name = "runtime")
    @SerializedName("Runtime")
    public String mRuntime;

    @ColumnInfo(name = "genres")
    @SerializedName("Genre")
    public String mGenre;

    @ColumnInfo(name = "director")
    @SerializedName("Director")
    public String mDirector;

    @ColumnInfo(name = "writers")
    public String mWriter;

    @ColumnInfo(name = "actors")
    public String mActors;

    @ColumnInfo(name = "plot")
    public String mPlot;

    @ColumnInfo(name = "language")
    public String mLanguage;

    @ColumnInfo(name = "country")
    public String mCountry;

    @ColumnInfo(name = "status") // true = in theaters, false = coming soon
    public int mStatus;

    @ColumnInfo(name = "theater_name")
    public String mTheaterName;

//    public String Writer;
//    public String Actors;
//    public String Plot;
//    public String Language;
//    public String Country;
//    public String Awards;
//    public String Poster;

//    @ColumnInfo
//    public String mMovieId;

//    private String mRuntime;
//    private String mTicketCost;

//    private Boolean mIs3D;
//    private HashMap<String, List<String>> mMovieProjections; // <Day, List<>>


    public Movie(@NonNull String mMovieTitle, String mMovieTitleMKD, String mGenresMKD, String mCineplexxURL, String mMovieDisplayTitle) {
        this.mMovieTitle = mMovieTitle;
        this.mMovieTitleMKD = mMovieTitleMKD;
        this.mMovieDisplayTitle = mMovieDisplayTitle;
        this.mGenresMKD = mGenresMKD;
        this.mCineplexxURL = mCineplexxURL;
    }

    public void fillOmdbInfo(String mYear, String mRuntime, String mRated, String mDirector, String mGenre,
                             String mWriter, String mActors, String mPlot, String mLanguage, String mCountry, String mPosterURL,
                             String mTheaterName){
        this.mYear = mYear;
        this.mRuntime = mRuntime;
        this.mRated = mRated;
        this.mDirector = mDirector;
        this.mGenre = mGenre;
        this.mWriter = mWriter;
        this.mActors = mActors;
        this.mPlot = mPlot;
        this.mLanguage = mLanguage;
        this.mCountry = mCountry;
        //todo: fix mPosterURL
//        this.mPosterURL = mPosterURL;
        this.mTheaterName = mTheaterName;
    }

    public void setStatus(int status){
        this.mStatus = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title:" + mMovieTitle + " mGenresMKD:" + mGenresMKD+ "\n";
    }
    
}
