package com.stefankrstikj.skopjemovieschedule.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;

import java.util.List;


public abstract class JsoupAsyncTask extends AsyncTask<Void, Void, Pair<List<Movie>, List<MovieSchedule>>>{
    private static String TAG = "JsoupCineplexxAsyncTask";
    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    protected JsoupAsyncTask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        this.mMovieRepository = mMovieRepository;
        this.mMovieScheduleRepository = mMovieScheduleRepository;
    }

    // todo fix caching
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        mMovieScheduleRepository.deleteAllMovieSchedules();
//    }

    @Override
    protected void onPostExecute(Pair<List<Movie>, List<MovieSchedule>> listListPair) {
        super.onPostExecute(listListPair);
        if(listListPair.first == null || listListPair.second == null){
            Log.v(TAG, "NULL Movies in onPostExecute");
            return;
        }
        for(Movie m : listListPair.first){
            mMovieRepository.insert(m);
        }
        Log.v(TAG, "onPostExecute done inserting movies in repo");
        for(MovieSchedule ms : listListPair.second){
            mMovieScheduleRepository.insert(ms);
        }
    }
}
