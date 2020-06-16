package com.stefankrstikj.skopjemovieschedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.stefankrstikj.skopjemovieschedule.database.maplocation.MapLocationDao;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieDao;
import com.stefankrstikj.skopjemovieschedule.database.movie.MovieScheduleDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.TmdbMovieDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.cast.TmdbCastDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.genre.TmdbMovieGenreDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.recommendation.TmdbMovieRecommendationDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.review.TmdbMovieReviewDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.video.TmdbMovieVideoDao;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.people.TmdbPeopleDao;
import com.stefankrstikj.skopjemovieschedule.models.MapLocation;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieGenre;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;
import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;
import com.stefankrstikj.skopjemovieschedule.utils.type_converters.MovieGenreTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
		Movie.class, MovieSchedule.class, MapLocation.class, TmdbMovieDetailed.class, TmdbMovieCast.class,
		TmdbMovieRecommendation.class, TmdbMovieGenre.class, TmdbMovieReview.class, TmdbMovieVideo.class,
		TmdbPerson.class},
		version = 1, exportSchema = false)
@TypeConverters({MovieGenreTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
	public abstract MovieDao movieDao();

	public abstract MovieScheduleDao movieScheduleDao();

	public abstract MapLocationDao mapLocationDao();

	public abstract TmdbMovieDao mTmdbMovieDiscoverDao();

	public abstract TmdbCastDao mTmdbCastDao();

	public abstract TmdbMovieRecommendationDao mTmdbMovieRecommendationDao();

	public abstract TmdbMovieGenreDao mTmdbMovieGenreDao();

	public abstract TmdbMovieReviewDao mTmdbMovieReviewDao();

	public abstract TmdbMovieVideoDao mTmdbMovieVideoDao();

	public abstract TmdbPeopleDao mTmdbPeopleDao();

	private static volatile AppDatabase INSTANCE;
	private static final int NUMBER_OF_THREADS = 4;
	public static final ExecutorService databaseWriteExecutor =
			Executors.newFixedThreadPool(NUMBER_OF_THREADS);

	public static AppDatabase getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (AppDatabase.class) {
				if (INSTANCE == null) {
//                     add fallbacktodestruct if there are problems with build version
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "movie_database")
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
