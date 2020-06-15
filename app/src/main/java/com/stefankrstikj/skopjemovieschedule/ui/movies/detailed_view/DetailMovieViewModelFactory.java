package com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;

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
