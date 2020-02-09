package mk.ukim.finki.skopjemovieschedule.asynctask.jsoup;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.utils.jsoup.JsoupCineplexxComingSoon;

public class JsoupCineplexxComingSoonAsynctask extends JsoupCineplexxAsyncTask {
    private static String TAG = "JsoupCineplexxComingSoonAsynctask";

    public JsoupCineplexxComingSoonAsynctask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(String... strings) {
        JsoupCineplexxComingSoon jsoupUtils = new JsoupCineplexxComingSoon();
        try {
            Pair<List<Movie>, List<MovieSchedule>> pair = jsoupUtils.getPairMovieAndSchedule();
//            List<Movie> allMovies = jsoupUtils.getMovies();
//            List<MovieSchedule> allMovieSchedules = jsoupUtils.getMovieSchedules();
            joinMovies(pair.first);
            Log.v(TAG, "doInBackground() Received movies: " + pair.first.toString());
            Log.v(TAG, "doInBackground() Received schedules: " + pair.second.toString());
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


//    @Override
//    protected List<Movie> doInBackground(String... strings) {
//        JsoupCineplexxComingSoon jsoupUtils = new JsoupCineplexxComingSoon();
//        try {
//            List<Movie> allMovies = jsoupUtils.getMovies();
//            allMovies = joinMovies(allMovies);
//            Log.v(TAG, "doInBackground() Received movies: " + allMovies.toString());
//            return allMovies;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }



}
