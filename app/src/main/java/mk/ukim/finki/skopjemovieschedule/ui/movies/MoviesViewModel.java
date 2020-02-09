package mk.ukim.finki.skopjemovieschedule.ui.movies;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.asynctask.jsoup.JsoupCineplexxComingSoonAsynctask;
import mk.ukim.finki.skopjemovieschedule.asynctask.jsoup.JsoupCineplexxInTheatersAsynctask;
import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;

public class MoviesViewModel extends ViewModel {
    private static String TAG = "MoviesViewModel";
    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    MoviesViewModel(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository){
        this.mMovieScheduleRepository = mMovieScheduleRepository;
        this.mMovieRepository = mMovieRepository;
    }


    public void insert (Movie movie){
        mMovieRepository.insert(movie);
    }

    public LiveData<List<Movie>> getAll(){
        fetchDataMovieList();
        return mMovieRepository.getAllMovies();
    }

    public LiveData<List<Movie>> getAllComingSoon(){
        fetchDataMovieListComingSoon();
        return mMovieRepository.getAllComingSoonMovies();
    }

    private void fetchDataMovieList(){
        JsoupCineplexxInTheatersAsynctask jsoupCineplexxInTheatersAsynctask =
                new JsoupCineplexxInTheatersAsynctask(mMovieRepository, mMovieScheduleRepository);
        jsoupCineplexxInTheatersAsynctask.execute();
    }

    private void fetchDataMovieListComingSoon(){
        JsoupCineplexxComingSoonAsynctask jsoupCineplexxComingSoonAsynctask =
                new JsoupCineplexxComingSoonAsynctask(mMovieRepository, mMovieScheduleRepository);
        jsoupCineplexxComingSoonAsynctask.execute();
    }
}