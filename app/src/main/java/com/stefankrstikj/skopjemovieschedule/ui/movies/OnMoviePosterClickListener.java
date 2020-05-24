package com.stefankrstikj.skopjemovieschedule.ui.movies;

import android.widget.ImageView;

import com.stefankrstikj.skopjemovieschedule.models.Movie;

public interface OnMoviePosterClickListener {
    void onMovieClick(Movie movie, ImageView imageView);
}
