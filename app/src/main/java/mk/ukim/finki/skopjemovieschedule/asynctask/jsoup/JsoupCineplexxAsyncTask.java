package mk.ukim.finki.skopjemovieschedule.asynctask.jsoup;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbApiClient;
import mk.ukim.finki.skopjemovieschedule.omdb.omdbMovie;

public abstract class JsoupCineplexxAsyncTask extends AsyncTask<String, Void, Pair<List<Movie>, List<MovieSchedule>>>{
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
//            Log.v(TAG, "loop: " + ms.toString());
            mMovieScheduleRepository.insert(ms);
//            Log.v(TAG, "loop: inserted");
        }
    }

//    protected void onPostExecute(List<Movie> movies) {
//        super.onPostExecute(movies);
//        if(movies == null){
//            Log.v(TAG, "NULL Movies in onPostExecute");
//            return;
//        }
//        for(Movie m : movies)
//            mMovieRepository.insert(m);
//    }

    List<Movie> joinMovies(List<Movie> allMovies) throws IOException {
        for(Movie m : allMovies){
            omdbMovie mo = omdbApiClient.getMovie(m.mMovieTitle);
            m.fillOmdbInfo(mo.Year, mo.Runtime,
                    mo.Rated, mo.Director, mo.Genre, mo.Writer, mo.Actors, mo.Plot, mo.Language, mo.Country, mo.Poster, "Cineplexx");
        }
        return allMovies;
    }
}
