package mk.ukim.finki.skopjemovieschedule.utils.jsoup;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.utils.URLList;

public class JsoupMilenium extends JsoupAbstract {
    private static String TAG = "JsoupMilenium";
    static final int MAX_CHARACTERS = 22;

    private Document doc;
    private Element tt_timetable;

    public Pair<List<Movie>, List<MovieSchedule>> getPairMovieAndSchedule() throws IOException {
        List<Movie> movies = new ArrayList<>();
        List<MovieSchedule> movieSchedules = new ArrayList<>();
        try{
            Document doc = Jsoup.connect(URLList.URLMilenium).get();
            Element element = doc.getElementsByClass("tt_timetable small  ").get(0);
            Elements li = element.getElementsByTag("li");
            this.doc = doc;
            this.tt_timetable = element;
            for (Element e : li) {
                Movie parsed = parseMovie(e, movieSchedules);
                movies.add(parsed);
            }
            getMovieSchedules(movieSchedules, movies);
        } catch (IOException e) {
            e.printStackTrace();
            return new Pair<>(movies, movieSchedules);
        }



        return new Pair<>(movies, movieSchedules);
    }



    void setPoster(Movie m, Document doc) {
        Element imgClass = doc.getElementsByClass("vc_single_image-img attachment-full").get(0);
        String imgURL = imgClass.getElementsByTag("img").get(0).attr("srcset").split(" ")[0];
        m.setPosterURL(imgURL);
    }

    private void getMovieSchedules(List<MovieSchedule> movieSchedules, List<Movie> movies){
        Elements h3 = tt_timetable.getElementsByTag("h3");
        for(Element result : h3){
            String day = result.text();
            Elements list = result.nextElementSibling().getElementsByTag("li");
            for(Element listEl : list){
                String movieMkd = listEl.getElementsByTag("a").first().text();
                String dayEng = dayTranslations.get(day.trim());
                String titleEng = "";
                for(Movie m : movies){
                    if(m.getMovieTitleMKD().equals(movieMkd)){
                        titleEng = m.getMovieTitle();
                        break;
                    }
                }
                String startTime = listEl.getElementsByClass("value").first().text().substring(0,5);
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayEng.toUpperCase());

                LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
                localDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));

                MovieSchedule movieSchedule = 
                        new MovieSchedule(titleEng, "Kino Milenium", dayEng, localDate.toString(), startTime, "Кино Милениум Сала", "2D", null);
                movieSchedules.add(movieSchedule);
            }
        }
    }

    @SuppressLint("LongLogTag")
    public void getDetailedInfo(Movie movie, List<MovieSchedule> movieSchedules) throws IOException {
        Log.v(TAG + " getDetailedInfo(): ", movie.getMovieTitle());
        Log.v(TAG, "mCineplexxURL: " + movie.getDetailsURL());

        // buffer overload for kinomilenium
        Connection.Response response = Jsoup.connect(movie.getDetailsURL()).timeout(20 * 1000)
                .execute();
        final Document doc = Jsoup.parse(response.body());


//        Document doc = Jsoup.connect(movie.mCineplexxURL)
//                .timeout(20* 1000).get();

        String title = doc.getElementsByTag("h2").get(0).text();
        Log.v(TAG + " getDetailedInfo() english: ", title);
        Element el = doc.getElementsByClass("wpb_column vc_column_container vc_col-sm-8").get(0);
        Elements strong = el.getElementsByTag("strong");
        String Director = strong.get(0).text().substring(1);
        String Actors = strong.get(1).text().substring(1);
        String projectionStart;
        String runtime;
        String country;
        String genre;
        if(strong.get(2).text().length() == 0 || strong.get(2).text().length() == 1){
            projectionStart = strong.get(3).text().substring(1);
            runtime = strong.get(4).text();
            country = strong.get(5).text();
            genre = strong.get(6).text() ;
        }
        else{
            projectionStart = strong.get(2).text().substring(1);
            runtime = strong.get(3).text();
            country = strong.get(4).text();
            genre = strong.get(5).text() ;
        }
        String year = projectionStart.split("\\.")[2];
        movie.setStatus(1);
        movie.setMovieTitle(title);
        movie.setDirector(Director);
        movie.setActors(Actors);
        movie.setYear(year);
        movie.setRuntime(runtime);
        movie.setCountry(country);
        setPoster(movie, doc);

    }

    @SuppressLint("LongLogTag")
    protected Movie parseMovie(Element e, List<MovieSchedule> movieSchedules) throws IOException {
        String titleMKD = e.getElementsByTag("a").get(0).text();
//        String startTime = e.getElementsByClass("value").get(0).text().substring(0, 4);
        String URL = e.getElementsByTag("a").get(0).attr("href");

        Movie movie = new Movie(titleMKD, titleMKD, null, null, null, null);
        movie.setDetailsURL(URL);

        // changes title from MKD to English
        getDetailedInfo(movie, movieSchedules);

        // fixes title to english
        Log.v(TAG, "Calling getOMDBInfo for " + movie.getMovieTitle());
        getOMDBInfo(movie);

        setDisplayTitle(movie);
        return movie;
    }
}
