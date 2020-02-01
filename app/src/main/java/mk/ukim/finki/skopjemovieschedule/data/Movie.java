package mk.ukim.finki.skopjemovieschedule.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;

@Entity(tableName = "movie")
public class Movie {
    // todo: make private
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_title")
    public String mMovieTitle;

    @ColumnInfo(name = "movie_title_mkd")
    public String mMovieTitleMKD;

    @ColumnInfo(name = "genres")
    public String mGenres;

    @ColumnInfo(name = "cineplexx_url")
    public String mCineplexxURL;

    @ColumnInfo(name = "poster")
    public String mPosterURL;

//    @ColumnInfo
//    public String mMovieId;

//    private String mRuntime;
//    private String mTicketCost;

//    private Boolean mIs3D;
//    private HashMap<String, List<String>> mMovieProjections; // <Day, List<>>


    public Movie(@NonNull String mMovieTitle, String mMovieTitleMKD, String mGenres, String mCineplexxURL) {
        this.mMovieTitle = mMovieTitle;
        this.mMovieTitleMKD = mMovieTitleMKD;
        this.mGenres = mGenres;
        this.mCineplexxURL = mCineplexxURL;
    }


    @NonNull
    @Override
    public String toString() {
        return mMovieTitle + " " + mGenres + "\n";
    }
    
}
