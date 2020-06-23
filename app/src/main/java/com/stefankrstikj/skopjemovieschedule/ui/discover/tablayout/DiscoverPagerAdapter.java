package com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

import org.jetbrains.annotations.NotNull;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class DiscoverPagerAdapter extends FragmentStatePagerAdapter {
	private static String TAG = "DiscoverPagerAdapter";
	@StringRes
	private static final int[] TAB_TITLES = new int[]{R.string.discover_tab_text_1, R.string.discover_tab_text_2, R.string.discover_tab_text_3, R.string.discover_tab_text_4, R.string.discover_tab_text_5};
	private final Context mContext;
	private OnClickListener mOnClickListener;

	public DiscoverPagerAdapter(Context context, OnClickListener onClickListener, FragmentManager fm) {
		super(fm);
		mContext = context;
		mOnClickListener = onClickListener;
	}

	@NotNull
	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class below).
		System.out.println("POSITION: " + position);
		switch(position){
			case 0:
				return new TrendingFragment(mOnClickListener);
			case 1:
				return new PopularFragment(mOnClickListener);
			case 2:
				return new TopRatedFragment(mOnClickListener);
			case 3:
				return new NowPlayingFragment(mOnClickListener);
			case 4:
				return new UpcomingFragment(mOnClickListener);
			default:
				return new UpcomingFragment(mOnClickListener);
		}
//		return DiscoverFragment.newInstance(position + 1);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mContext.getResources().getString(TAB_TITLES[position]);
	}

	@Override
	public int getCount() {
		return TAB_TITLES.length;
	}
}