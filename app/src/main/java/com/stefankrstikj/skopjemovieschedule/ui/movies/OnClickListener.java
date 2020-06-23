package com.stefankrstikj.skopjemovieschedule.ui.movies;

import android.widget.ImageView;

public interface OnClickListener<T> {
    void onClick(T t, ImageView imageView, Integer position);

}
