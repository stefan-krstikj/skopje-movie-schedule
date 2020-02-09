package mk.ukim.finki.skopjemovieschedule.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Query("SELECT * FROM movie WHERE movie.status = 1")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movie WHERE movie.status = 0")
    LiveData<List<Movie>> getAllComingSoonMovies();
}
