package mk.ukim.finki.skopjemovieschedule.models;

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
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "places_id")
    private String placesId;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "photo_url")
    private String photoURL;

    @ColumnInfo(name = "user_ratings_total")
    private String userRatingsTotal;

    @ColumnInfo(name = "open_now")
    private int openNow;

    @ColumnInfo(name = "vicinity")
    private String vicinity;

    @ColumnInfo(name = "maps_url")
    private String mapsURL;


    public MapLocation(@NotNull String id, String name,
                       String placesId, String rating, String latitude, String longitude,
                       String photoURL, String userRatingsTotal, int openNow, String vicinity) {
        this.id = id;
        this.name = name;
        this.placesId = placesId;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoURL = photoURL;
        this.userRatingsTotal = userRatingsTotal;
        this.openNow = openNow;
        this.vicinity = vicinity;
        this.mapsURL = createMapsURL();
    }

    private String createMapsURL(){
        String mapsURL = "https://www.google.com/maps/search/?api=1&query=" + this.name.replace(" ", "") +"&query_place_id=" + this.placesId;
        Log.v(TAG, "mapsURL " +name +": " + mapsURL + "\n");
        return mapsURL;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlacesId() {
        return placesId;
    }

    public void setPlacesId(String placesId) {
        this.placesId = placesId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(String userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public int getOpenNow() {
        return openNow;
    }

    public void setOpenNow(int openNow) {
        this.openNow = openNow;
    }

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        MapLocation.TAG = TAG;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getMapsURL() {
        return mapsURL;
    }

    public void setMapsURL(String mapsURL) {
        this.mapsURL = mapsURL;
    }

    private LatLng getmLatLng() {
        return new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public MarkerOptions getMarker(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(this.getmLatLng());markerOptions.title(this.name);
        return  markerOptions;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ", " + placesId + ", " + rating + "\n";
    }
}
