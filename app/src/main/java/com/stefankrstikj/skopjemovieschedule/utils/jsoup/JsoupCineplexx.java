package com.stefankrstikj.skopjemovieschedule.utils.jsoup;

import android.util.Log;
import android.util.Pair;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;

public abstract class JsoupCineplexx extends Jsoup {
    private static String TAG = "JsoupCineplexxAbstract";

    static final String GENRE_SEPERATOR = " \\| ";
    static final int MAX_CHARACTERS = 22;


    JsoupCineplexx(){
        setTranslations();
    }

    abstract protected Movie parseMovie(Element element, List<MovieSchedule> movieSchedules) throws IOException;
    public abstract Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException;

    public void getDetailedInfo(Movie m, List<MovieSchedule> movieSchedules) throws IOException{
        String URL = "https://" + m.getDetailsURL();
        Document doc = org.jsoup.Jsoup.connect(URL).get();
        setPoster(m, doc);
        if(m.getStatus() == 1) {
            Log.v(TAG, "Setting schedule for URL: " + m.getDetailsURL());
            setMovieSchedules(movieSchedules, doc, m);
        }
        else
            setDetailsComingSoon(m, doc);
        setDisplayTitle(m);
    }

    void setPoster(Movie m, Document doc){
        Elements imgElements = doc.getElementsByClass("pull-left");
        m.setPosterURL("https://www.cineplexx.mk/" + imgElements.get(0).getElementsByTag("img").get(0).attr("src"));
    }

    void setMovieSchedules(List<MovieSchedule> movieSchedules, Document doc, Movie movie) throws IOException {
        Log.v(TAG, "Setting movie schedule: " + movie.getMovieTitle());
        Elements perDay =  doc.getElementsByClass("row-fluid separator time-row");

        // todo: fix crash when calling date-desc-label
        // System.out.println(doc);
//        String theaterName = doc.getElementsByClass("date-desc-label").get(0).text();
        String theaterName = "CINEPLEXX SKOPJE CITY MALL";
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
                        (movie.getMovieTitle(), theaterName, day, date, time, theaterHall, m3D, reservationURL);
                movieSchedules.add(movieSchedule);
            }
        }
    }


    private void setDetailsComingSoon(Movie movie, Document doc){
        Element element = doc.getElementsByClass("filmdetails").get(0);
        Elements tr = element.getElementsByTag("tr");
        String titleEnglish = tr.get(0).getElementsByTag("td").get(1).text();
        Log.v(TAG, "TitleEnglish: " + titleEnglish);
        movie.setMovieTitle(titleEnglish);
    }


    private String getEnglishDay(String day){
        if(day.contains(",")){
            day = day.split(",")[1].substring(1);
        }
        return dayTranslations.get(day);
    }
}
