package com.stefankrstikj.skopjemovieschedule.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "tmdb_movie_recommendation")
public class TmdbMovieRecommendation {
	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "source_movie_id")
	private Integer mSourceMovieId;

	@ColumnInfo(name = "movie_id")
	private Integer mMovieId;

	public TmdbMovieRecommendation(Integer sourceMovieId, Integer movieId) {
		mSourceMovieId = sourceMovieId;
		mMovieId = movieId;
	}

	public Integer getSourceMovieId() {
		return mSourceMovieId;
	}

	public void setSourceMovieId(Integer sourceMovieId) {
		mSourceMovieId = sourceMovieId;
	}

	public Integer getMovieId() {
		return mMovieId;
	}

	public void setMovieId(Integer movieId) {
		mMovieId = movieId;
	}
}
