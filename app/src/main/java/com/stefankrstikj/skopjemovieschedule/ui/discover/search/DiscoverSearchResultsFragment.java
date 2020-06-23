package com.stefankrstikj.skopjemovieschedule.ui.discover.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieListAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

import java.util.Objects;

public class DiscoverSearchResultsFragment extends Fragment {
	private static String TAG = "DiscoverSearchResultsFragment";
	private TmdbMovieListAdapter mAdapter;
	private DiscoverSearchResultsViewModel mDiscoverSearchResultsViewModel;
	private OnClickListener mOnClickListener;

	public DiscoverSearchResultsFragment(DiscoverSearchResultsViewModel discoverSearchResultsViewModel, OnClickListener onClickListener) {
		mDiscoverSearchResultsViewModel = discoverSearchResultsViewModel;
		mOnClickListener = onClickListener;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_with_recyclerview, container, false);
		return root;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
		initData();
	}

	private void initListView(){
		RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView_fragment_template);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new TmdbMovieListAdapter(mOnClickListener);
		recyclerView.setAdapter(mAdapter);
	}

	private void initData(){
		Log.v(TAG, "adding observer");
		this.mDiscoverSearchResultsViewModel.getQueryMovies().observe(getViewLifecycleOwner(), data -> {
			Log.v(TAG, "Received data: " + data);
			mAdapter.updateDataset(data);
		});
	}
}
