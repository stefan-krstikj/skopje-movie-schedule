package com.stefankrstikj.skopjemovieschedule.asynctask.cineplexx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.asynctask.JsoupAsyncTask;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.database.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.database.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.utils.jsoup.JsoupCineplexxInTheaters;


public class JsoupCineplexxInTheatersAsynctask extends JsoupAsyncTask {
    private static final String TAG = "JsoupCineplexxInTheatersAsynctask";

    public JsoupCineplexxInTheatersAsynctask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(Void... voids) {
        JsoupCineplexxInTheaters jsoupUtils = new JsoupCineplexxInTheaters();
        try {
            Pair<List<Movie>, List<MovieSchedule>> pair = jsoupUtils.getPairMovieAndSchedule();
            Log.v(TAG, "Received pair: " + pair.first.toString());
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
