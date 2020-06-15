package com.stefankrstikj.skopjemovieschedule.asynctask.milenium;

import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.asynctask.JsoupAsyncTask;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.utils.jsoup.JsoupMilenium;

public class JsoupMileniumAsyncTask extends JsoupAsyncTask {
    private static String TAG = "JsoupMileniumAsyncTask";


    public JsoupMileniumAsyncTask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        super(mMovieRepository, mMovieScheduleRepository);
    }

    @Override
    protected Pair<List<Movie>, List<MovieSchedule>> doInBackground(Void... voids) {
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
