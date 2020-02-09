package mk.ukim.finki.skopjemovieschedule.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private static String TAG = "MovieRepository";

    private MovieDao mMovieDao;
    private static volatile MovieRepository instance;
    private LiveData<List<Movie>> mAllMovies;
    private LiveData<List<Movie>> mAllComingSoonMovies;

    MovieRepository(MovieDao movieDao){
        this.mMovieDao = movieDao;
//        AppDatabase db = AppDatabase.getDatabase(application);
//        mMovieDao = db.movieDao();
//        mAllMovies = mMovieDao.getAllMovies();
//        mAllComingSoonMovies = mMovieDao.getAllComingSoonMovies();
      //  Log.v(TAG, "comingMovies: " + mAllComingSoonMovies.getValue().toString());
    }

    public static MovieRepository getInstance(MovieDao movieDao){
        if(instance == null){
            synchronized(MovieRepository.class) {
                if (instance == null)
                    instance = new MovieRepository(movieDao);
            }
        }
        return instance;
    }

    public LiveData<List<Movie>> getAllMovies(){
        return mMovieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getAllComingSoonMovies(){
        return mMovieDao.getAllComingSoonMovies();
    }

    public void insert(final Movie movie){
            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mMovieDao.insert(movie);
                }
            });
    }
}
