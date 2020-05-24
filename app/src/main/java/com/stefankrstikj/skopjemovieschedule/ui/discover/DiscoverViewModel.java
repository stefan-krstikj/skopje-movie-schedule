package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api_response.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.api_response.tmdb.TmdbMovieDiscoveryResponse;
import com.stefankrstikj.skopjemovieschedule.api_response.tmdb.TmdbMovieDiscoveryResult;

import java.io.IOException;

public class DiscoverViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    LiveData<TmdbMovieDiscoveryResponse> getAllPopularMovies(){
        AsyncTask<Void, Void, TmdbMovieDiscoveryResponse> asyncTask = new AsyncTask<Void, Void, TmdbMovieDiscoveryResponse>() {
            @Override
            protected TmdbMovieDiscoveryResponse doInBackground(Void... voids) {
                TmdbApiClient tmdbApiClient = new TmdbApiClient();

                try {
                    TmdbMovieDiscoveryResponse tmdb = tmdbApiClient.getPopularMovies();
                    return tmdb;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        asyncTask.execute();
        return null;
    }
}
