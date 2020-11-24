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
import com.stefankrstikj.skopjemovieschedule.listeners.OnCastClickListener;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieCastAdapter extends RecyclerView.Adapter<TmdbMovieCastAdapter.TmdbMovieCastHolder> {
	private static final String TAG = "MovieCastAdapter";
	private OnCastClickListener mOnCastClickListener;
	private List<TmdbMovieCast> mDataset;

	public class TmdbMovieCastHolder extends RecyclerView.ViewHolder{

		private TextView mName;
		private TextView mCharacter;
		private ImageView mPoster;


		public TmdbMovieCastHolder(@NonNull View itemView) {
			super(itemView);

			mName = itemView.findViewById(R.id.textView_cast_name);
			mCharacter = itemView.findViewById(R.id.textView_cast_character);
			mPoster = itemView.findViewById(R.id.imageView_cast_poster);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					TmdbMovieCast tmdbMovieCast = mDataset.get(getAdapterPosition());
					Log.v(TAG, "Clicked " + tmdbMovieCast.getName());
					mOnCastClickListener.onCastClick(tmdbMovieCast, mPoster, getAdapterPosition());

				}
			});
		}

		void setText(TmdbMovieCast tmdbMovieCast){
			this.mName.setText(tmdbMovieCast.getName());
			this.mCharacter.setText(tmdbMovieCast.getCharacter());
			Picasso.get()
					.load(URLList.URLTmdbCastProfilePath + tmdbMovieCast.getProfilePath())
					.placeholder(R.drawable.profile_picture_template)
					.into(mPoster);
		}
	}

	public TmdbMovieCastAdapter(OnCastClickListener onCastClickListener) {
		mOnCastClickListener = onCastClickListener;
		mDataset = new ArrayList<>();
	}

	@NonNull
	@Override
	public TmdbMovieCastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v  = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_layout_cast, parent, false);
		return new TmdbMovieCastHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull TmdbMovieCastHolder holder, int position) {
		holder.mPoster.setTransitionName(holder.mPoster.getTransitionName() + position);
		holder.setText(mDataset.get(position));
	}


	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void updateDataset(List<TmdbMovieCast> dataset){
		this.mDataset = dataset;
		notifyDataSetChanged();
	}
}
