package com.stefankrstikj.skopjemovieschedule.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "map_location")
public class MapLocation {

    private static String TAG = "MapLocation";

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "mId")
    private String mId;

    @ColumnInfo(name = "mName")
    private String mName;

    @ColumnInfo(name = "places_id")
    private String mPlacesId;

    @ColumnInfo(name = "mRating")
    private String mRating;

    @ColumnInfo(name = "mLatitude")
    private String mLatitude;

    @ColumnInfo(name = "mLongitude")
    private String mLongitude;

    @ColumnInfo(name = "photo_url")
    private String mPhotoURL;

    @ColumnInfo(name = "user_ratings_total")
    private String mUserRatingsTotal;

    @ColumnInfo(name = "open_now")
    private int mOpenNow;

    @ColumnInfo(name = "mVicinity")
    private String mVicinity;

    @ColumnInfo(name = "maps_url")
    private String mMapsURL;


    public MapLocation(@NotNull String id, String name,
                       String placesId, String rating, String latitude, String longitude,
                       String photoURL, String userRatingsTotal, int openNow, String vicinity) {
        this.mId = id;
        this.mName = name;
        this.mPlacesId = placesId;
        this.mRating = rating;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPhotoURL = photoURL;
        this.mUserRatingsTotal = userRatingsTotal;
        this.mOpenNow = openNow;
        this.mVicinity = vicinity;
        this.mMapsURL = createMapsURL();
    }

    private String createMapsURL(){
        String mapsURL = "https://www.google.com/maps/search/?api=1&query=" + this.mName.replace(" ", "") +"&query_place_id=" + this.mPlacesId;
        Log.v(TAG, "mMapsURL " + mName +": " + mapsURL + "\n");
        return mapsURL;
    }

    @NotNull
    public String getId() {
        return mId;
    }

    public void setId(@NotNull String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPlacesId() {
        return mPlacesId;
    }

    public void setPlacesId(String placesId) {
        this.mPlacesId = placesId;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        this.mRating = rating;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        this.mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        this.mLongitude = longitude;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.mPhotoURL = photoURL;
    }

    public String getUserRatingsTotal() {
        return mUserRatingsTotal;
    }

    public void setUserRatingsTotal(String userRatingsTotal) {
        this.mUserRatingsTotal = userRatingsTotal;
    }

    public int getOpenNow() {
        return mOpenNow;
    }

    public void setOpenNow(int openNow) {
        this.mOpenNow = openNow;
    }

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        MapLocation.TAG = TAG;
    }

    public String getVicinity() {
        return mVicinity;
    }

    public void setVicinity(String vicinity) {
        this.mVicinity = vicinity;
    }

    public String getMapsURL() {
        return mMapsURL;
    }

    public void setMapsURL(String mapsURL) {
        this.mMapsURL = mapsURL;
    }

    private LatLng getmLatLng() {
        return new LatLng(Double.parseDouble(mLatitude), Double.parseDouble(mLongitude));
    }

    public MarkerOptions getMarker(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(this.getmLatLng());markerOptions.title(this.mName);
        return  markerOptions;
    }

    @NonNull
    @Override
    public String toString() {
        return mName + ", " + mPlacesId + ", " + mRating + "\n";
    }
}
