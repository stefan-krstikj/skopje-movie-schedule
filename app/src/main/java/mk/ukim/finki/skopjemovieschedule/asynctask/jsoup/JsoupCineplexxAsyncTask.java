package mk.ukim.finki.skopjemovieschedule.asynctask.jsoup;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.database.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.database.MovieScheduleRepository;


// todo: change to Void, Void, Pair
public abstract class JsoupCineplexxAsyncTask extends AsyncTask<Void, Void, Pair<List<Movie>, List<MovieSchedule>>>{
    private static String TAG = "JsoupCineplexxAsyncTask";
    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    JsoupCineplexxAsyncTask(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
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
