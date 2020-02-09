package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;

public abstract class JsoupUtilsAbstract {

    static final String GENRE_SEPERATOR = " \\| ";
    static final int MAX_CHARACTERS = 22;

    JsoupUtilsAbstract(){
    }

//    abstract public List<Movie> getMovies() throws IOException;

    abstract protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException;

    Movie getHighResPoster(Movie m) throws IOException {
        String URL = "https://" + m.mCineplexxURL;
        Document doc = Jsoup.connect(URL).get();
        Elements address =  doc.getElementsByClass("pull-left");
        String img = "https://www.cineplexx.mk/" + address.get(0).getElementsByTag("img").get(0).attr("src");
        m.mPosterURL = img;
        return m;
    }

    public abstract Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException;

    void addSchedule(List<MovieSchedule> movieSchedules, String movieTitle, Elements p){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = calendar.getTime();
        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

        for(int i = 4; i < p.size()-2; i++){
            MovieSchedule movieSchedule = new MovieSchedule(movieTitle);
            String time = p.get(i++).text();
            String hall =  p.get(i++).text();
            String is3D = p.get(i).text();
            movieSchedule.insertScreening(day, time, hall, is3D);
            movieSchedules.add(movieSchedule);
        }
    }
}
