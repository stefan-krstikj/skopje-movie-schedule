package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieGridAdapter;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovie;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class RecommendationsFragment extends Fragment implements OnMoviePosterClickListener {
	private static final String TAG = "RecommendationsFragment";

	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private TmdbMovieGridAdapter mAdapter;


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

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 0, 0, 0);

		SwipeRefreshLayout swipeRefreshLayout = getView().findViewById(R.id.swipe_movie_list);
		swipeRefreshLayout.setLayoutParams(lp);

		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_movie_list);
		recyclerView.setLayoutParams(lp);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
		mAdapter = new TmdbMovieGridAdapter(this);
		recyclerView.setAdapter(mAdapter);
	}

	void initData(){
		this.mDetailedTmdbMovieViewModel.getTmdbMovieRecommendationsForMovie().observe(getViewLifecycleOwner(), data -> mAdapter.updateDataset(data));
	}

	@Override
	public void onMovieClick(Object o, ImageView imageView) {
		TmdbMovieDetailed movie = (TmdbMovieDetailed) o;
		Intent intent = new Intent(getActivity(), DetailedTmdbMovie.class);

		imageView.buildDrawingCache();
		Bitmap bitmap = imageView.getDrawingCache();

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
		intent.putExtra("byteArray", bs.toByteArray());
		intent.putExtra("movie", movie);
		ActivityOptions activityOptions = ActivityOptions
				.makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName());

		startActivity(intent, activityOptions.toBundle());
	}
}
