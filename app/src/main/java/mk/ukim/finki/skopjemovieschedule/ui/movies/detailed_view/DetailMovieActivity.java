package mk.ukim.finki.skopjemovieschedule.ui.movies.detailed_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.adapters.MovieScheduleAdapter;
import mk.ukim.finki.skopjemovieschedule.data.Movie;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        this.mMovie = (Movie) getIntent().getSerializableExtra("movie");
        this.mMoviePoster = (Bitmap)this.getIntent().getParcelableExtra("image");


        initViews();
        initViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initViews(){
        imageViewPoster = findViewById(R.id.imageView_details_movie);
        textViewTitle = findViewById(R.id.textView_title_movie);
        textViewYear = findViewById(R.id.textView_year);
        textViewPlot = findViewById(R.id.textView_plot);
        textViewGenre = findViewById(R.id.textView_genre);


        RecyclerView recyclerView = findViewById(R.id.recyclerView_movie_schedules);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieScheduleAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initSchedule(List<MovieSchedule> movieSchedules){
//        Log.v(TAG, "initSchedule() received: " + movieSchedules.toString());

    }

    private void initData(){
        textViewTitle.setText(mMovie.mMovieTitle);
        imageViewPoster.setImageBitmap(mMoviePoster);
        textViewYear.setText(mMovie.mYear);
        textViewPlot.setText(mMovie.mPlot);
        textViewGenre.setText(mMovie.mGenre);
    }

    private void initViewModel(){
        DetailMovieViewModelFactory factory = InjectorUtils.provideDetailMovieViewFactory(this);
        detailMovieViewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel.class);
        Log.v(TAG, "Getting movie schedules for: " + mMovie.mMovieTitle);
        detailMovieViewModel.getMovieScheduleForMovie(mMovie.mMovieTitle)
                .observe(this, new Observer<List<MovieSchedule>>() {
                    @Override
                    public void onChanged(List<MovieSchedule> movieSchedules) {
                        if(movieSchedules != null || movieSchedules.size() <= 0){
                            adapter.updateDataset(movieSchedules);
                        }
                    }
                });
    }
}
