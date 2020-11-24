package com.stefankrstikj.skopjemovieschedule.asynctask.cineplexx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.asynctask.JsoupAsyncTask;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.utils.jsoup.JsoupCineplexxComingSoon;

public class JsoupCineplexxComingSoonAsynctask extends JsoupAsyncTask {
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
