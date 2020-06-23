package com.stefankrstikj.skopjemovieschedule.adapters;

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
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieListAdapter extends RecyclerView.Adapter<TmdbMovieListAdapter.TmdbMovieListHolder> {

	private OnClickListener mOnClickListener;
	private List<TmdbMovieDetailed> mDataset;


	public TmdbMovieListAdapter(OnClickListener onClickListener) {
		mOnClickListener = onClickListener;
		mDataset = new ArrayList<>();
	}

	public class TmdbMovieListHolder extends RecyclerView.ViewHolder{
		private ImageView mMoviePoster;
		private TextView mTitle;
		private TextView mYear;
		private TextView mGenres;

		public TmdbMovieListHolder(@NonNull View itemView) {
			super(itemView);
			mMoviePoster = itemView.findViewById(R.id.imageView_movie_list_list_movie_poster);
			mTitle = itemView.findViewById(R.id.textView_movie_list_list_title);
			mYear = itemView.findViewById(R.id.textView_movie_list_list_year);
			mGenres = itemView.findViewById(R.id.textView_movie_list_list_genres);
			itemView.setOnClickListener(v -> mOnClickListener.onClick(mDataset.get(getAdapterPosition()), mMoviePoster, getAdapterPosition()));
		}

		void setText(TmdbMovieDetailed tmdbMovieDetailed){
			mTitle.setText(tmdbMovieDetailed.getTitle());
			mYear.setText(MovieUtils.getDisplayYear(tmdbMovieDetailed));
			mGenres.setText(MovieUtils.getGenres(tmdbMovieDetailed));
			Glide
					.with(itemView.getContext())
					.load(URLList.URLTmdbPoster + tmdbMovieDetailed.getPosterPath())
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.fitCenter()
					.placeholder(R.drawable.movie_list_placeholder)
					.transition(DrawableTransitionOptions.withCrossFade())
					.into(mMoviePoster);
		}
	}

	@NonNull
	@Override
	public TmdbMovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_layout_movie_list, parent, false);
		return new TmdbMovieListHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull TmdbMovieListHolder holder, int position) {
		holder.setText(mDataset.get(position));
	}

	public void updateDataset(List<TmdbMovieDetailed> newDataset){
		mDataset = newDataset;
		notifyDataSetChanged();
	}
	@Override
	public int getItemCount() {
		return mDataset.size();
	}
}
