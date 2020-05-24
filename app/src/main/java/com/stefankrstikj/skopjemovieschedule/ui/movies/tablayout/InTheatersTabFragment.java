package com.stefankrstikj.skopjemovieschedule.ui.movies.tablayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.adapters.MovieAdapter;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import com.stefankrstikj.skopjemovieschedule.ui.movies.detailed_view.DetailMovieActivity;
import com.stefankrstikj.skopjemovieschedule.ui.movies.MoviesViewModel;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

public class InTheatersTabFragment extends Fragment implements OnMoviePosterClickListener{

    private MoviesViewModel moviesViewModel;
    MovieAdapter adapter;
    private static String TAG = "InTheatersTabFragment";
    private SwipeRefreshLayout swipeContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_intheaters_tab_fragment, container, false);
        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        initData();
        initSwipeContainer();
    }

    private void initListView(){
        RecyclerView recyclerView = getView().findViewById(R.id.latest_movies_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        MoviesViewModelFactory factory = InjectorUtils.provideMoviesViewModelFactory(getContext());
        moviesViewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel.class);
        moviesViewModel.getAll().observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
        });
    }

    private void initSwipeContainer(){
        swipeContainer = getActivity().findViewById(R.id.in_theaters_swipe);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMoviesAsync();
            }
        });
        swipeContainer.setColorSchemeColors(
                ContextCompat.getColor(getContext(),android.R.color.holo_blue_dark),
                ContextCompat.getColor(getContext(),android.R.color.holo_green_light),
                ContextCompat.getColor(getContext(),android.R.color.holo_orange_light),
                ContextCompat.getColor(getContext(),android.R.color.holo_red_light)
        );
    }

    private void fetchMoviesAsync(){
        moviesViewModel.refreshData();
        swipeContainer.setRefreshing(false);
    }


    @Override
    public void onMovieClick(Object o, ImageView imageView) {
        Movie movie = (Movie) o;
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        intent.putExtra("movie", movie);
        intent.putExtra("image", bitmap);
        ActivityOptions activityOptions = ActivityOptions
                .makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName());

        startActivity(intent, activityOptions.toBundle());
    }
}
