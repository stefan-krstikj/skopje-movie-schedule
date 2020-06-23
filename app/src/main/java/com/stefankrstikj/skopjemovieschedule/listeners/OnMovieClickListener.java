package com.stefankrstikj.skopjemovieschedule.listeners;

import android.widget.ImageView;

public interface OnMovieClickListener<T> {
    void onMovieClick(T t, ImageView imageView, Integer position);

}
