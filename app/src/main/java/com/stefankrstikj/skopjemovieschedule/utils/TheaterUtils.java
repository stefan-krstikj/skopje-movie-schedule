package com.stefankrstikj.skopjemovieschedule.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.stefankrstikj.skopjemovieschedule.models.MapLocation;

public class TheaterUtils {
    private static String TAG = "TheaterUtils";
    private static Context mContext;

    public static ArrayList<MapLocation> fetchMapLocations(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, ("Error closing input stream"));
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<MapLocation> mapLocations = extractMapLocationsFromJson(jsonResponse);

        // Return the {@link Event}
        return mapLocations;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, ("Error with creating URL "));
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, ("Error response code: " + urlConnection.getResponseCode()));
            }
        } catch (IOException e) {
            Log.e(TAG,("Problem retrieving the map location JSON results."));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link MapLocation} object by parsing out information
     * about the first earthquake from the input mapLocationJSON string.
     */
    private static ArrayList<MapLocation> extractMapLocationsFromJson(String mapLocationJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(mapLocationJSON)) {
            return null;
        }
        ArrayList<MapLocation> mapLocations = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(mapLocationJSON);
            String status = baseJsonResponse.getString("status");
            if(!status.equals("OK")) {
                Log.v(TAG, "Error status: " + status.toString());
            }


            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");
            Log.v(TAG, "resultsArray: " + resultsArray.length());
//            Log.v(TAG, resultsArray.toString());
            // If there are results in the features array

            for (int i = 0; i < resultsArray.length(); i++) {
                // Extract out the first feature (which is an mapLocation)
                JSONObject mapLocationObject = resultsArray.getJSONObject(i);
                JSONObject geometry = mapLocationObject.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");

                String lat = location.getString("lat");
                String lon = location.getString("lng");

                String id = mapLocationObject.getString("id");
                String name = mapLocationObject.getString("name");
                if(name.contains("Sex") || !mapLocationObject.has("user_ratings_total"))
                    continue;

                String place_id = mapLocationObject.getString("place_id");

                String rating = null;
                int open = -1;
                String photoURL = null;
                String userRatingsTotal = null;

                if(mapLocationObject.has("rating"))
                    rating = mapLocationObject.getString("rating");

                if(mapLocationObject.has("opening_hours")){
                    if(mapLocationObject.getJSONObject("opening_hours").getString("open_now").equals("true")){
                        Log.v(TAG, "open with value: " + mapLocationObject.getJSONObject("opening_hours").getString("open_now"));
                        open = 1;
                    }
                    else
                        open = 0;
                }

                userRatingsTotal = mapLocationObject.getString("user_ratings_total");
                String vicinity = mapLocationObject.getString("vicinity");

                MapLocation  mapLocation = new MapLocation(id, name, place_id, rating, lat, lon, null, userRatingsTotal, open, vicinity);


//                String API_KEY = MapsFragment.GOOGLE_MAPS_API_KEY;
//                Log.v(TAG, "Maps KEY: " + API_KEY);
//                String requestUrl = URLList.REQUEST_URL_MAPLOCATION_DETAILS
//                        + "&key=" + API_KEY + "&place_id=" + place_id;
//                Log.v(TAG, "Request: " + requestUrl);
//
//                // Create URL object
//                URL url = createUrl(requestUrl);
//
//                // Perform HTTP request to the URL and receive a JSON response back
//                String jsonResponse = null;
//                try {
//                    jsonResponse = makeHttpRequest(url);
//                } catch (IOException e) {
//                    Log.e(TAG, ("Error closing input stream"));
//                }
//                setDetailsForPlace(jsonResponse, mapLocation);

                mapLocations.add(mapLocation);
                Log.v(TAG, "added MapLocation: " + name);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, ("Problem parsing the mapLocation JSON results"));
        }
        return mapLocations;
    }

//    private static void setDetailsForPlace(String detailsJson, MapLocation mapLocation) throws JSONException {
//        Log.v(TAG, "details: " + detailsJson);
//        JSONObject detailsJsonObject = new JSONObject(detailsJson);
//        String status = detailsJsonObject.getString("status");
//        if(!status.equals("OK"))
//            return;
//
//        JSONObject result = detailsJsonObject.getJSONObject("result");
//        String address = null;
//        String phoneNumber = null;
//
//        if(result.has("formatted_address"))
//            address = result.getString("formatted_address");
//
//        if(result.has("formatted_phone_number"))
//            phoneNumber = result.getString("formatted_phone_number");
//
//
//        String mapsURL = result.getString("url");
//
//    }
}
