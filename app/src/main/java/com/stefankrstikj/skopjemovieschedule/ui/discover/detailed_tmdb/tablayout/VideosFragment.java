package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieReviewAdapter;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieVideoAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;

import java.util.Objects;

public class VideosFragment extends Fragment {
	private static String TAG = "TrailerFragment";
	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private TmdbMovieVideoAdapter mAdapter;

	public VideosFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel) {
		Log.v(TAG, "Initialized trailer fragment");
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_with_recyclerview, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
		initData();
	}

	void initListView(){
		// init Recycler View
		Log.v(TAG, "Initializing ListView for Trailer");
		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_fragment_template);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new TmdbMovieVideoAdapter();
		recyclerView.setAdapter(mAdapter);
	}

	void initData(){
		mDetailedTmdbMovieViewModel.getTmdbdVideosForMovie().observe(getViewLifecycleOwner(), data -> {
			Log.v(TAG, "Received data: " + data.toString());
			mAdapter.updateDataset(data);
		});
	}
}
