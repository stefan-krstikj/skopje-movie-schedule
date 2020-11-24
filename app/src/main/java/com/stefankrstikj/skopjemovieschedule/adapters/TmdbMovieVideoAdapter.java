package com.stefankrstikj.skopjemovieschedule.adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieVideoAdapter extends RecyclerView.Adapter<TmdbMovieVideoAdapter.TmdbMovieVideoHolder> {
	private static String TAG = "TmdbMovieVideoAdapter";
	private List<TmdbMovieVideo> mDataset;

	public static class TmdbMovieVideoHolder extends RecyclerView.ViewHolder{
		ImageView mThumbnail;
		TextView mMovieType;
		TextView mMovieName;

		public TmdbMovieVideoHolder(@NonNull View itemView) {
			super(itemView);

			mMovieType = itemView.findViewById(R.id.textView_movie_video_type);
			mMovieName = itemView.findViewById(R.id.textView_movie_video_name);
			mThumbnail = itemView.findViewById(R.id.imageView_movie_video_thumbnail);

		}

		void setText(TmdbMovieVideo tmdbMovieVideo){
			mMovieName.setText(tmdbMovieVideo.getName());
			mMovieType.setText(tmdbMovieVideo.getType());

			String videoURL = URLList.URLYouTubeVideo + tmdbMovieVideo.getKey();
			mThumbnail.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videoURL))));

			String url = URLList.URLYouTubeThumbnailBaseUrl + tmdbMovieVideo.getKey() + URLList.URLYouTubeThumbnailSuffix;
			Glide.
					with(itemView)
					.load(url)
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.into(mThumbnail);
		}
	}

	public TmdbMovieVideoAdapter() {
		mDataset = new ArrayList<>();
	}

	@NonNull
	@Override
	public TmdbMovieVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_layout_video, parent, false);
		return new TmdbMovieVideoHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull TmdbMovieVideoHolder holder, int position) {
		holder.setText(mDataset.get(position));
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void updateDataset(List<TmdbMovieVideo> newDataset){
		mDataset = newDataset;
		notifyDataSetChanged();
	}
}
