package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

public class TopRatedFragment extends AbstractDiscoverTab {

	private static String TAG = "TopRatedFragment";
	private static String mResultType = "Top Rated";

//	private OnMoviePosterClickListener mOnMoviePosterClickListener;
//	private ShimmerFrameLayout mShimmerFrameLayout;
//	private RecyclerView mRecyclerView;
//	private TmdbMovieAdapter mAdapter;
//	private DiscoverViewModel mViewModel;
//	private SwipeRefreshLayout mSwipeRefreshLayout;

	public TopRatedFragment(OnMoviePosterClickListener onMoviePosterClickListener) {
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
		mViewModel.fetchTopRatedMovies();
		mViewModel.getAllTopRatedMovies().observe(getViewLifecycleOwner(), data -> {
			mAdapter.updateDataset(this, data);
		});
	}

	public void fetchNewData() {
		mAdapter.deleteDataset();
		mViewModel.clearAll(mResultType);
		mViewModel.fetchTopRatedMovies();
	}

	@Override
	public void dataRefreshed() {
		mSwipeRefreshLayout.setRefreshing(false);
		mShimmerFrameLayout.stopShimmer();
		mShimmerFrameLayout.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.VISIBLE);
	}

}
