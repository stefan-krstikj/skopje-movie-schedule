package mk.ukim.finki.skopjemovieschedule.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.models.MapLocation;

public class MapLocationRepository {

    private MapLocationDao mMapLocationDao;
    private static volatile MapLocationRepository instance;

    public static MapLocationRepository getInstance(MapLocationDao mapLocationDao){
        if(instance == null){
            synchronized (MapLocationDao.class){
                if(instance == null){
                    instance = new MapLocationRepository(mapLocationDao);
                }
            }
        }
        return instance;
    }

    private  MapLocationRepository(MapLocationDao mapLocationDao){
        this.mMapLocationDao = mapLocationDao;
    }

    public void insert(MapLocation mapLocation){
        AppDatabase.databaseWriteExecutor.execute(() -> mMapLocationDao.insert(mapLocation));
    }

    public LiveData<List<MapLocation>> getAllMapLocations(){
        return mMapLocationDao.getAllMapLocations();
    }
}
