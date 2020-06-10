package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

import java.util.Objects;

public class RecommendationsFragment extends Fragment {
	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private TmdbMovieAdapter mAdapter;


	public RecommendationsFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel) {
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
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

		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_movie_list);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 0, 0, 0);
		recyclerView.setLayoutParams(lp);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
		mAdapter = new TmdbMovieAdapter(new OnMoviePosterClickListener() {
			@Override
			public void onMovieClick(Object o, ImageView imageView) {

			}
		});
		recyclerView.setAdapter(mAdapter);
	}

	void initData(){
		this.mDetailedTmdbMovieViewModel.getTmdbMovieRecommendationsForMovie().observe(getViewLifecycleOwner(), data -> mAdapter.updateDataset(data));
	}
}
