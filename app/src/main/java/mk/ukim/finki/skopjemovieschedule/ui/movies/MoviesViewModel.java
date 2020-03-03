package mk.ukim.finki.skopjemovieschedule.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.asynctask.jsoup.JsoupCineplexxComingSoonAsynctask;
import mk.ukim.finki.skopjemovieschedule.asynctask.jsoup.JsoupCineplexxInTheatersAsynctask;
import mk.ukim.finki.skopjemovieschedule.asynctask.jsoup.JsoupMileniumAsyncTask;
import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.ui.movies.tablayout.InTheatersTabFragment;

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