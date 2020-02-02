package mk.ukim.finki.skopjemovieschedule.jsoup;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.Movie;

public class jsoupUtils {
    private static final String GENRE_SEPERATOR = " \\| ";
    private static final int MAX_CHARACTERS = 22;
    private static final String TAG = "jsoupUtils";

    private String URL;

    public jsoupUtils(String URL) {
        this.URL = URL;
    }

    public List<Movie> getAllMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();
        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.getElementsByClass("overview-element separator");
        for(Element el : elements){
            movies.add(parseMovie(el));
        }
        return movies;
    }

    private Movie parseMovie(Element element) throws IOException {
        Elements a = element.getElementsByTag("a");
        Elements p = element.getElementsByTag("p");
        String titleMKD = a.get(1).text();
        String cineplexxURL = a.get(1).attr("href");
        cineplexxURL = cineplexxURL.substring(2, cineplexxURL.length());
        String title = p.get(1).text();
        String displayTitle = title;
        if(title.length() >= MAX_CHARACTERS){
            displayTitle = title.substring(0, MAX_CHARACTERS-3) + "...";
        }

        String genres[] = p.get(2).text().split(GENRE_SEPERATOR);
        Movie movie = new Movie(title, titleMKD, genres[0], cineplexxURL, displayTitle);
        movie = getHighResPoster(movie);
        return movie;
    }

    private Movie getHighResPoster(Movie m) throws IOException {
        String URL = "https://" + m.mCineplexxURL;
        Log.v(TAG, "URL: " + URL);
        Document doc = Jsoup.connect(URL).get();
        Elements address =  doc.getElementsByClass("pull-left");
        String img = "https://www.cineplexx.mk/" + address.get(0).getElementsByTag("img").get(0).attr("src");
        m.mPosterURL = img;
        return m;
    }
}
