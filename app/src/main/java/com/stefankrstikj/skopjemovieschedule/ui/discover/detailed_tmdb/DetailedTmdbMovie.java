package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.ui.discover.detailed_tmdb.tablayout.DetailsPagerAdapter;
import com.stefankrstikj.skopjemovieschedule.utils.InjectorUtils;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

public class DetailedTmdbMovie extends AppCompatActivity {
	private static String TAG = "DetailedTmdbMovie";

	DetailedTmdbMovieViewModel mDetailedTmdbMovieViewModel;

	private TmdbMovieDetailed mTmdbMovieDetailed;

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

	private DetailsPagerAdapter mDetailsPagerAdapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_tmdb_movie);

		this.mTmdbMovieDetailed = (TmdbMovieDetailed) getIntent().getSerializableExtra("movie");
		this.mMoviePoster = this.getIntent().getParcelableExtra("image");

		DetailsPagerAdapter sectionsPagerAdapter = new DetailsPagerAdapter(this, getSupportFragmentManager());
		viewPager = findViewById(R.id.detailed_tmdb_view_pager);
		viewPager.setAdapter(sectionsPagerAdapter);
//		TabLayout tabs = findViewById(R.id.tabs);
//		tabs.setupWithViewPager(viewPager);

		initViews();
		initViewModel();
		initData();
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
		imageViewbackdropPath = findViewById(R.id.imageView_backdrop_path);
	}

	private void initViewModel() {
		DetailedTmdbMovieViewModelFactory factory = InjectorUtils.provideDetailedTmdbMovieViewModelFactory(this);
		mDetailedTmdbMovieViewModel = ViewModelProviders.of(this, factory).get(DetailedTmdbMovieViewModel.class);
		mDetailedTmdbMovieViewModel.getTmdbMovieCastForMovie(mTmdbMovieDetailed.getId())
				.observe(this, tmdbMovieCast -> {

				});
	}

	private void initData() {
		textViewTitle.setText(mTmdbMovieDetailed.getTitle());
		imageViewPoster.setImageBitmap(mMoviePoster);
		textViewRuntime.setText(mTmdbMovieDetailed.getRuntime() + " min");
		textViewYear.setText(mTmdbMovieDetailed.getReleaseDate().substring(0, 4));
		textViewPlot.setText(mTmdbMovieDetailed.getOverview());
		textViewGenre.setText(mTmdbMovieDetailed.getGenres().toString().replace("[", "").replace("]", ""));
//		textViewDirector.setText(mTmdbMovieDetailed.getDirector());
//		textViewActors.setText(mTmdbMovieDetailed.getActors());
//		textViewRuntime.setText(mTmdbMovieDetailed.get());
		Picasso.get()
				.load(URLList.URLTmdbBackdrop + mTmdbMovieDetailed.getBackdropPath())
				.into(imageViewbackdropPath);
	}
}
