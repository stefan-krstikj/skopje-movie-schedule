package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout.DiscoverPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout.TrendingFragment;
import com.stefankrstikj.skopjemovieschedule.ui.discover.tablayout.UpcomingFragment;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

public class DiscoverFragment extends Fragment implements OnMoviePosterClickListener {
    private static String TAG = "DiscoverFragment";

    DiscoverPagerAdapter mDiscoverPagerAdapter;
    ViewPager viewPager;

    private static final String ARG_SECTION_NUMBER = "section_number";


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ViewModel view = ViewModelProviders.of(this).get(TrendingViewModel.class);
//        int index = 1;
//        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_SECTION_NUMBER);
//        }
//        mTrendingViewModel.setIndex(index);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDiscoverPagerAdapter = new DiscoverPagerAdapter(getContext(), getChildFragmentManager());
        viewPager = view.findViewById(R.id.discover_view_pager);
        viewPager.setAdapter(mDiscoverPagerAdapter);

//        initListView();
//        initData();
    }


    @Override
    public void onMovieClick(Object o, ImageView imageView) {

    }
}
