package com.stefankrstikj.skopjemovieschedule.database.maplocation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.MapLocation;

import java.util.List;

@Dao
public interface MapLocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (MapLocation mapLocation);

    @Query("DELETE FROM map_location")
    void deleteAll();

    @Query("SELECT * FROM map_location")
    LiveData<List<MapLocation>> getAllMapLocations();
}
