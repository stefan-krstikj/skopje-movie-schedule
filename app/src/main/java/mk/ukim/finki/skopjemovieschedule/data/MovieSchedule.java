package mk.ukim.finki.skopjemovieschedule.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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
        primaryKeys = {"movie_id", "day", "time", "movie_hall"})
public class MovieSchedule {

//    @PrimaryKey(autoGenerate = true)
//    public int id;

    @NotNull
    @ColumnInfo(name = "movie_id")
    public String mMovieName;

    @NotNull
    @ColumnInfo(name = "day")
    public String mDay;

    @NotNull
    @ColumnInfo(name = "time")
    public String mTime; // hh:mm military format

    @NotNull
    @ColumnInfo(name = "movie_hall")
    public String mMovieHall;

    @ColumnInfo(name = "3d_status")
    public String m3D;

    public MovieSchedule(@NotNull String mMovieName) {
        this.mMovieName = mMovieName;
    }

    public void insertScreening(String day, String time, String movieHall, String m3D){
        this.mDay = day;
        this.mTime = time;
        this.mMovieHall = movieHall;
        this.m3D = m3D;
    }

    @NonNull
    @Override
    public String toString() {
        return mMovieName + " " + mDay + " " + mTime + " " + mMovieHall +  " " + m3D + "\n";
    }
}
