package com.stefankrstikj.skopjemovieschedule.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieReview;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieReviewAdapter extends RecyclerView.Adapter<TmdbMovieReviewAdapter.TmdbMovieReviewHolder> {

	private static final String TAG = "TmdbMovieReviewAdapter";
	private List<TmdbMovieReview> mDataset;

	public TmdbMovieReviewAdapter() {
		this.mDataset = new ArrayList<>();
	}

	public static class TmdbMovieReviewHolder extends RecyclerView.ViewHolder{
		private TextView mAuthor;
		private ReadMoreTextView mContent;

		public TmdbMovieReviewHolder(@NonNull View itemView) {
			super(itemView);
			mAuthor = itemView.findViewById(R.id.textView_review_author);
			mContent = itemView.findViewById(R.id.readMoreTextView_review_content);
		}

		void setText(TmdbMovieReview tmdbMovieReview){
			mAuthor.setText(tmdbMovieReview.getAuthor());
			mContent.setText(tmdbMovieReview.getContent());
		}
	}


	@NonNull
	@Override
	public TmdbMovieReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_layout_reviews, parent, false);
		return new TmdbMovieReviewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull TmdbMovieReviewHolder holder, int position) {
		holder.setText(mDataset.get(position));
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void updateDataset(List<TmdbMovieReview> dataset){
		this.mDataset = dataset;
		notifyDataSetChanged();
	}
}
