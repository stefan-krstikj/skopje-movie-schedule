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

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbApiClient;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbMovie;
import mk.ukim.finki.skopjemovieschedule.utils.URLList;

public class JsoupCineplexxInTheaters extends JsoupCineplexxAbstract {
    private static String TAG = "JsoupCineplexxInTheaters";
    private static String URL = URLList.URLCineplexxInTheaters;

    protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException {
        Elements a = element.getElementsByTag("a");
        Elements p = element.getElementsByTag("p");
        String titleMKD = a.get(1).text();
        String cineplexxURL = a.get(1).attr("href");
        cineplexxURL = cineplexxURL.substring(2);
        String title = p.get(1).text();
        String displayTitle = title;

        String[] genres = p.get(2).text().split(GENRE_SEPERATOR);
        String mProjectionStart = p.get(3).text().split(":")[1].substring(1);
        Movie movie = new Movie(title, titleMKD, genres[0], cineplexxURL, displayTitle, mProjectionStart);
        movie.setStatus(1);

        getOMDBInfo(movie);
        getDetailedInfo(movie, movieSchedules);

        return movie;
    }

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
