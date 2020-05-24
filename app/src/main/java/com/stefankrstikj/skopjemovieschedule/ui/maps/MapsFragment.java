package com.stefankrstikj.skopjemovieschedule.ui.maps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.MapLocationAdapter;
import com.stefankrstikj.skopjemovieschedule.models.MapLocation;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import static androidx.core.content.ContextCompat.*;

public class MapsFragment extends Fragment implements OnMapReadyCallback, OnMapLocationClickListener{
    private static String TAG = "MapsFragment";

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    public static String GOOGLE_MAPS_API_KEY;
    private Boolean mFoundPlaces = false;

    private MapsViewModel mMapsViewModel;
    private MapLocationAdapter adapter;
    private String mRequest;

    private GoogleMap mMap;
    private MapView mMapView;
    public static Location mLastLocation;
    private Marker mCurrLocationMarker;
    private FusedLocationProviderClient mFusedLocationClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maps, container, false);

        GOOGLE_MAPS_API_KEY = getResources().getString(R.string.google_maps_key);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mMapView = root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mMapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.v(TAG, "Initializing google map");
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(60000); // two minute interval
        mLocationRequest.setFastestInterval(60000);

        if (checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Location Permission already granted
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mMap.setMyLocationEnabled(true);
        } else {
            //Request Location Permission
            checkLocationPermission();
        }
    }


    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                //move map camera
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

                mRequest = URLList.REQUEST_URL_MAPLOCATIONS + latLng.latitude +","+latLng.longitude
                    + "&key=" + getResources().getString(R.string.google_maps_key)
                    + "&type=movie_theater&rankby=distance";

                if(!mFoundPlaces) {
                    initData();
                    mFoundPlaces = true;
                }
            }
        }
    };


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to mRequest the permission.
                new AlertDialog.Builder(getContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can mRequest the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    private void updateMap(List<MapLocation> locations) {
        if(locations == null || locations.size() == 0)
            return;
        for(MapLocation ml : locations){
            mMap.addMarker(ml.getMarker());
        }
    }

    private void initListView(){
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView_TheaterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MapLocationAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        MapsViewModelFactory factory = InjectorUtils.provideMapsViewModelFactory(getContext());
        mMapsViewModel =  ViewModelProviders.of(this, factory).get(MapsViewModel.class);
        mMapsViewModel. getAll(mRequest).observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
            updateMap(data);
        });
    }


    @Override
    public void onMapLocationClick(MapLocation mapLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapLocation.getMapsURL()));
        startActivity(intent);
    }
}