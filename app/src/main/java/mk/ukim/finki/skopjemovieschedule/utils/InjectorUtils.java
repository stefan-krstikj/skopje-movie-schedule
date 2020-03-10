package mk.ukim.finki.skopjemovieschedule.utils;

import android.content.Context;

import mk.ukim.finki.skopjemovieschedule.database.AppDatabase;
import mk.ukim.finki.skopjemovieschedule.database.MapLocationRepository;
import mk.ukim.finki.skopjemovieschedule.database.MovieRepository;
import mk.ukim.finki.skopjemovieschedule.database.MovieScheduleRepository;
import mk.ukim.finki.skopjemovieschedule.ui.maps.MapsViewModelFactory;
import mk.ukim.finki.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view.DetailMovieViewModelFactory;

public class InjectorUtils {

    private static MovieRepository getMovieRepository(Context context){
        return MovieRepository.getInstance(AppDatabase.getDatabase(context).movieDao());
    }

    private static MovieScheduleRepository getMovieScheduleRepository(Context context){
        return MovieScheduleRepository.getInstance(AppDatabase.getDatabase(context).movieScheduleDao());
    }

    private static MapLocationRepository getMapLocationRepository(Context context){
        return MapLocationRepository.getInstance(AppDatabase.getDatabase(context).mapLocationDao());
    }

    public static DetailMovieViewModelFactory provideDetailMovieViewFactory(Context context){
        MovieRepository movieRepository = getMovieRepository(context);
        MovieScheduleRepository movieScheduleRepository = getMovieScheduleRepository(context);
        return new DetailMovieViewModelFactory(movieRepository, movieScheduleRepository);
    }

    public static MoviesViewModelFactory provideMoviesViewModelFactory(Context context){
        MovieRepository movieRepository = getMovieRepository(context);
        MovieScheduleRepository movieScheduleRepository = getMovieScheduleRepository(context);
        return new MoviesViewModelFactory(movieRepository, movieScheduleRepository);
    }

    public static MapsViewModelFactory provideMapsViewModelFactory(Context context){
        MapLocationRepository mapLocationRepository = getMapLocationRepository(context);
        return new MapsViewModelFactory(mapLocationRepository);
    }

}
