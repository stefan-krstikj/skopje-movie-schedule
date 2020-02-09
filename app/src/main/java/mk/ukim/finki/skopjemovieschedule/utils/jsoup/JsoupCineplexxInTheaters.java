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

public class JsoupCineplexxInTheaters extends JsoupUtilsAbstract {

    private static String URL = URLList.URLCineplexxInTheaters;

    @Override
    protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException {
        Elements a = element.getElementsByTag("a");
        Elements p = element.getElementsByTag("p");
        String titleMKD = a.get(1).text();
        String cineplexxURL = a.get(1).attr("href");
        cineplexxURL = cineplexxURL.substring(2);
        String title = p.get(1).text();
        String displayTitle = title;
        if(title.length() >= MAX_CHARACTERS){
            displayTitle = title.substring(0, MAX_CHARACTERS-3) + "...";
        }

        String[] genres = p.get(2).text().split(GENRE_SEPERATOR);
        Movie movie = new Movie(title, titleMKD, genres[0], cineplexxURL, displayTitle);
        movie = getHighResPoster(movie);
        movie.setStatus(1);


        addSchedule(movieSchedules, title, p);

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
