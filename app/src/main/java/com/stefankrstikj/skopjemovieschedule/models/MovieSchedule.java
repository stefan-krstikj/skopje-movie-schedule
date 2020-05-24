package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "movie_schedule",
        foreignKeys = {
            @ForeignKey(entity = Movie.class,
                        parentColumns = "movie_title",
                        childColumns = "movie_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
            @Index(name = "index_movie_id", value = {"movie_id"})
        },
        primaryKeys = {"movie_id","theater_name", "day", "time", "movie_hall"})
public class MovieSchedule {

//    @PrimaryKey(autoGenerate = true)
//    public int id;

    @NotNull
    @ColumnInfo(name = "movie_id")
    public String mMovieName;

    @NotNull
    @ColumnInfo(name = "theater_name")
    public String mTheaterName;

    @NotNull
    @ColumnInfo(name = "day")
    public String mDay;

    @ColumnInfo(name = "date")
    public String mDate;

    @NotNull
    @ColumnInfo(name = "time")
    public String mTime; // hh:mm military format

    @NotNull
    @ColumnInfo(name = "movie_hall")
    public String mMovieHall;

    @ColumnInfo(name = "3d_status")
    public String m3D;

    @ColumnInfo(name = "reservation_status")
    public int mReservationStatus;

    @ColumnInfo(name = "reservation_url")
    public String mReservationURL;

    public MovieSchedule(@NotNull String mMovieName, @NotNull String mTheaterName, @NotNull String mDay, String mDate,
                         @NotNull String mTime, @NotNull String mMovieHall, String m3D, String reservationURL) {
        this.mMovieName = mMovieName;
        this.mTheaterName = mTheaterName;
        this.mDay = mDay;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mMovieHall = mMovieHall;
        this.m3D = m3D;
        this.mReservationURL = reservationURL;
    }


    @NonNull
    @Override
    public String toString() {
        return mMovieName + " " + mDay + " " + mTime + " " + mMovieHall +  " " + m3D + "\n";
    }
}
