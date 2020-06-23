package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieGridAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.DetailedTmdbMovieViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

import java.util.Objects;

public class RecommendationsFragment extends Fragment {
	private static final String TAG = "RecommendationsFragment";

	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private TmdbMovieGridAdapter mAdapter;
	private OnClickListener mOnClickListener;


	public RecommendationsFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel, OnClickListener onClickListener) {
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
		mOnClickListener = onClickListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_movies_list_template, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
		initData();
	}

	void initListView(){
		ShimmerFrameLayout shimmerFrameLayout = getView().findViewById(R.id.movie_list_shimmer);
		shimmerFrameLayout.stopShimmer();
		shimmerFrameLayout.setVisibility(View.GONE);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 0, 0, 0);

		SwipeRefreshLayout swipeRefreshLayout = getView().findViewById(R.id.swipe_movie_list);
		swipeRefreshLayout.setLayoutParams(lp);

		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_movie_list);
		recyclerView.setLayoutParams(lp);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
		mAdapter = new TmdbMovieGridAdapter(mOnClickListener);
		recyclerView.setAdapter(mAdapter);
	}

	void initData(){
		this.mDetailedTmdbMovieViewModel.getTmdbMovieRecommendationsForMovie().observe(getViewLifecycleOwner(), data -> mAdapter.updateDataset(data));
	}

}
