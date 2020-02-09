package mk.ukim.finki.skopjemovieschedule.ui.movies.tablayout;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.adapters.MovieAdapter;
import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.ui.movies.MoviesViewModelFactory;
import mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view.DetailMovieActivity;
import mk.ukim.finki.skopjemovieschedule.ui.movies.MoviesViewModel;
import mk.ukim.finki.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import mk.ukim.finki.skopjemovieschedule.utils.InjectorUtils;

public class ComingSoonTabFragment extends Fragment implements OnMoviePosterClickListener{

    private MoviesViewModel moviesViewModel;
    private MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comingsoon_tab_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        initData();
    }

    private void initListView(){
        RecyclerView recyclerView = getView().findViewById(R.id.coming_soon_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        MoviesViewModelFactory factory = InjectorUtils.provideMoviesViewModelFactory(getContext());
        moviesViewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel.class);
        moviesViewModel.getAllComingSoon().observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
        });
    }

    @Override
    public void onMovieClick(Movie movie, ImageView imageView) {
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
