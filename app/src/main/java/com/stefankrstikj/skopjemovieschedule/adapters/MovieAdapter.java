package com.stefankrstikj.skopjemovieschedule.adapters;

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

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;

public class MovieAdapter extends RecyclerView.Adapter {
    private static final String TAG = "MovieAdapter";

    // todo: refactor this and TmdbMovieAdapter
    private static final int POSTER_WIDTH = 272;
    private static final int POSTER_HEIGHT = 403;

    private List<Movie> mDataset;
    OnMoviePosterClickListener mListener;

    public class MovieHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private ImageView moviePoster;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onMovieClick(mDataset.get(getAdapterPosition()), moviePoster);
                }
            });
        }

        void setText(Movie movie, final OnMoviePosterClickListener listener){
            movieTitle.setText(movie.getMovieDisplayTitle());
            Picasso.get()
                    .load(movie.getPosterURL())
                    .resize(POSTER_WIDTH, POSTER_HEIGHT)
                    .into(moviePoster);
        }
    }

    public MovieAdapter(OnMoviePosterClickListener listener) {
        this.mDataset = new ArrayList<>();
        this.mListener = listener;
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
        ((MovieHolder) holder).setText(mDataset.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateDataset(List<Movie> newDataset){
        this.mDataset = newDataset;
        notifyDataSetChanged();
    }
}
