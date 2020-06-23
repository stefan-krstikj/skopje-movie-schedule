package com.stefankrstikj.skopjemovieschedule.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.stefankrstikj.skopjemovieschedule.utils.MovieUtils.POSTER_HEIGHT;
import static com.stefankrstikj.skopjemovieschedule.utils.MovieUtils.POSTER_WIDTH;

public class MovieAdapter extends RecyclerView.Adapter {
    private static final String TAG = "MovieAdapter";

    private List<Movie> mDataset;
    OnClickListener mListener;

    public class MovieHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private ImageView moviePoster;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.textView_movie_list_grid_movie_title);
            moviePoster = itemView.findViewById(R.id.imageView_movie_list_grid_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(mDataset.get(getAdapterPosition()), moviePoster, getAdapterPosition());
                }
            });
        }

        void setText(Movie movie, final OnClickListener listener){
            movieTitle.setText(movie.getMovieDisplayTitle());
            Picasso.get()
                    .load(movie.getPosterURL())
                    .resize(POSTER_WIDTH, POSTER_HEIGHT)
                    .into(moviePoster);
        }
    }

    public MovieAdapter(OnClickListener listener) {
        this.mDataset = new ArrayList<>();
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout_movie_grid, parent, false);
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
