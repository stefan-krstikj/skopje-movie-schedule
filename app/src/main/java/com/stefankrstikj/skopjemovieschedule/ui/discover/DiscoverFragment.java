package com.stefankrstikj.skopjemovieschedule.ui.discover;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.TmdbMovieAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

public class DiscoverFragment extends Fragment implements OnMoviePosterClickListener {
    private static String TAG = "DiscoverFragment";
    private DiscoverViewModel mViewModel;
    private TmdbMovieAdapter adapter;

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

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


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        initData();


    }

    private void initListView(){
        RecyclerView recyclerView = getView().findViewById(R.id.discover_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new TmdbMovieAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        DiscoverViewModelFactory factory = InjectorUtils.provideDiscoverViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(DiscoverViewModel.class);
        mViewModel.getAllPopularMovies().observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
        });
    }

    @Override
    public void onMovieClick(Object o, ImageView imageView) {

    }
}
