package mk.ukim.finki.skopjemovieschedule.ui.intheaters;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.asynctask.jsoupAsynctask;
import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;

public class InTheatersViewModel extends AndroidViewModel {

    public MovieRepository mMovieRepository;

    public InTheatersViewModel(Application application) {
        super(application);
        mMovieRepository = new MovieRepository(application);
    }


    public void insert (Movie movie){
        mMovieRepository.insert(movie);
    }

    public LiveData<List<Movie>> getAll(){
        fetchDataMovieList();
        return mMovieRepository.getAllMovies();
    }

    public void fetchDataMovieList(){
        jsoupAsynctask jsoupAsynctask = new jsoupAsynctask(mMovieRepository);
        jsoupAsynctask.execute();
    }
}