package mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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
        int spacing = 8;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing*2);
            }

        });
        adapter = new MovieScheduleAdapter();
        recyclerView.setAdapter(adapter);

    }


    private void initData(){
        textViewTitle.setText(mMovie.getMovieTitle());
        imageViewPoster.setImageBitmap(mMoviePoster);
        textViewYear.setText(mMovie.getYear());
        textViewPlot.setText(mMovie.getPlot());
        textViewGenre.setText(mMovie.getGenre());
        textViewDirector.setText(mMovie.getDirector());
        textViewActors.setText(mMovie.getActors());
        textViewRuntime.setText(mMovie.getRuntime());
    }

    private void initViewModel(){
        DetailMovieViewModelFactory factory = InjectorUtils.provideDetailMovieViewFactory(this);
        detailMovieViewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel.class);
        Log.v(TAG, "Getting movie schedules for: " + mMovie.getMovieTitle());
        detailMovieViewModel.getMovieScheduleForMovie(mMovie.getMovieTitle())
                .observe(this, movieSchedules -> adapter.updateDataset(movieSchedules));
    }
}
