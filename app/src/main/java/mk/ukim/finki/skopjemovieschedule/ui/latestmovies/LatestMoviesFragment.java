package mk.ukim.finki.skopjemovieschedule.ui.latestmovies;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.adapters.MovieAdapter;
import mk.ukim.finki.skopjemovieschedule.data.Movie;

public class LatestMoviesFragment extends Fragment {
    private static final String TAG = "LatestMoviesFragment";

    private LatestMoviesViewModel latestMoviesViewModel;
    MovieAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        latestMoviesViewModel =
                ViewModelProviders.of(this).get(LatestMoviesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_latest_movies, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        initData();
    }

    private void initListView(){
        RecyclerView recyclerView = getView().findViewById(R.id.latest_movies_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initData(){
        latestMoviesViewModel = ViewModelProviders.of(this).get(LatestMoviesViewModel.class);
        latestMoviesViewModel.getAll().observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
        });
    }


}