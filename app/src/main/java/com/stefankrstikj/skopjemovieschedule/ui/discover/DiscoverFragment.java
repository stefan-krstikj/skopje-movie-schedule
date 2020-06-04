package com.stefankrstikj.skopjemovieschedule.ui.discover;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.DetailedTmdbMovie;
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

        mDiscoverPagerAdapter = new DiscoverPagerAdapter(getContext(), this, getChildFragmentManager());
        viewPager = view.findViewById(R.id.discover_view_pager);
        viewPager.setAdapter(mDiscoverPagerAdapter);
    }


    @Override
    public void onMovieClick(Object o, ImageView imageView) {
        TmdbMovieDetailed movie = (TmdbMovieDetailed) o;
        Intent intent = new Intent(getContext(), DetailedTmdbMovie.class);
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        intent.putExtra("movie", movie);
        intent.putExtra("image", bitmap);
        ActivityOptions activityOptions = ActivityOptions
                .makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName());

        startActivity(intent, activityOptions.toBundle());
    }
}
