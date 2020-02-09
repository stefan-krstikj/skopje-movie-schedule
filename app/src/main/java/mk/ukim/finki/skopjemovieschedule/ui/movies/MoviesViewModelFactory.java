package mk.ukim.finki.skopjemovieschedule.ui.movies;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;

public class MoviesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    public MoviesViewModelFactory(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        this.mMovieRepository = mMovieRepository;
        this.mMovieScheduleRepository = mMovieScheduleRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MoviesViewModel moviesViewModel= new MoviesViewModel(mMovieRepository, mMovieScheduleRepository);
        return (T) moviesViewModel;
    }
}
