package mk.ukim.finki.skopjemovieschedule.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.database.MapLocationRepository;
import mk.ukim.finki.skopjemovieschedule.models.MapLocation;
import mk.ukim.finki.skopjemovieschedule.utils.TheaterUtils;

public class TheatersAsyncTask extends AsyncTask<String, Void, List<MapLocation>> {
    private static String TAG = "TheatersAsyncTask";
    private MapLocationRepository mMapLocationRepository;

    public TheatersAsyncTask(MapLocationRepository mMapLocationRepository) {
        this.mMapLocationRepository = mMapLocationRepository;
    }

    @Override
    protected List<MapLocation> doInBackground(String... strings) {
        if(strings.length < 1 || strings[0] == null)
            return null;

        try{
            List<MapLocation> locations = TheaterUtils.fetchMapLocations(strings[0]);
            Log.v(TAG + "Map Locations: ", locations.toString());
            return locations;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<MapLocation> mapLocations) {
        super.onPostExecute(mapLocations);
        if(mapLocations != null){
            for(MapLocation m : mapLocations){
                mMapLocationRepository.insert(m);
            }
        }
    }
}