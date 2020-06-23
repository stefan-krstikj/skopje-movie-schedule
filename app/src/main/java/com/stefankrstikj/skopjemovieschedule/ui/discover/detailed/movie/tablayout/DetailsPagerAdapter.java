package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.DetailedTmdbMovieViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {


	@StringRes
	private static final int[] TAB_TITLES = new int[] {R.string.detailed_tmdb_tab_text_1, R.string.detailed_tmdb_tab_text_2, R.string.detailed_tmdb_tab_text_3, R.string.detailed_tmdb_tab_text_4};

	private final Context mContext;
	private DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;
	private OnClickListener mOnClickListener;


	public DetailsPagerAdapter(DetailedTmdbMovieViewModel detailedTmdbMovieViewModel, Context context, FragmentManager fm, OnClickListener onClickListener) {
		super(fm);
		mContext = context;
		mDetailedTmdbMovieViewModel = detailedTmdbMovieViewModel;
		mOnClickListener = onClickListener;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch(position){
			case 0:
				return new CastFragment(mDetailedTmdbMovieViewModel, mOnClickListener);
			case 1:
				return new VideosFragment(mDetailedTmdbMovieViewModel);
			case 2:
				return new ReviewsFragment(mDetailedTmdbMovieViewModel);
			case 3:
				return new RecommendationsFragment(mDetailedTmdbMovieViewModel, mOnClickListener);
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
