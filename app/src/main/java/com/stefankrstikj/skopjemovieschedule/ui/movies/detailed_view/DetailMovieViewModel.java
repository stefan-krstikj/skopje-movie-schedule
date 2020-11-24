package com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;

public class DetailMovieViewModel extends ViewModel {

    private static String TAG = "DetailMovieViewModel";

    private MovieRepository mMovieRepository;
    private MovieScheduleRepository mMovieScheduleRepository;

    DetailMovieViewModel(MovieRepository mMovieRepository, MovieScheduleRepository mMovieScheduleRepository) {
        this.mMovieRepository = mMovieRepository;
        this.mMovieScheduleRepository = mMovieScheduleRepository;
    }

    LiveData<List<MovieSchedule>> getMovieScheduleForMovie(String movieTitle){
        return mMovieScheduleRepository.getAllSchedulesFromMovie(movieTitle);
    }

    LiveData<List<MovieSchedule>> getAllMovieSchedules(){
        return mMovieScheduleRepository.getAllMovieSchedules();
    }
}
