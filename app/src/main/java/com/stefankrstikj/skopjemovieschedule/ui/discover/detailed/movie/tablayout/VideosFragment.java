package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieVideoAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.DetailedTmdbMovieViewModel;

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
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 0, 0, 0);

		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_fragment_template);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new TmdbMovieVideoAdapter();
		recyclerView.setAdapter(mAdapter);
		recyclerView.setLayoutParams(lp);
	}

	void initData(){
		mDetailedTmdbMovieViewModel.getTmdbdVideosForMovie().observe(getViewLifecycleOwner(), data -> {
			Log.v(TAG, "Received data: " + data.toString());
			mAdapter.updateDataset(data);
		});
	}
}
