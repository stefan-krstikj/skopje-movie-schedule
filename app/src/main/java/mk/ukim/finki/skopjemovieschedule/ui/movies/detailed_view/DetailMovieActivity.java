package mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.adapters.MovieScheduleAdapter;
import mk.ukim.finki.skopjemovieschedule.models.Movie;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;
import mk.ukim.finki.skopjemovieschedule.utils.InjectorUtils;

public class DetailMovieActivity extends AppCompatActivity {
    private static String TAG = "DetailMovieActivity";
    private static final int POSTER_WIDTH = 318;
    private static final int POSTER_HEIGHT = 471;

    private DetailMovieViewModel detailMovieViewModel;
    private MovieScheduleAdapter adapter;

    private Movie mMovie;
    private MovieSchedule mMovieSchedule;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        this.mMovie = (Movie) getIntent().getSerializableExtra("movie");
        this.mMoviePoster = (Bitmap)this.getIntent().getParcelableExtra("image");


        initViews();
        initViewModel();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews(){
        imageViewPoster = findViewById(R.id.imageView_details_movie);
        textViewTitle = findViewById(R.id.textView_title_movie);
        textViewYear = findViewById(R.id.textView_year);
        textViewPlot = findViewById(R.id.textView_plot);
        textViewGenre = findViewById(R.id.textView_genre);
        textViewDirector = findViewById(R.id.textView_Director);
        textViewActors = findViewById(R.id.textView_Actors);
        textViewRuntime = findViewById(R.id.textView_runtime);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_movie_schedules);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieScheduleAdapter();
        recyclerView.setAdapter(adapter);
    }


    private void initData(){
        textViewTitle.setText(mMovie.mMovieTitle);
        imageViewPoster.setImageBitmap(mMoviePoster);
        textViewYear.setText(mMovie.mYear);
        textViewPlot.setText(mMovie.mPlot);
        textViewGenre.setText(mMovie.mGenre);
        textViewDirector.setText(mMovie.mDirector);
        textViewActors.setText(mMovie.mActors);
        textViewRuntime.setText(mMovie.mRuntime);
    }

    private void initViewModel(){
        DetailMovieViewModelFactory factory = InjectorUtils.provideDetailMovieViewFactory(this);
        detailMovieViewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel.class);
        Log.v(TAG, "Getting movie schedules for: " + mMovie.mMovieTitle);
        detailMovieViewModel.getMovieScheduleForMovie(mMovie.mMovieTitle)
                .observe(this, movieSchedules -> adapter.updateDataset(movieSchedules));
    }
}
