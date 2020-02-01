package mk.ukim.finki.skopjemovieschedule.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.jsoup.jsoupUtils;



public class jsoupAsynctask extends AsyncTask<String, Void, List<Movie>> {
    private static final String TAG = "jsoupAsynctask";


    private MovieRepository mMovieRepository;



    public jsoupAsynctask(MovieRepository mMovieRepository) {
        this.mMovieRepository = mMovieRepository;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        jsoupUtils jsoupUtils = new jsoupUtils("https://www.cineplexx.mk/filmovi/");
        try {
            List<Movie> allMovies = jsoupUtils.getAllMovies();
            Log.v(TAG, "doInBackground() Received movies: " + allMovies.toString());
            return allMovies;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if(movies == null){
            Log.v(TAG, "NULL Movies in onPostExecute");
            return;
        }
        for(Movie m : movies)
            mMovieRepository.insert(m);
        Log.v(TAG, "onPostExecute done inserting movies in repo");
        Log.v(TAG, "onPostExecute Getting all movies...");
        LiveData<List<Movie>> moviesLive = mMovieRepository.getAllMovies();
        List<Movie> m = moviesLive.getValue();
        Log.v(TAG, m.toString());

    }
}
