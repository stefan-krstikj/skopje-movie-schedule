package com.stefankrstikj.skopjemovieschedule.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.RefreshDataCallback;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnMoviePosterClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TmdbMovieAdapter extends RecyclerView.Adapter {
	private static final String TAG = "TmdbMovieAdapter";
	private List<TmdbMovieDetailed> mDataset;
	private OnMoviePosterClickListener mListener;

	public class TmdbMovieHolder extends RecyclerView.ViewHolder {

		private TextView movieTitle;
		private ImageView moviePoster;

		TmdbMovieHolder(@NonNull View itemView) {
			super(itemView);
			movieTitle = itemView.findViewById(R.id.movieTitle);
			moviePoster = itemView.findViewById(R.id.moviePoster);
			itemView.setOnClickListener(v -> mListener.onMovieClick(mDataset.get(getAdapterPosition()), moviePoster));
		}

		void setText(TmdbMovieDetailed movie) {
			movieTitle.setText(MovieUtils.getDisplayTitle(movie));
			Log.v(TAG, URLList.URLTmdbPoster + movie.getPosterPath());
//			Picasso.get()
//					.load(URLList.URLTmdbPoster + movie.getPosterPath())
////					.placeholder(R.drawable.movie_poster_template)
////					.resize(POSTER_WIDTH, POSTER_HEIGHT)
//					.into(moviePoster);
//			CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
//			circularProgressDrawable.setStrokeWidth(5f);
//			circularProgressDrawable.setCenterRadius(30f);
//			circularProgressDrawable.start();
			DrawableCrossFadeFactory factory =
					new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
			Glide
					.with(itemView.getContext())
//					.asBitmap()
					.load(URLList.URLTmdbPoster + movie.getPosterPath())
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.fitCenter()
					.placeholder(R.drawable.movie_list_placeholder)
					.transition(DrawableTransitionOptions.withCrossFade())
					.into(moviePoster);
//					.into(new CustomTarget<Bitmap>() {
//						@Override
//						public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//							moviePoster.setImageBitmap(resource);
//						}
//
//						@Override
//						public void onLoadCleared(@Nullable Drawable placeholder) {
//
//						}
//					});
		}
	}

	public TmdbMovieAdapter(OnMoviePosterClickListener listener) {
//		mDataset = new ArrayList<>(Collections.nCopies(20,
//				new TmdbMovieDetailed(0.0, "", "", false, "", 0, false, "", "", "", "", "", "", "", new ArrayList<>(), 0)));
		mDataset = new ArrayList<>();
		mListener = listener;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.movie_list_layout, parent, false);
		return new TmdbMovieHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		((TmdbMovieHolder) holder).setText(mDataset.get(position));
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
		Log.v(TAG, "Received update dataset");
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
