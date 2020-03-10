package mk.ukim.finki.skopjemovieschedule.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.MapLocation;

@Dao
public interface MapLocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (MapLocation mapLocation);

    @Query("DELETE FROM map_location")
    void deleteAll();

    @Query("SELECT * FROM map_location")
    LiveData<List<MapLocation>> getAllMapLocations();
}
