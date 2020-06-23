package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.person;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.stefankrstikj.skopjemovieschedule.R;

public class PersonPagerAdapter extends FragmentStatePagerAdapter {

	@StringRes
	private static final int[] TAB_TITLES = new int[] {R.string.detailed_person_tab_text_1, R.string.detailed_person_tab_text_2, R.string.detailed_person_tab_text_3};

	private final Context mContext;
	private DetailedTmdbPersonViewModel mDetailedTmdbPersonViewModel;

	public PersonPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context, DetailedTmdbPersonViewModel detailedTmdbPersonViewModel) {
		super(fm, behavior);
		mContext = context;
		mDetailedTmdbPersonViewModel = detailedTmdbPersonViewModel;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch (position){
			case 0:
				return null;
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
