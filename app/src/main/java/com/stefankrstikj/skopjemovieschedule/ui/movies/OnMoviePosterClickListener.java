package com.stefankrstikj.skopjemovieschedule.ui.movies;

import android.widget.ImageView;

import com.stefankrstikj.skopjemovieschedule.models.Movie;

public interface OnMoviePosterClickListener<T> {
    void onMovieClick(T t, ImageView imageView);
}
