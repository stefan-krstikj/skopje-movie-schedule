package com.stefankrstikj.skopjemovieschedule.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.asynctask.cineplexx.JsoupCineplexxComingSoonAsynctask;
import com.stefankrstikj.skopjemovieschedule.asynctask.cineplexx.JsoupCineplexxInTheatersAsynctask;
import com.stefankrstikj.skopjemovieschedule.asynctask.milenium.JsoupMileniumAsyncTask;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;

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

    public void refreshData(){
        mMovieScheduleRepository.deleteAll();
        mMovieRepository.deleteAll();
        fetchDataMovieList();
        fetchDataMovieListComingSoon();
    }

    private void fetchDataMovieList(){
        JsoupCineplexxInTheatersAsynctask jsoupCineplexxInTheatersAsynctask =
                new JsoupCineplexxInTheatersAsynctask(mMovieRepository, mMovieScheduleRepository);
        JsoupMileniumAsyncTask jsoupMileniumAsyncTask =
                new JsoupMileniumAsyncTask(mMovieRepository, mMovieScheduleRepository);

        jsoupCineplexxInTheatersAsynctask.execute();
        jsoupMileniumAsyncTask.execute();


    }

    private void fetchDataMovieListComingSoon(){
        JsoupCineplexxComingSoonAsynctask jsoupCineplexxComingSoonAsynctask =
                new JsoupCineplexxComingSoonAsynctask(mMovieRepository, mMovieScheduleRepository);
        jsoupCineplexxComingSoonAsynctask.execute();
    }
}