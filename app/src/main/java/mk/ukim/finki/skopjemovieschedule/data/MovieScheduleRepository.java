package mk.ukim.finki.skopjemovieschedule.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieScheduleRepository {
    private static String TAG = "MovieScheduleRepository";

    private MovieScheduleDao mMovieScheduleDao;
    private LiveData<List<MovieSchedule>> mAllMovieSchedules;
    private static volatile MovieScheduleRepository instance;

    public static MovieScheduleRepository getInstance(MovieScheduleDao movieScheduleDao){
        if(instance == null){
            synchronized (MovieScheduleDao.class){
                if(instance == null){
                    instance = new MovieScheduleRepository(movieScheduleDao);
                }
            }
        }
        return instance;
    }

    private MovieScheduleRepository(MovieScheduleDao movieScheduleDao){
        this.mMovieScheduleDao = movieScheduleDao;
    }

    public void insert(MovieSchedule movieSchedule){
        Log.v(TAG, "insert(MovieSchedule): " + movieSchedule.toString());
        AppDatabase.databaseWriteExecutor.execute(() -> mMovieScheduleDao.insert(movieSchedule));
    }

    public void deleteAllMovieSchedules(){
        mMovieScheduleDao.deleteAllMovieSchedules();
    }

    public LiveData<List<MovieSchedule>> getAllMovieSchedules(){
        Log.v(TAG, "getAllMovieSchedules(): " + mMovieScheduleDao.getAllMovieSchedules());
        return mMovieScheduleDao.getAllMovieSchedules();
    }

    public LiveData<List<MovieSchedule>> getAllSchedulesFromMovie(String movieTitle){
        return mMovieScheduleDao.getAllSchedulesFromMovie(movieTitle);
    }

    public LiveData<List<MovieSchedule>> getAllScheduesFromMovieByDay(String movieTitle, String day){
        return mMovieScheduleDao.getAllSchedulesFromMovieByDay(movieTitle, day);
    }
}
