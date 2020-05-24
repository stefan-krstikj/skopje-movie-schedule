package com.stefankrstikj.skopjemovieschedule.adapters;

import android.util.Log;
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
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovie;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieAdapter extends RecyclerView.Adapter {
	private static final String TAG = "TmdbMovieAdapter";
	// todo: refactor this and MovieAdapter
	private static final int POSTER_WIDTH = 272;
	private static final int POSTER_HEIGHT = 403;

	// todo: this and MovieAdapter can be the same class, just generic List items
	private List<TmdbMovie> mDataset;
	OnMoviePosterClickListener mListener;

	public class TmdbMovieHolder extends RecyclerView.ViewHolder{

		private TextView movieTitle;
		private ImageView moviePoster;

		TmdbMovieHolder(@NonNull View itemView) {
			super(itemView);
			movieTitle = itemView.findViewById(R.id.movieTitle);
			moviePoster = itemView.findViewById(R.id.moviePoster);
			itemView.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					mListener.onMovieClick(mDataset.get(getAdapterPosition()), moviePoster);
				}
			});
		}

		void setText(TmdbMovie movie, final OnMoviePosterClickListener listener){
			movieTitle.setText(MovieUtils.getDisplayTitle(movie));
			Picasso.get()
					.load(URLList.URLTmdbPoster + movie.getPosterPath())
					.resize(POSTER_WIDTH, POSTER_HEIGHT)
					.into(moviePoster);
		}
	}

	public TmdbMovieAdapter( OnMoviePosterClickListener listener) {
		mDataset = new ArrayList<>();
		mListener = listener;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v  = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.movie_list_layout, parent, false);
		return new TmdbMovieHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		((TmdbMovieHolder) holder).setText(mDataset.get(position), mListener);
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void updateDataset(List<TmdbMovie> newDataset){
		this.mDataset = newDataset;
		notifyDataSetChanged();
	}
}
