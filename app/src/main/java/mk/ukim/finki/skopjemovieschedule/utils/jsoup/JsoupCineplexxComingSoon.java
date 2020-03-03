package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.util.Log;
import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.utils.URLList;

public class JsoupCineplexxComingSoon extends JsoupCineplexxAbstract {
    private static String TAG = "JsoupCineplexxComingSoon";
    private static String URL = URLList.URLCineplexxComingSoon;

    protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException {
        Log.v(TAG, "ParseMovie called");
        Elements a = element.getElementsByTag("a");
        Elements p = element.getElementsByTag("p");
        String title = a.get(1).text();
        String cineplexxURL = a.get(1).attr("href");
        cineplexxURL = cineplexxURL.substring(2, cineplexxURL.length());
        String displayTitle = title;
        String[] genres = p.get(1).text().split(GENRE_SEPERATOR);
        String mProjectionStart = p.get(2).text().split(":")[1].substring(1);
        Movie movie = new Movie(title, title, genres[0], cineplexxURL, displayTitle, mProjectionStart);

        movie.setStatus(0);

        Log.v(TAG, "Calling getOMDBInfo for " + movie.mMovieTitle);
        getOMDBInfo(movie);

        getDetailedInfo(movie, movieSchedules);
        return movie;
    }

    public Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException {
        List<Movie> movies = new ArrayList<>();
        List<MovieSchedule> movieSchedules = new ArrayList<>();
        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.getElementsByClass("span3");
        for(Element el : elements){
            movies.add(parseMovie(el, movieSchedules));
        }

        return new Pair<>(movies, movieSchedules);
    }
}
