package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Handler;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieGridAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.RefreshDataCallback;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

public abstract class AbstractDiscoverTab extends Fragment implements RefreshDataCallback {

	protected OnMoviePosterClickListener mOnMoviePosterClickListener;
	protected ShimmerFrameLayout mShimmerFrameLayout;
	protected RecyclerView mRecyclerView;
	protected TmdbMovieGridAdapter mAdapter;
	protected DiscoverViewModel mViewModel;
	protected SwipeRefreshLayout mSwipeRefreshLayout;

	abstract void initViews();

	protected void initRecyclerView(){
		mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setItemViewCacheSize(20);
		mAdapter = new TmdbMovieGridAdapter(mOnMoviePosterClickListener);
		mRecyclerView.setAdapter(mAdapter);
	}

	protected void initData() {
		DiscoverViewModelFactory factory = InjectorUtils.provideDiscoverViewModelFactory(getContext());
		mViewModel = ViewModelProviders.of(this, factory).get(DiscoverViewModel.class);
		initDataSource();
		if(mAdapter.getItemCount() < 19)
			mShimmerFrameLayout.startShimmer();
	}

	abstract void initDataSource();

	protected void initSwipe() {
		mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> refreshData(), 100));
	}

	protected void refreshData() {
		if(mAdapter.getItemCount() < 19)
			return;
		mShimmerFrameLayout.setVisibility(View.VISIBLE);
		mRecyclerView.setVisibility(View.GONE);
		mShimmerFrameLayout.startShimmer();
		fetchNewData();
	}

	abstract void fetchNewData();


}
