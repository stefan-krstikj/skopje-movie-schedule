package com.stefankrstikj.skopjemovieschedule.utils;

import com.stefankrstikj.skopjemovieschedule.models.Movie;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;

public class MovieUtils {
	static final int MAX_CHARACTERS = 22;
	public static final int POSTER_WIDTH = 272;
	public static final int POSTER_HEIGHT = 403;

	public static String getDisplayTitle(Movie movie){
		String title = movie.getMovieTitle();
		if(movie.getMovieTitle().length() >= MAX_CHARACTERS){
			title = movie.getMovieTitle().substring(0, MAX_CHARACTERS-3) + "...";
		}
		return title;
	}

	public static String getDisplayTitle(TmdbMovieDetailed movie){
		String title = movie.getTitle();
		if(movie.getTitle().length() >= MAX_CHARACTERS){
			title = movie.getTitle().substring(0, MAX_CHARACTERS-3) + "...";
		}
		return title;
	}
}
