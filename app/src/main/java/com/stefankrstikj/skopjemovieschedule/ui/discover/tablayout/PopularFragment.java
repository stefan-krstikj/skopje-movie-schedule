package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

public class PopularFragment extends AbstractDiscoverTab {

	private static String TAG = "PopularFragment";
	private static String mResultType = "Popular";

//	private OnMoviePosterClickListener mOnMoviePosterClickListener;
//	private ShimmerFrameLayout mShimmerFrameLayout;
//	private RecyclerView mRecyclerView;
//	private TmdbMovieAdapter mAdapter;
//	private DiscoverViewModel mViewModel;
//	private SwipeRefreshLayout mSwipeRefreshLayout;

	public PopularFragment(OnClickListener onClickListener) {
		mOnClickListener = onClickListener;
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
		mViewModel.fetchPopularMovies();
		mViewModel.getAllPopularMovies().observe(getViewLifecycleOwner(), data -> {
			Log.v(TAG, "Received data, now updating dataset: " + data.size());
			mAdapter.updateDataset(this, data);
		});
	}

	public void fetchNewData() {
		Log.v(TAG, "Fetching new data");
		mAdapter.deleteDataset();
		mViewModel.clearAll(mResultType);
		mViewModel.fetchPopularMovies();
	}

	@Override
	public void dataRefreshed() {
		Log.v(TAG, "Popular data refreshed");
		mSwipeRefreshLayout.setRefreshing(false);
		mShimmerFrameLayout.stopShimmer();
		mShimmerFrameLayout.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.VISIBLE);
	}

}
