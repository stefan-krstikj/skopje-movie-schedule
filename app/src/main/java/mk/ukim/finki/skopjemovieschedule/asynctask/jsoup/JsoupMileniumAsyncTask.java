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
import mk.ukim.finki.skopjemovieschedule.utils.jsoup.JsoupCineplexxAbstract;
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
//            joinMovies(pair.first);
            Log.v(TAG, "Received pair: " + pair.first.toString());
            return pair;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    @Override
//    protected void onPostExecute(Pair<List<Movie>, List<MovieSchedule>> listListPair) {
//        if(listListPair == null){
//            Log.v(TAG, "Pair is null");
//            return;
//        }
//
//        for(Movie m : listListPair.first){
//            Log.v(TAG + " onPostExecute", "inserting: " + m.mMovieTitle);
//            mMovieRepository.insert(m);
//        }
//    }

//    List<Movie> joinMovies(List<Movie> allMovies) throws IOException {
//        for(Movie m : allMovies){
//            omdbMovie mo = omdbApiClient.getMovie(m.mMovieTitle, m.mYear);
//            if (mo.Title == null){
//                Log.v(TAG, "Received null for: " + m.mMovieTitle + ", cutting string...");
//                String[] titleSplit = m.mMovieTitle.split(" " );
//                String shortTitle  = "";
//                if(titleSplit.length >= 3)
//                    shortTitle = titleSplit[0] + " " + titleSplit[1] + " " + titleSplit[2];
//                else if(titleSplit.length >= 2)
//                    shortTitle = titleSplit[0] + " " + titleSplit[1];
//
//                mo = omdbApiClient.getMovie(shortTitle, m.mYear);
//            }
//            m.fillOmdbInfo(mo.Title, mo.Year, mo.Runtime,
//                    mo.Rated, mo.Director, mo.Genre, mo.Writer, mo.Actors, mo.Plot, mo.Language, mo.Country, mo.Poster, "Cineplexx");
//        }
//        return allMovies;
//    }
}
