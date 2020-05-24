package com.stefankrstikj.skopjemovieschedule.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.stefankrstikj.skopjemovieschedule.api_response.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.api_response.tmdb.TmdbMovieDiscoveryResponse;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;

import java.io.IOException;
import java.util.List;

public class TmdbAsyncTask extends AsyncTask<Void, Void, List<TmdbMovie>> {
	private static String TAG = "TmdbAsyncTask";
	private TmdbMovieRepository mTmdbMovieRepository;

	public TmdbAsyncTask(TmdbMovieRepository tmdbMovieRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
	}

	@Override
	protected List<TmdbMovie> doInBackground(Void... voids) {
		try {
			TmdbMovieDiscoveryResponse response = TmdbApiClient.getPopularMovies();
			Log.v(TAG, "Response: " + response.toString());
			return response.getResults();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<TmdbMovie> movies) {
		if(movies != null){
			for(TmdbMovie m : movies){
				mTmdbMovieRepository.insert(m);
			}
		}
	}
}
