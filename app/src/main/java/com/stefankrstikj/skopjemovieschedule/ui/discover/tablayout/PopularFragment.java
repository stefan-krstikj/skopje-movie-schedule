package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

public class PopularFragment extends AbstractDiscoverTab {

	private static String TAG = "PopularFragment";
	private static String mResultType = "Popular";

//	private OnMoviePosterClickListener mOnMoviePosterClickListener;
//	private ShimmerFrameLayout mShimmerFrameLayout;
//	private RecyclerView mRecyclerView;
//	private TmdbMovieAdapter mAdapter;
//	private DiscoverViewModel mViewModel;
//	private SwipeRefreshLayout mSwipeRefreshLayout;

	public PopularFragment(OnMoviePosterClickListener onMoviePosterClickListener) {
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
