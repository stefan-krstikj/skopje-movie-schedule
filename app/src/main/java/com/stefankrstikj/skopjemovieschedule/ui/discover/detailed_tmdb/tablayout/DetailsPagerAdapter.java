package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.stefankrstikj.skopjemovieschedule.R;

public class DetailsPagerAdapter extends FragmentPagerAdapter {


	@StringRes
	private static final int[] TAB_TITLES = new int[] {R.string.detailed_tmdb_tab_text_1, R.string.detailed_tmdb_tab_text_2, R.string.detailed_tmdb_tab_text_3};

	private final Context mContext;


	public DetailsPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch(position){
			case 0:
				return new TrailerFragment();
			case 1:
				return new CastFragment();
			case 2:
				return new CastFragment();
			default:
				return new TrailerFragment();
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
