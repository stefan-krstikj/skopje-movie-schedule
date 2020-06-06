package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;

public class CastFragment extends Fragment {
	private static String TAG = "CastFragment";
	DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;

	public CastFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel) {
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_cast, container, false);
	}
}
