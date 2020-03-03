package mk.ukim.finki.skopjemovieschedule.ui.movies;

import android.widget.ImageView;

import mk.ukim.finki.skopjemovieschedule.models.Movie;

public interface OnMoviePosterClickListener {
    void onMovieClick(Movie movie, ImageView imageView);
}
