package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.util.Log;
import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;

public abstract class JsoupCineplexxAbstract extends JsoupAbstract{
    private static String TAG = "JsoupCineplexxAbstract";

    static final String GENRE_SEPERATOR = " \\| ";
    static final int MAX_CHARACTERS = 22;


    JsoupCineplexxAbstract(){
        setTranslations();
    }

    abstract protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException;
    public abstract Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException;

    public void getDetailedInfo(Movie m, List<MovieSchedule> movieSchedules) throws IOException{
        String URL = "https://" + m.mCineplexxURL;
        Document doc = Jsoup.connect(URL).get();
        setPoster(m, doc);
        if(m.mStatus == 1)
            setMovieSchedules(movieSchedules, doc, m);
        else
            setDetailsComingSoon(m, doc);
        setDisplayTitle(m);
    }

    void setPoster(Movie m, Document doc){
        Elements imgElements = doc.getElementsByClass("pull-left");
        m.mPosterURL = "https://www.cineplexx.mk/" + imgElements.get(0).getElementsByTag("img").get(0).attr("src");
    }

    void setMovieSchedules(List<MovieSchedule> movieSchedules, Document doc, Movie movie) throws IOException {
        Elements perDay =  doc.getElementsByClass("row-fluid separator time-row");
        String theaterName = doc.getElementsByClass("date-desc-label").get(0).text();
        for(Element e : perDay){
            String day = getEnglishDay(e.getElementsByTag("span").get(0).text());
            String date = e.getElementsByTag("time").get(0).text();
            Elements perDaySchedules = e.getElementsByClass("span3");
            for(Element el : perDaySchedules){
                String reservationURL = el.getElementsByTag("a").get(0).attr("data-link");
                Elements p = el.getElementsByTag("p");
                String time = p.get(0).text();
                String theaterHall = p.get(1).text();
                String m3D = p.get(2).text();
                MovieSchedule movieSchedule = new MovieSchedule
                        (movie.mMovieTitle, theaterName, day, date, time, theaterHall, m3D, reservationURL);
                movieSchedules.add(movieSchedule);
            }
        }
    }


    private void setDetailsComingSoon(Movie movie, Document doc){
        Element element = doc.getElementsByClass("filmdetails").get(0);
        Elements tr = element.getElementsByTag("tr");
        String titleEnglish = tr.get(0).getElementsByTag("td").get(1).text();
        Log.v(TAG, "TitleEnglish: " + titleEnglish);
        movie.mMovieTitle = titleEnglish;
    }


    private String getEnglishDay(String day){
        if(day.contains(",")){
            day = day.split(",")[1].substring(1);
        }
        return dayTranslations.get(day);
    }
}
