package mk.ukim.finki.skopjemovieschedule.ui.movies.tablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import mk.ukim.finki.skopjemovieschedule.R;

public class TabLayoutPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.in_theaters_tab_text_1, R.string.in_theaters_tab_text_2};

    private Context mContext;

    public TabLayoutPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                InTheatersTabFragment tab1 = new InTheatersTabFragment();
                return tab1;
            case 1:
                ComingSoonTabFragment tab2 = new ComingSoonTabFragment();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
