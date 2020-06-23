package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.movie.tablayout.DetailsPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.ui.movies.OnClickListener;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class DetailedTmdbMovieFragment extends Fragment implements OnClickListener {
	private static String TAG = "DetailedTmdbMovie";

	DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;

	private TmdbMovieDetailed mTmdbMovieDetailed;

	private NestedScrollView mNestedScrollView;
	private CollapsingToolbarLayout mCollapsingToolbarLayout;

	private Bitmap mMoviePoster;

	private ImageView imageViewPoster;
	private TextView textViewTitle;
	private TextView textViewGenre;
	private TextView textViewYear;
	private TextView textViewPlot;
	private TextView textViewRating;
	private TextView textViewDirector;
	private TextView textViewActors;
	private TextView textViewRuntime;
	private TextView textViewMovieSchedule;
	private ImageView imageViewbackdropPath;

	private ViewPager viewPager;
	private BottomNavigationView mNavView;


//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// todo: Refactor using data-binding in XML layout
////		ActivityDetailedTmdbMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_tmdb_movie);
////		binding.setTmdbMovie(mTmdbMovieDetailed);
//
//		setContentView(R.layout.activity_detailed_tmdb_movie);
//
//		mTmdbMovieDetailed = (TmdbMovieDetailed) getIntent().getSerializableExtra("movie");
//		mMoviePoster = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
//
//		initToolbar();
//		initNestedScrollView();
//		initViewModel();
//		initViews();
//		initData();
//		initPagerAdapter();
//	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fragment_image_transition));
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.activity_detailed_tmdb_movie, container, false);


		return root;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//
//		Bundle bundle = this.getArguments();
//		mTmdbMovieDetailed = (TmdbMovieDetailed) bundle.getSerializable("movie");
//		mMoviePoster = BitmapFactory.decodeByteArray(bundle.getByteArray("byteArray"), 0, bundle.getByteArray("byteArray").length);

		initToolbar();
		initNestedScrollView();
		initViews();
		Bundle b = getArguments();
		if (b != null) {
			String transitionName = b.getString("transitionName");
			TmdbMovieDetailed movie = (TmdbMovieDetailed) b.getSerializable("movie");
			mTmdbMovieDetailed = movie;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				imageViewPoster.setTransitionName(transitionName);
			}

			if (movie != null) {
				Glide.with(getActivity())
						.load(URLList.URLTmdbPoster + movie.getPosterPath())
						.into(imageViewPoster);

			}
		}


		initViewModel();

		initData();
		initPagerAdapter();
	}

//	private void initToolbar() {
//		androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.detailed_tmdb_movie_toolbar);
//		setSupportActionBar(myToolbar);
//		Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//	}

	private void initToolbar() {
		androidx.appcompat.widget.Toolbar myToolbar = getView().findViewById(R.id.detailed_tmdb_movie_toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
		Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
	}

//	private void initNestedScrollView() {
//		this.mNestedScrollView = findViewById(R.id.detailed_tmdb_movie_scrollview);
//		mCollapsingToolbarLayout = findViewById(R.id.detailed_tmdb_movie_toolbarlayout);
//
//		AppBarLayout appBarLayout = findViewById(R.id.detailed_tmdb_movie_appbar);
//		appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//			@Override
//			public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//				if (Math.abs(i) == appBarLayout.getTotalScrollRange()) {
//					appBarLayout.setActivated(true);
//					mCollapsingToolbarLayout.setTitleEnabled(true);
//				} else {
//					appBarLayout.setActivated(false);
//					mCollapsingToolbarLayout.setTitleEnabled(false);
//				}
//			}
//		});
//	}

	private void initNestedScrollView() {
		this.mNestedScrollView = getView().findViewById(R.id.detailed_tmdb_movie_scrollview);
		mCollapsingToolbarLayout = getView().findViewById(R.id.detailed_tmdb_movie_toolbarlayout);

		AppBarLayout appBarLayout = getView().findViewById(R.id.detailed_tmdb_movie_appbar);
		appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
				if (Math.abs(i) == appBarLayout.getTotalScrollRange()) {
					appBarLayout.setActivated(true);
					mCollapsingToolbarLayout.setTitleEnabled(true);
				} else {
					appBarLayout.setActivated(false);
					mCollapsingToolbarLayout.setTitleEnabled(false);
				}
			}
		});
	}


//	private void initViews() {
//		imageViewPoster = findViewById(R.id.imageView_details_movie);
//		textViewTitle = findViewById(R.id.textView_title_movie);
//		textViewYear = findViewById(R.id.textView_year);
//		textViewPlot = findViewById(R.id.textView_plot);
//		textViewGenre = findViewById(R.id.textView_genre);
//		textViewDirector = findViewById(R.id.textView_Director);
//		textViewActors = findViewById(R.id.textView_Actors);
//		textViewRuntime = findViewById(R.id.textView_runtime);
//		textViewMovieSchedule = findViewById(R.id.textView_movie_schedule_text);
//	}

	private void initViews() {
		imageViewPoster = getView().findViewById(R.id.imageView_details_movie);
		textViewTitle = getView().findViewById(R.id.textView_title_movie);
		textViewYear = getView().findViewById(R.id.textView_year);
		textViewPlot = getView().findViewById(R.id.textView_plot);
		textViewGenre = getView().findViewById(R.id.textView_genre);
		textViewDirector = getView().findViewById(R.id.textView_Director);
		textViewActors = getView().findViewById(R.id.textView_Actors);
		textViewRuntime = getView().findViewById(R.id.textView_runtime);
		textViewMovieSchedule = getView().findViewById(R.id.textView_movie_schedule_text);
	}

	private void initViewModel() {
		DetailedTmdbMovieViewModelFactory factory = InjectorUtils.provideDetailedTmdbMovieViewModelFactory(getActivity(), mTmdbMovieDetailed);
		mDetailedTmdbMovieViewModel = ViewModelProviders.of(this, factory).get(DetailedTmdbMovieViewModel.class);
	}

	private void initData() {
		mCollapsingToolbarLayout.setTitle(mTmdbMovieDetailed.getTitle());
		mNavView = getActivity().findViewById(R.id.nav_view);

		textViewTitle.setText(mTmdbMovieDetailed.getTitle());
		imageViewPoster.setImageBitmap(mMoviePoster);
		textViewRuntime.setText(mTmdbMovieDetailed.getRuntime() + " min");
		textViewYear.setText(mTmdbMovieDetailed.getReleaseDate().substring(0, 4));
		textViewPlot.setText(mTmdbMovieDetailed.getOverview());
		textViewGenre.setText(MovieUtils.getGenres(mTmdbMovieDetailed));

	}

	private void initPagerAdapter() {
		DetailsPagerAdapter sectionsPagerAdapter = new DetailsPagerAdapter(mDetailedTmdbMovieViewModel, getActivity(), getActivity().getSupportFragmentManager(), this);
		viewPager = getView().findViewById(R.id.detailed_tmdb_view_pager);
		viewPager.setAdapter(sectionsPagerAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		mNavView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onPause() {
		super.onPause();
		mNavView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(Object o, ImageView imageView, Integer position) {
		TmdbMovieDetailed movie = (TmdbMovieDetailed) o;

		imageView.buildDrawingCache();
		Bitmap bitmap = imageView.getDrawingCache();

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);

		Bundle bundle = new Bundle();
		bundle.putByteArray("byteArray", bs.toByteArray());
		bundle.putSerializable("movie", movie);
		bundle.putSerializable("transitionName", "imageMain" + position);
		Fragment fragment = new DetailedTmdbMovieFragment();
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getChildFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.coordinatorLayout_detailed_tmdb_movie, fragment);
		fragmentTransaction.addToBackStack(TAG);
		fragmentTransaction.addSharedElement(imageView, "transition" + position);
		fragmentTransaction.commit();
	}
}
