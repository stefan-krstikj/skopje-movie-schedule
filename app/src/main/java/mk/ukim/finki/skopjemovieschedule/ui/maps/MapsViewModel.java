package mk.ukim.finki.skopjemovieschedule.ui.maps;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.asynctask.TheatersAsyncTask;
import mk.ukim.finki.skopjemovieschedule.data.MapLocationRepository;
import mk.ukim.finki.skopjemovieschedule.models.MapLocation;

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