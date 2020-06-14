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
import com.stefankrstikj.skopjemovieschedule.models.TmdbCast;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieCastAdapter extends RecyclerView.Adapter<TmdbMovieCastAdapter.TmdbMovieCastHolder> {
	private static final String TAG = "MovieCastAdapter";

	private List<TmdbCast> mDataset;

	public static class TmdbMovieCastHolder extends RecyclerView.ViewHolder{

		private TextView mName;
		private TextView mCharacter;
		private ImageView mPoster;


		public TmdbMovieCastHolder(@NonNull View itemView) {
			super(itemView);

			mName = itemView.findViewById(R.id.textView_cast_name);
			mCharacter = itemView.findViewById(R.id.textView_cast_character);
			mPoster = itemView.findViewById(R.id.imageView_cast_poster);
		}

		void setText(TmdbCast tmdbCast){
			this.mName.setText(tmdbCast.getName());
			this.mCharacter.setText(tmdbCast.getCharacter());
			Picasso.get()
					.load(URLList.URLTmdbCastProfilePath + tmdbCast.getProfilePath())
					.placeholder(R.drawable.profile_picture_template)
					.into(mPoster);
		}
	}

	public TmdbMovieCastAdapter() {
		this.mDataset = new ArrayList<>();
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
		holder.setText(mDataset.get(position));

	}


	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public void updateDataset(List<TmdbCast> dataset){
		this.mDataset = dataset;
		notifyDataSetChanged();
	}
}
