package com.stefankrstikj.skopjemovieschedule;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMoviesView extends AppCompatActivity {
	private static final String TAG = "MainMoviesView";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_movies_view);


		// set toolbar
		Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_discover);
		setSupportActionBar(myToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		BottomNavigationView navView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_discover,
				R.id.navigation_movies, R.id.navigation_theaters)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupWithNavController(navView, navController);
	}

	@Deprecated
	public void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, View sharedView, String sharedElementName) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		// check if the fragment is in back stack
		boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
		if (fragmentPopped) {
			// fragment is pop from backStack
		} else {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				current.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.fragment_image_transition));
				current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

				newFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.fragment_image_transition));
				newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
			}
			Log.v(TAG, "Fragments: " + fragmentManager.getBackStackEntryCount());
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.activity_main_movies_container, newFragment, tag);
			fragmentTransaction.addToBackStack(tag);
			fragmentTransaction.addSharedElement(sharedView, sharedElementName);
			fragmentTransaction.commit();
		}
	}
}
