package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovie;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout.DiscoverPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

import java.io.ByteArrayOutputStream;

public class DiscoverFragment extends Fragment implements OnMoviePosterClickListener {
	private static String TAG = "DiscoverFragment";

	DiscoverPagerAdapter mDiscoverPagerAdapter;
	ViewPager viewPager;
	private DiscoverSearchResultsViewModel mDiscoverSearchResultsViewModel;


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_discover_tabs, container, false);
		setHasOptionsMenu(true);
		androidx.appcompat.widget.Toolbar toolbar = root.findViewById(R.id.toolbar_discover);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		return root;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mDiscoverPagerAdapter = new DiscoverPagerAdapter(getContext(), this, getChildFragmentManager());
		viewPager = view.findViewById(R.id.discover_view_pager);
		viewPager.setAdapter(mDiscoverPagerAdapter);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.options_menu_discover, menu);

//		androidx.appcompat.widget.SearchView searchView =
//				(androidx.appcompat.widget.SearchView) menu.findItem(R.id.discover_search).getActionView();

		MenuItem searchItem = menu.findItem(R.id.discover_search);
		SearchView searchView = (SearchView) searchItem.getActionView();

		searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				DiscoverSearchResultsViewModelFactory factory = InjectorUtils.provideDiscoverSearchResultsViewModelFactory(getContext());
				mDiscoverSearchResultsViewModel = ViewModelProviders.of(getActivity(), factory).get(DiscoverSearchResultsViewModel.class);
				return true;
			}

			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				getActivity().getSupportFragmentManager().popBackStackImmediate();
//				getActivity().getSupportFragmentManager().popBackStackImmediate();
				return true;
			}
		});

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.v(TAG, "Submitting " + newText);
				if(newText == null || newText.length() <= 3)
					return false;
				if(getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0){
					Fragment fragment = new DiscoverSearchResultsFragment(mDiscoverSearchResultsViewModel, DiscoverFragment.this::onMovieClick);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, fragment).addToBackStack("DiscoverFragment").commit();
				}

				mDiscoverSearchResultsViewModel.fetchQueryMovies(newText);
				return false;
			}
		});


	}

	@Override
	public void onMovieClick(Object o, ImageView imageView) {
		// todo: bad implementation, cant do the OnMoviePosterClick inside recommendationFragment (which is inside DetailedTmdbMovie.class)
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
