package mk.ukim.finki.skopjemovieschedule.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.data.Movie;

public class MovieAdapter extends RecyclerView.Adapter {
    private static final String TAG = "MovieAdapter";

    private List<Movie> mDataset;

    public static class MovieHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private TextView movieGenres;
        private ImageView moviePoster;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            // todo: what does this do? useless?
            itemView.setTag(this);
        }

        public void setText(Movie movie){
            movieTitle.setText(movie.mMovieTitle);
            Picasso.get()
                    .load(movie.mPosterURL)
                    .into(moviePoster);
        }
    }

    public MovieAdapter() {
        this.mDataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_layout, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieHolder) holder).setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        Log.v(TAG, "ItemCount: " + mDataset.size());
        return mDataset.size();
    }

    public void updateDataset(List<Movie> newDataset){
        this.mDataset = newDataset;
        notifyDataSetChanged();
    }
}
