package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

public class TrendingFragment extends AbstractDiscoverTab {

	private static String TAG = "TrendingFragment";
	private static String mResultType = "Trending";

//	private OnMoviePosterClickListener mOnMoviePosterClickListener;
//	private ShimmerFrameLayout mShimmerFrameLayout;
//	private RecyclerView mRecyclerView;
//	private TmdbMovieAdapter mAdapter;
//	private DiscoverViewModel mViewModel;
//	private SwipeRefreshLayout mSwipeRefreshLayout;


	public TrendingFragment(OnMoviePosterClickListener onMoviePosterClickListener) {
		mOnMoviePosterClickListener = onMoviePosterClickListener;
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
		mShimmerFrameLayout = getView().findViewById(R.id.movie_list_shimmer);
		mSwipeRefreshLayout = getView().findViewById(R.id.swipe_movie_list);
		mRecyclerView = getView().findViewById(R.id.recyclerView_movie_list);
	}

	@Override
	void initDataSource() {
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