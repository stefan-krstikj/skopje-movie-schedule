package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.utils.URLList;

public class JsoupCineplexxComingSoon extends JsoupUtilsAbstract {

    private static String URL = URLList.URLCineplexxComingSoon;



//    @Override
//    public List<Movie> getMovies() throws IOException {
//        List<Movie> movies = new ArrayList<>();
//        Document doc = Jsoup.connect(URL).get();
//        Elements elements = doc.getElementsByClass("span3");
//        for(Element el : elements){
//            movies.add(parseMovie(el));
//        }
//
//        return movies;
//    }

    @Override
    protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException {
        Elements a = element.getElementsByTag("a");
        Elements p = element.getElementsByTag("p");
        String title = a.get(1).text();
        String cineplexxURL = a.get(1).attr("href");
        cineplexxURL = cineplexxURL.substring(2, cineplexxURL.length());
        String displayTitle = title;
        if(title.length() >= MAX_CHARACTERS){
            displayTitle = title.substring(0, MAX_CHARACTERS-3) + "...";
        }

        String[] genres = p.get(1).text().split(GENRE_SEPERATOR);
        // todo: add start projection p.get(2)

        Movie movie = new Movie(title, null, genres[0], cineplexxURL, displayTitle);
        movie = getHighResPoster(movie);
        movie.setStatus(0);
//        Log.v(TAG, "comingSoonParse: " + movie.toString());
//        addSchedule(movieSchedules, title, p);
        // todo: fix
        movieSchedules.add(new MovieSchedule("Blank"));
        return movie;
    }

    @Override
    public Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException {
        List<Movie> movies = new ArrayList<>();
        List<MovieSchedule> movieSchedules = new ArrayList<>();
        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.getElementsByClass("overview-element separator");
        for(Element el : elements){
            movies.add(parseMovie(el, movieSchedules));
        }
        return new Pair<>(movies, movieSchedules);
    }
}
