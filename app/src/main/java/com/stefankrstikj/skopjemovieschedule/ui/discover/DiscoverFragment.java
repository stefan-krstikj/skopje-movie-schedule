package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.graphics.Bitmap;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.DetailedTmdbMovieFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.discover.search.DiscoverSearchResultsViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout.DiscoverPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

import java.io.ByteArrayOutputStream;

public class DiscoverFragment extends Fragment implements OnClickListener {
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
				if (newText == null || newText.length() <= 3)
					return false;
				if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
					Fragment fragment = new DiscoverSearchResultsFragment(mDiscoverSearchResultsViewModel, DiscoverFragment.this::onClick);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, fragment).addToBackStack("DiscoverFragment").commit();
				}

				mDiscoverSearchResultsViewModel.fetchQueryMovies(newText);
				return false;
			}
		});
	}

	@Override
	public void onClick(Object o, ImageView imageView, Integer position) {
		TmdbMovieDetailed movie = (TmdbMovieDetailed) o;

		imageView.buildDrawingCache();
		Bitmap bitmap = imageView.getDrawingCache();

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);

		Bundle bundle = new Bundle();
		bundle.putByteArray("byteArray", bs.toByteArray());
		bundle.putSerializable("movie", movie);
		bundle.putSerializable("transitionName", "imageMain" + position);
		Fragment fragment = new DetailedTmdbMovieFragment();
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getChildFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.coordinatorLayout_fragment_discover, fragment);
		fragmentTransaction.addToBackStack(TAG);
		fragmentTransaction.addSharedElement(imageView, "transition" + position);
		fragmentTransaction.commit();
	}
}
