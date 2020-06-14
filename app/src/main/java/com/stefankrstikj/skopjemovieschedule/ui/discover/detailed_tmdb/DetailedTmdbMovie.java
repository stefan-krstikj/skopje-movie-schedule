package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout.DetailsPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;
import com.stefankrstikj.skopjemovieschedule.utils.MovieUtils;

import java.util.Objects;

public class DetailedTmdbMovie extends AppCompatActivity {
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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// todo: Refactor using data-binding in XML layout
//		ActivityDetailedTmdbMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_tmdb_movie);
//		binding.setTmdbMovie(mTmdbMovieDetailed);

		setContentView(R.layout.activity_detailed_tmdb_movie);

		this.mTmdbMovieDetailed = (TmdbMovieDetailed) getIntent().getSerializableExtra("movie");
		mMoviePoster = BitmapFactory.decodeByteArray(
				getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);



		initToolbar();
		initNestedScrollView();
		initViewModel();
		initViews();
		initData();
		initPagerAdapter();
	}

	private void initToolbar(){
		androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.detailed_tmdb_movie_toolbar);
		setSupportActionBar(myToolbar);
		Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
	}

	private void initNestedScrollView() {
		this.mNestedScrollView = findViewById(R.id.detailed_tmdb_movie_scrollview);
		mCollapsingToolbarLayout = findViewById(R.id.detailed_tmdb_movie_toolbarlayout);

		AppBarLayout appBarLayout = findViewById(R.id.detailed_tmdb_movie_appbar);
		appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
				if (Math.abs(i) == appBarLayout.getTotalScrollRange()) {
					appBarLayout.setActivated(true);
					mCollapsingToolbarLayout.setTitleEnabled(true);
				}else{
					appBarLayout.setActivated(false);
					mCollapsingToolbarLayout.setTitleEnabled(false);
				}
			}
		});
	}


	private void initViews() {
		imageViewPoster = findViewById(R.id.imageView_details_movie);
		textViewTitle = findViewById(R.id.textView_title_movie);
		textViewYear = findViewById(R.id.textView_year);
		textViewPlot = findViewById(R.id.textView_plot);
		textViewGenre = findViewById(R.id.textView_genre);
		textViewDirector = findViewById(R.id.textView_Director);
		textViewActors = findViewById(R.id.textView_Actors);
		textViewRuntime = findViewById(R.id.textView_runtime);
		textViewMovieSchedule = findViewById(R.id.textView_movie_schedule_text);
	}

	private void initViewModel() {
		DetailedTmdbMovieViewModelFactory factory = InjectorUtils.provideDetailedTmdbMovieViewModelFactory(this, mTmdbMovieDetailed);
		mDetailedTmdbMovieViewModel = ViewModelProviders.of(this, factory).get(DetailedTmdbMovieViewModel.class);
	}

	private void initData() {
		mCollapsingToolbarLayout.setTitle(mTmdbMovieDetailed.getTitle());

		textViewTitle.setText(mTmdbMovieDetailed.getTitle());
		imageViewPoster.setImageBitmap(mMoviePoster);
		textViewRuntime.setText(mTmdbMovieDetailed.getRuntime() + " min");
		textViewYear.setText(mTmdbMovieDetailed.getReleaseDate().substring(0, 4));
		textViewPlot.setText(mTmdbMovieDetailed.getOverview());
		textViewGenre.setText(MovieUtils.getGenres(mTmdbMovieDetailed));

	}

	private void initPagerAdapter(){
		DetailsPagerAdapter sectionsPagerAdapter = new DetailsPagerAdapter(mDetailedTmdbMovieViewModel, this, getSupportFragmentManager());
		viewPager = findViewById(R.id.detailed_tmdb_view_pager);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
}
