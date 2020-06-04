package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.DiscoverViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class TrendingFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private DiscoverViewModel mViewModel;
	private TmdbMovieAdapter adapter;
	private static String TAG = "TrendingFragment";
	private OnMoviePosterClickListener mOnMoviePosterClickListener;

	public TrendingFragment(OnMoviePosterClickListener onMoviePosterClickListener) {
		mOnMoviePosterClickListener = onMoviePosterClickListener;
	}

	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_discover_tab_layout, container, false);

		return root;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initListView();
        initData();
	}

	private void initListView(){
		RecyclerView recyclerView = getView().findViewById(R.id.discover_tab_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		adapter = new TmdbMovieAdapter(mOnMoviePosterClickListener);
		recyclerView.setAdapter(adapter);
	}

	private void initData(){

		DiscoverViewModelFactory factory = InjectorUtils.provideDiscoverViewModelFactory(getContext());
		mViewModel = ViewModelProviders.of(this, factory).get(DiscoverViewModel.class);
		mViewModel.getAllTrendingMovies().observe(getViewLifecycleOwner(), data -> {
			adapter.updateDataset(data);
		});
	}
}