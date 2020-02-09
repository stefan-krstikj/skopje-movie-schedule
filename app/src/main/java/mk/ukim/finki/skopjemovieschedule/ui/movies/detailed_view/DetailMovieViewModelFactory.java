package mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;

public class DetailMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    public DetailMovieViewModelFactory(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        this.mMovieRepository = mMovieRepository;
        this.mMovieScheduleRepository = mMovieScheduleRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        DetailMovieViewModel detailMovieViewModel = new DetailMovieViewModel(mMovieRepository, mMovieScheduleRepository);
        return (T) detailMovieViewModel;
    }
}
