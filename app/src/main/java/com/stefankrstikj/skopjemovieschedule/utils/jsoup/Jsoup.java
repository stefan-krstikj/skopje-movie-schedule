package com.stefankrstikj.skopjemovieschedule.utils.jsoup;

import android.util.Log;
import android.util.Pair;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.api_response.omdb.OmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.api_response.omdb.OmdbMovieResult;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;

public abstract class Jsoup {
    private static String TAG = "JsoupAbstract";

    static final String GENRE_SEPERATOR = " \\| ";
    static HashMap<String, String> dayTranslations = new HashMap<String, String>();

    Jsoup() {
        setTranslations();
    }

    abstract protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException;
    public abstract Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException;
    public abstract void getDetailedInfo(Movie m, List<MovieSchedule> movieSchedules) throws IOException;
    abstract void setPoster(Movie m, Document doc);

    void setTranslations(){
        dayTranslations = new HashMap<>();
        dayTranslations.put("Пон", "Monday"); dayTranslations.put("Вто", "Tuesday");
        dayTranslations.put("Сре", "Wednesday"); dayTranslations.put("Чет", "Thursday");
        dayTranslations.put("Пет", "Friday"); dayTranslations.put("Саб", "Saturday"); dayTranslations.put("Нед", "Sunday");

        dayTranslations.put("понеделник", "Monday"); dayTranslations.put("вторник", "Tuesday");
        dayTranslations.put("среда", "Wednesday"); dayTranslations.put("четврток", "Thursday");
        dayTranslations.put("петок", "Friday"); dayTranslations.put("сабота", "Saturday"); dayTranslations.put("недела", "Sunday");
    }

    void setDisplayTitle(Movie movie){
        // todo: fix crash (if movie title is null? or movie is null?)
        movie.setMovieDisplayTitle(MovieUtils.getDisplayTitle(movie));

    }

    void getOMDBInfo(Movie movie) throws IOException {
        OmdbMovieResult mo = OmdbApiClient.getMovie(movie.getMovieTitle().toLowerCase());
        if (mo.mTitle == null){
            Log.v(TAG, "Received null for: " + movie.getMovieTitle() + ", cutting string...");
            String[] titleSplit = movie.getMovieTitle().split(" " );
            String shortTitle  = "";
            if(titleSplit.length >= 3)
                shortTitle = titleSplit[0] + " " + titleSplit[1] + " " + titleSplit[2];
            else if(titleSplit.length >= 2)
                shortTitle = titleSplit[0] + " " + titleSplit[1];

            mo = OmdbApiClient.getMovie(shortTitle);
        }

        if(mo.mTitle == null)
            Log.v(TAG, "Found NULL OMDB for: " + movie.getMovieTitle());

        if(mo.mTitle != null){
            movie.fillOmdbInfo(mo.mTitle, mo.mYear, mo.mRuntime,
                    mo.mRated, mo.mDirector, mo.mGenre, mo.mWriter, mo.mActors, mo.mPlot, mo.mLanguage, mo.mCountry, "Cineplexx", mo.mPoster);
        }

    }
}
