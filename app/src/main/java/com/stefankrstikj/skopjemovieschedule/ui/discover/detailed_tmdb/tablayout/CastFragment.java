package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieCastAdapter;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;

public class CastFragment extends Fragment {
	private static String TAG = "CastFragment";
	DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	TmdbMovieCastAdapter mAdapter;

	public CastFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel) {
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_cast, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
		initData();
	}

	void initListView(){
		RecyclerView recyclerView = getView().findViewById(R.id.recyclerView_tmdb_movie_cast);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new TmdbMovieCastAdapter();
		recyclerView.setAdapter(mAdapter);
	}

	void initData(){
		mDetailedTmdbMovieViewModel.getTmdbMovieCastForMovie().observe(getViewLifecycleOwner(), data -> {
			mAdapter.updateDataset(data);
		});
	}
}
