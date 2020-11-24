package com.stefankrstikj.skopjemovieschedule.ui.maps;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.asynctask.TheatersAsyncTask;
import com.stefankrstikj.skopjemovieschedule.database.maplocation.MapLocationRepository;
import com.stefankrstikj.skopjemovieschedule.models.MapLocation;

public class MapsViewModel extends ViewModel {
    private static String TAG = "MapsViewModel";
    private MapLocationRepository mMapLocationRepository;

    MapsViewModel(MapLocationRepository mapLocationRepository) {
        this.mMapLocationRepository = mapLocationRepository;
    }

    LiveData<List<MapLocation>> getAll(String request){
        Log.v(TAG + " getAll request: ", request);
        fetchMovieTheaters(request);
        return mMapLocationRepository.getAllMapLocations();
    }

    private void fetchMovieTheaters(String request){
        TheatersAsyncTask theatersAsyncTask = new TheatersAsyncTask(mMapLocationRepository);
        theatersAsyncTask.execute(request);
    }
}