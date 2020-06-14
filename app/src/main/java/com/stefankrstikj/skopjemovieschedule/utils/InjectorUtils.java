package com.stefankrstikj.skopjemovieschedule.utils;

import android.content.Context;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.database.MapLocationRepository;
import com.stefankrstikj.skopjemovieschedule.database.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.maps.MapsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view.DetailMovieViewModelFactory;

import java.util.Observer;

public class InjectorUtils {

    // todo: other viewmodels dont need all the repository access they have, refactor them later
    private static TmdbApiClient getTmdbApiClient(Context context){
        return TmdbApiClient.getInstance(getTmdbMovieDiscoverRepository(context), getTmdbCastRepository(context), getTmdbMovieRecommendationRepository(context),
                getTmdbMovieGenreRepository(context), getTmdbMovieReviewRepository(context), getTmdbMovieVideoRepository(context));
    }

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

    private static TmdbMovieReviewRepository getTmdbMovieReviewRepository(Context context){
        return TmdbMovieReviewRepository.getInstance(AppDatabase.getDatabase(context).mTmdbMovieReviewDao());
    }

    private static TmdbMovieVideoRepository getTmdbMovieVideoRepository(Context context){
        return TmdbMovieVideoRepository.getInstance(AppDatabase.getDatabase(context).mTmdbMovieVideoDao());
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
        return new DiscoverViewModelFactory(getTmdbMovieDiscoverRepository(context), getTmdbCastRepository(context),
                getTmdbMovieRecommendationRepository(context), getTmdbMovieGenreRepository(context), getTmdbMovieReviewRepository(context),
                getTmdbMovieVideoRepository(context), getTmdbApiClient(context));
    }

    public static DetailedTmdbMovieViewModelFactory provideDetailedTmdbMovieViewModelFactory(Context context, TmdbMovieDetailed movieDetailed){
        return new DetailedTmdbMovieViewModelFactory(getTmdbMovieDiscoverRepository(context), getTmdbCastRepository(context), getTmdbMovieRecommendationRepository(context),
                getTmdbMovieGenreRepository(context), getTmdbMovieReviewRepository(context), getTmdbMovieVideoRepository(context), movieDetailed, getTmdbApiClient(context));
    }

    public static DiscoverSearchResultsViewModelFactory provideDiscoverSearchResultsViewModelFactory(Context context){
        return new DiscoverSearchResultsViewModelFactory(getTmdbMovieDiscoverRepository(context), getTmdbApiClient(context));
    }

}
