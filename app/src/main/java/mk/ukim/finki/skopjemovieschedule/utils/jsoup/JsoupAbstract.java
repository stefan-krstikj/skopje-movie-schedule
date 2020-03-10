package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.util.Log;
import android.util.Pair;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbApiClient;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbMovie;

public abstract class JsoupAbstract {
    private static String TAG = "JsoupAbstract";

    static final String GENRE_SEPERATOR = " \\| ";
    static final int MAX_CHARACTERS = 22;
    static HashMap<String, String> dayTranslations = new HashMap<String, String>();

    JsoupAbstract() {
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

        // todo: fix crash
        movie.setMovieDisplayTitle(movie.getMovieTitle());
        if(movie.getMovieTitle() != null){
            if(movie.getMovieTitle().length() >= MAX_CHARACTERS){
                movie.setMovieDisplayTitle(movie.getMovieTitle().substring(0, MAX_CHARACTERS-3) + "...");
            }
        }

    }

    void getOMDBInfo(Movie movie) throws IOException {
        omdbMovie mo = omdbApiClient.getMovie(movie.getMovieTitle().toLowerCase());
        if (mo.Title == null){
            Log.v(TAG, "Received null for: " + movie.getMovieTitle() + ", cutting string...");
            String[] titleSplit = movie.getMovieTitle().split(" " );
            String shortTitle  = "";
            if(titleSplit.length >= 3)
                shortTitle = titleSplit[0] + " " + titleSplit[1] + " " + titleSplit[2];
            else if(titleSplit.length >= 2)
                shortTitle = titleSplit[0] + " " + titleSplit[1];

            mo = omdbApiClient.getMovie(shortTitle);
        }

        if(mo.Title == null)
            Log.v(TAG, "Found NULL OMDB for: " + movie.getMovieTitle());

        if(mo.Title != null){
            movie.fillOmdbInfo(mo.Title, mo.Year, mo.Runtime,
                    mo.Rated, mo.Director, mo.Genre, mo.Writer, mo.Actors, mo.Plot, mo.Language, mo.Country, "Cineplexx", mo.Poster);
        }

    }
}
