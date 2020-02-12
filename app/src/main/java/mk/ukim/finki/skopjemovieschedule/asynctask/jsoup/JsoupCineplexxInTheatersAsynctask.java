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
import mk.ukim.finki.skopjemovieschedule.utils.jsoup.JsoupCineplexxInTheaters;


public class JsoupCineplexxInTheatersAsynctask extends JsoupCineplexxAsyncTask {
    private static final String TAG = "JsoupCineplexxInTheatersAsynctask";

    public JsoupCineplexxInTheatersAsynctask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(String... strings) {
        JsoupCineplexxInTheaters jsoupUtils = new JsoupCineplexxInTheaters();
        try {
            Pair<List<Movie>, List<MovieSchedule>> pair = jsoupUtils.getPairMovieAndSchedule();
//            joinMovies(pair.first);
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
