package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieReviewAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovieViewModel;

import java.util.Objects;

public class ReviewsFragment extends Fragment {
	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private TmdbMovieReviewAdapter mAdapter;

	public ReviewsFragment(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel) {
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reviews, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
		initData();
	}

	void initListView(){
		// init Recycler View
		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_tmdb_movie_reviews);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new TmdbMovieReviewAdapter();
		recyclerView.setAdapter(mAdapter);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
				LinearLayoutManager.VERTICAL);
		dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.divier_item_decoration)));
		recyclerView.addItemDecoration(dividerItemDecoration);
	}

	void initData(){
		mDetailedTmdbMovieViewModel.getTmdbMovieReviewsForMovie().observe(getViewLifecycleOwner(), data -> mAdapter.updateDataset(data));
	}
}