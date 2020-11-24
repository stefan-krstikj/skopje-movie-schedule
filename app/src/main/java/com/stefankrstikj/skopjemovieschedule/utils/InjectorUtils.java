package com.stefankrstikj.skopjemovieschedule.utils;

import android.content.Context;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.database.maplocation.MapLocationRepository;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.cast.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.genre.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.recommendation.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.review.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.video.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.people.TmdbPeopleRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.DetailedTmdbMovieViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.person.DetailedTmdbPersonViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.maps.MapsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view.DetailMovieViewModelFactory;

public class InjectorUtils {

    private static TmdbApiClient getTmdbApiClient(Context context){
        return TmdbApiClient.getInstance(getTmdbMovieDiscoverRepository(context), getTmdbCastRepository(context), getTmdbMovieRecommendationRepository(context),
                getTmdbMovieGenreRepository(context), getTmdbMovieReviewRepository(context), getTmdbMovieVideoRepository(context), getTmdbPeopleRepository(context));
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

    private static TmdbPeopleRepository getTmdbPeopleRepository(Context context){
        return TmdbPeopleRepository.getInstance(AppDatabase.getDatabase(context).mTmdbPeopleDao());
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
        return new DiscoverViewModelFactory(getTmdbMovieDiscoverRepository(context), getTmdbApiClient(context));
    }

    public static DetailedTmdbMovieViewModelFactory provideDetailedTmdbMovieViewModelFactory(Context context, TmdbMovieDetailed movieDetailed){
        return new DetailedTmdbMovieViewModelFactory(getTmdbCastRepository(context), getTmdbMovieRecommendationRepository(context),
                getTmdbMovieReviewRepository(context), getTmdbMovieVideoRepository(context), movieDetailed, getTmdbApiClient(context));
    }

    public static DiscoverSearchResultsViewModelFactory provideDiscoverSearchResultsViewModelFactory(Context context){
        return new DiscoverSearchResultsViewModelFactory(getTmdbMovieDiscoverRepository(context), getTmdbApiClient(context));
    }

    public static DetailedTmdbPersonViewModelFactory provideDetailedTmdbPersonViewModelFactory(Context context){
        return new DetailedTmdbPersonViewModelFactory(getTmdbPeopleRepository(context), getTmdbApiClient(context));
    }

}
