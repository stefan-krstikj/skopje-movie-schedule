package com.stefankrstikj.skopjemovieschedule.utils;

import android.content.Context;

import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.database.MapLocationRepository;
import com.stefankrstikj.skopjemovieschedule.database.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.maps.MapsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view.DetailMovieViewModelFactory;

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

    private static TmdbMovieRepository getTmdbMovieDiscoverRepository(Context context){
        return TmdbMovieRepository.getInstance(AppDatabase.getDatabase(context).mTmdbMovieDiscoverDao());
    }

    private static TmdbCastRepository getTmdbCastRepository(Context context){
        return TmdbCastRepository.getInstance(AppDatabase.getDatabase(context).mTmdbCastDao());
    }

    private static TmdbMovieRecommendationRepository getTmdbMovieRecommendationRepository(Context context){
        return TmdbMovieRecommendationRepository.getInstance(AppDatabase.getDatabase(context).mTmdbMovieRecommendationDao());
    }

    private static TmdbMovieGenreRepository getTmdbMovieGenreRepository(Context context){
        return TmdbMovieGenreRepository.getInstance(AppDatabase.getDatabase(context).mTmdbMovieGenreDao());
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

    public static DiscoverViewModelFactory provideDiscoverViewModelFactory(Context context){
        TmdbMovieRepository tmdbMovieRepository = getTmdbMovieDiscoverRepository(context);
        TmdbCastRepository tmdbCastRepository = getTmdbCastRepository(context);
        TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository = getTmdbMovieRecommendationRepository(context);
        TmdbMovieGenreRepository tmdbMovieGenreRepository = getTmdbMovieGenreRepository(context);
        return new DiscoverViewModelFactory(tmdbMovieRepository, tmdbCastRepository, tmdbMovieRecommendationRepository, tmdbMovieGenreRepository);
    }

    public static DetailedTmdbMovieViewModelFactory provideDetailedTmdbMovieViewModelFactory(Context context, TmdbMovieDetailed movieDetailed){
        TmdbCastRepository tmdbCastRepository = getTmdbCastRepository(context);
        return new DetailedTmdbMovieViewModelFactory(tmdbCastRepository, movieDetailed);
    }


}
