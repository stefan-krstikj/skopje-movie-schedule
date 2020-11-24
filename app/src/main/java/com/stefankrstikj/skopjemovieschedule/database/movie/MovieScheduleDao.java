package com.stefankrstikj.skopjemovieschedule.database.movie;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.models.MovieSchedule;

@Dao
public interface MovieScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieSchedule movieSchedule);

    @Query("SELECT * FROM movie_schedule")
    LiveData<List<MovieSchedule>> getAllMovieSchedules();

    @Query("SELECT * FROM movie_schedule WHERE movie_id =:movieTitle")
    LiveData<List<MovieSchedule>> getAllSchedulesFromMovie(String movieTitle);

    @Query("SELECT * FROM movie_schedule WHERE movie_id =:movieTitle AND day =:day ")
    LiveData<List<MovieSchedule>> getAllSchedulesFromMovieByDay(String movieTitle, String day);

    @Query("DELETE FROM movie_schedule")
    void deleteAllMovieSchedules();
}
