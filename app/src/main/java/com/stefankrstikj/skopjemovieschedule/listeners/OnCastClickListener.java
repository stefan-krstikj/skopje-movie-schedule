package com.stefankrstikj.skopjemovieschedule.listeners;

import android.widget.ImageView;

public interface OnCastClickListener<T> {
	void onCastClick(T t, ImageView imageView, Integer position);
}
