package com.stefankrstikj.skopjemovieschedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.stefankrstikj.skopjemovieschedule.models.MapLocation;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

@Database(entities =  {Movie.class, MovieSchedule.class, MapLocation.class, TmdbMovie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract MovieScheduleDao movieScheduleDao();
    public abstract MapLocationDao mapLocationDao();
    public abstract TmdbMovieDao mTmdbMovieDiscoverDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
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
