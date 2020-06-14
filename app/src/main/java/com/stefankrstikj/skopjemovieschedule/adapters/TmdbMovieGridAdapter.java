package com.stefankrstikj.skopjemovieschedule.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.RefreshDataCallback;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieGridAdapter extends RecyclerView.Adapter {
	private static final String TAG = "TmdbMovieAdapter";
	private List<TmdbMovieDetailed> mDataset;
	private OnMoviePosterClickListener mOnMoviePosterClickListener;

	public class TmdbMovieGridHolder extends RecyclerView.ViewHolder {

		private TextView movieTitle;
		private ImageView moviePoster;

		TmdbMovieGridHolder(@NonNull View itemView) {
			super(itemView);
			movieTitle = itemView.findViewById(R.id.textView_movie_list_grid_movie_title);
			moviePoster = itemView.findViewById(R.id.imageView_movie_list_grid_movie_poster);
			itemView.setOnClickListener(v -> mOnMoviePosterClickListener.onMovieClick(mDataset.get(getAdapterPosition()), moviePoster));
		}

		void setText(TmdbMovieDetailed movie) {
			movieTitle.setText(MovieUtils.getDisplayTitle(movie));
			Glide
					.with(itemView.getContext())
					.load(URLList.URLTmdbPoster + movie.getPosterPath())
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.fitCenter()
					.placeholder(R.drawable.movie_list_placeholder)
					.transition(DrawableTransitionOptions.withCrossFade())
					.into(moviePoster);
		}
	}

	public TmdbMovieGridAdapter(OnMoviePosterClickListener onMoviePosterClickListener) {
		mDataset = new ArrayList<>();
		mOnMoviePosterClickListener = onMoviePosterClickListener;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_layout_movie_grid, parent, false);
		return new TmdbMovieGridHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		((TmdbMovieGridHolder) holder).setText(mDataset.get(position));
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void deleteDataset() {
		mDataset.clear();
		notifyDataSetChanged();
	}

	public void updateDataset(RefreshDataCallback refreshDataCallback, List<TmdbMovieDetailed> newDataset) {
		Log.v(TAG, "Received update dataset " + newDataset.size());
		// only change the view when the dataset is complete, to avoid UI
		if (newDataset.size() < 20)
			return;
		Log.v(TAG, "yep, data is refreshed and good!");
		refreshDataCallback.dataRefreshed();
		if(!mDataset.equals(newDataset)){
			mDataset = newDataset;
			notifyDataSetChanged();
		}


	}

	public void updateDataset(List<TmdbMovieDetailed> newDataset) {
		this.mDataset = newDataset;
		notifyDataSetChanged();
	}
}
