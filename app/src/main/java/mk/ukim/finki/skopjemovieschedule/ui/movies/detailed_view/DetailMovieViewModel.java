package mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.data.MovieScheduleRepository;

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
