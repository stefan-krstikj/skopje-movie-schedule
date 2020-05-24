package com.stefankrstikj.skopjemovieschedule.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.ui.movies.tablayout.TabLayoutPagerAdapter;

public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";


    TabLayoutPagerAdapter tabLayoutPagerAdapter;
    ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.toolbar_fragment);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);


        tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), getContext());
        viewPager = view.findViewById(R.id.in_theaters_view_pager);
        viewPager.setAdapter(tabLayoutPagerAdapter);

    }
}