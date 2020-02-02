package mk.ukim.finki.skopjemovieschedule.ui.intheaters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.adapters.MovieAdapter;

public class InTheatersFragment extends Fragment {
    private static final String TAG = "InTheatersFragment";

    private InTheatersViewModel inTheatersViewModel;
    MovieAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inTheatersViewModel =
                ViewModelProviders.of(this).get(InTheatersViewModel.class);
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
        inTheatersViewModel = ViewModelProviders.of(this).get(InTheatersViewModel.class);
        inTheatersViewModel.getAll().observe(getViewLifecycleOwner(), data -> {
            adapter.updateDataset(data);
        });
    }


}