package com.stefankrstikj.skopjemovieschedule.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "tmdb_movie_review")
public class TmdbMovieReview {
	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private String mId;

	@ColumnInfo(name="author")
	@SerializedName("author")
	private String mAuthor;

	@ColumnInfo(name = "content")
	@SerializedName("content")
	private String mContent;

	@ColumnInfo(name = "url")
	@SerializedName("url")
	private String mUrl;

	@ColumnInfo(name = "movie_id")
	private Integer movieId;

	public TmdbMovieReview(@NotNull String id, String author, String content, String url, Integer movieId) {
		mId = id;
		mAuthor = author;
		mContent = content;
		mUrl = url;
		this.movieId = movieId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	@NotNull
	public String getId() {
		return mId;
	}

	public void setId(@NotNull String id) {
		mId = id;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public void setAuthor(String author) {
		mAuthor = author;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		mContent = content;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}
}
