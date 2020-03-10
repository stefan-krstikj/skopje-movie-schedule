package mk.ukim.finki.skopjemovieschedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mk.ukim.finki.skopjemovieschedule.models.MapLocation;
import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;

@Database(entities =  {Movie.class, MovieSchedule.class, MapLocation.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract MovieScheduleDao movieScheduleDao();
    public abstract MapLocationDao mapLocationDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // to/do: add fallbacktodestruct if there are problems with build version
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
