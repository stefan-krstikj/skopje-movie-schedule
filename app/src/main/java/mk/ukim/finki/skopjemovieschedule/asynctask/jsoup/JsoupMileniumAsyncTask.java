package mk.ukim.finki.skopjemovieschedule.asynctask.jsoup;

import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.utils.jsoup.JsoupMilenium;

public class JsoupMileniumAsyncTask extends JsoupCineplexxAsyncTask {
    private static String TAG = "JsoupMileniumAsyncTask";


    public JsoupMileniumAsyncTask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(String... strings) {
        JsoupMilenium jsoupMilenium = new JsoupMilenium();
        try {
            Pair<List<Movie>, List<MovieSchedule>> pair = jsoupMilenium.getPairMovieAndSchedule();
            Log.v(TAG, "Received pair: " + pair.first.toString());
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
