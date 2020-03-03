package mk.ukim.finki.skopjemovieschedule.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;

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
        AppDatabase.databaseWriteExecutor.execute(() -> mMovieScheduleDao.insert(movieSchedule));
    }

    public void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieScheduleDao.deleteAllMovieSchedules();
            }
        });
    }

    public LiveData<List<MovieSchedule>> getAllMovieSchedules(){
        return mMovieScheduleDao.getAllMovieSchedules();
    }

    public LiveData<List<MovieSchedule>> getAllSchedulesFromMovie(String movieTitle){
        return mMovieScheduleDao.getAllSchedulesFromMovie(movieTitle);
    }

    public LiveData<List<MovieSchedule>> getAllScheduesFromMovieByDay(String movieTitle, String day){
        return mMovieScheduleDao.getAllSchedulesFromMovieByDay(movieTitle, day);
    }
}
