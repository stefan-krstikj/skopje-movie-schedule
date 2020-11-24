package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.listeners.OnMovieClickListener;

public class TrendingFragment extends AbstractDiscoverTab {

	private static String TAG = "TrendingFragment";
	private static String mResultType = "Trending";


	public TrendingFragment(OnMovieClickListener onMovieClickListener) {
		mOnMovieClickListener = onMovieClickListener;
	}

	public View onCreateView(
			@NonNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_movies_list_template, container, false);
		return root;
	}

	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews();
		initRecyclerView();
		initData();
		initSwipe();
	}

	@Override
	void initViews() {
		Log.v(TAG, "initViews()");
		mShimmerFrameLayout = getView().findViewById(R.id.movie_list_shimmer);
		mSwipeRefreshLayout = getView().findViewById(R.id.swipe_movie_list);
		mRecyclerView = getView().findViewById(R.id.recyclerView_movie_list);
	}

	@Override
	void initDataSource() {
		Log.v(TAG, "initDataSource()");
		mViewModel.fetchTrendingMovies();
		mViewModel.getAllTrendingMovies().observe(getViewLifecycleOwner(), data -> {
			mAdapter.updateDataset(this, data);
		});
	}

	@Override
	public void fetchNewData() {
		Log.v(TAG, "Fetching new data");
		mViewModel.clearAll(mResultType);
		mAdapter.deleteDataset();
		mViewModel.fetchTrendingMovies();
	}

	@Override
	public void dataRefreshed() {
		Log.v(TAG, "Trending data refreshed");
		mSwipeRefreshLayout.setRefreshing(false);
		mShimmerFrameLayout.stopShimmer();
		mShimmerFrameLayout.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.VISIBLE);
	}
}