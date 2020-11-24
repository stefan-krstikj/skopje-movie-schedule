package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.listeners.OnCastClickListener;
import com.stefankrstikj.skopjemovieschedule.listeners.OnMovieClickListener;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout.CastFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout.RecommendationsFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout.ReviewsFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout.VideosFragment;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {


	@StringRes
	private static final int[] TAB_TITLES = new int[] {R.string.detailed_tmdb_tab_text_1, R.string.detailed_tmdb_tab_text_2, R.string.detailed_tmdb_tab_text_3, R.string.detailed_tmdb_tab_text_4};

	private final Context mContext;
	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private OnMovieClickListener mOnMovieClickListener;
	private OnCastClickListener mOnCastClickListener;

	public DetailsPagerAdapter(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel, Context context, FragmentManager fm, OnMovieClickListener onMovieClickListener, OnCastClickListener onCastClickListener) {
		super(fm);
		mContext = context;
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
		mOnMovieClickListener = onMovieClickListener;
		mOnCastClickListener = onCastClickListener;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch(position){
			case 0:
				return new CastFragment(mDetailedTmdbMovieViewModel, mOnCastClickListener);
			case 1:
				return new VideosFragment(mDetailedTmdbMovieViewModel);
			case 2:
				return new ReviewsFragment(mDetailedTmdbMovieViewModel);
			case 3:
				return new RecommendationsFragment(mDetailedTmdbMovieViewModel, mOnMovieClickListener);
			default:
				return null;
		}
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mContext.getResources().getString(TAB_TITLES[position]);
	}

	@Override
	public int getCount() {
		// Show 2 total pages.
		return TAB_TITLES.length;
	}
}
