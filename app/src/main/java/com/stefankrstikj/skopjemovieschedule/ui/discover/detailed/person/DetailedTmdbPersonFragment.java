package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.person;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieCast;
import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;

public class DetailedTmdbPersonFragment extends Fragment {
	private static final String TAG = "DetailedTmdbPersonFragment";

	private DetailedTmdbPersonViewModel mDetailedTmdbPersonViewModel;
	private TmdbPerson mTmdbPerson;
	private Bitmap mProfileBitmap;
	private ImageView mProfileImage;
	private TextView mName;
	private Integer mPersonId;


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.activity_detailed_tmdb_movie, container, false);
		return root;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViewModel();
		unwrapBundle();
		initViews();
		initData();

	}

	void initViewModel(){
		DetailedTmdbPersonViewModelFactory factory = InjectorUtils.provideDetailedTmdbPersonViewModelFactory(getContext());
		mDetailedTmdbPersonViewModel = ViewModelProviders.of(getActivity(), factory).get(DetailedTmdbPersonViewModel.class);
	}

	void unwrapBundle(){
		Bundle bundle = this.getArguments();
		if (bundle != null) {
			TmdbMovieCast tmdbPerson = (TmdbMovieCast) bundle.getSerializable("cast");
			Log.v(TAG, "RECEIVED: " + tmdbPerson);
			mProfileBitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("byteArray"), 0, bundle.getByteArray("byteArray").length);

			mPersonId = tmdbPerson.getId();
			mDetailedTmdbPersonViewModel.fetchPersonForId(mPersonId);
		}
	}


	void initViews(){
		mProfileImage = getView().findViewById(R.id.imageView_details_movie);
		mName = getView().findViewById(R.id.textView_title_movie);
	}

	void initData(){

		mDetailedTmdbPersonViewModel.getPersonForId(mPersonId).observe(getViewLifecycleOwner(), data -> {
			mTmdbPerson = data;
			setDetails();
		});
	}

	void setDetails(){
		mName.setText(mTmdbPerson.getName());
		mProfileImage.setImageBitmap(mProfileBitmap);
	}

}
