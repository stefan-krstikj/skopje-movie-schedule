package mk.ukim.finki.skopjemovieschedule.asynctask.jsoup;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.database.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.database.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.utils.jsoup.JsoupCineplexxComingSoon;

public class JsoupCineplexxComingSoonAsynctask extends JsoupCineplexxAsyncTask {
    private static String TAG = "JsoupCineplexxComingSoonAsynctask";

    public JsoupCineplexxComingSoonAsynctask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(Void... voids) {
        JsoupCineplexxComingSoon jsoupUtils = new JsoupCineplexxComingSoon();
        try {
            Pair<List<Movie>, List<MovieSchedule>> pair = jsoupUtils.getPairMovieAndSchedule();
            Log.v(TAG, "Received pair: " + pair.first.toString());
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "RETURNING NULL PAIR");
        return null;
    }
}
