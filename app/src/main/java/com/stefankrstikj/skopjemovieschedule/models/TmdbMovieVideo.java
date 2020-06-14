package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "tmdb_movie_videos",
		foreignKeys = {
				@ForeignKey(entity = TmdbMovieDetailed.class,
						parentColumns = {"id", "result_type"},
						childColumns = {"movie_id", "movie_result_type"},
						onDelete = ForeignKey.CASCADE)},
		indices = {
				@Index(name = "index_movie_videos_video_id", value = {"movie_id"})
		})
public class TmdbMovieVideo {

	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private String mId;

	@ColumnInfo(name = "iso_639_1")
	@SerializedName("iso_630_1")
	private String mIso_639_1;

	@ColumnInfo(name = "iso_3166_1")
	@SerializedName("iso_3166_1")
	private String mIso_3166_1;

	@ColumnInfo(name = "key")
	@SerializedName("key")
	private String mKey;

	@ColumnInfo(name = "name")
	@SerializedName("name")
	private String mName;

	@ColumnInfo(name = "site")
	@SerializedName("site")
	private String mSite;

	@ColumnInfo(name = "size")
	@SerializedName("size")
	private Integer mSize;

	@ColumnInfo(name = "type")
	@SerializedName("type")
	private String mType;

	@ColumnInfo(name ="movie_id")
	@SerializedName("movie_id")
	private Integer mMovieId;

	@ColumnInfo(name = "movie_result_type")
	private String mMovieResultType;

	public TmdbMovieVideo(@NotNull String id, String iso_639_1, String iso_3166_1, String key, String name, String site, Integer size, String type, Integer movieId, String movieResultType) {
		mId = id;
		mIso_639_1 = iso_639_1;
		mIso_3166_1 = iso_3166_1;
		mKey = key;
		mName = name;
		mSite = site;
		mSize = size;
		mType = type;
		mMovieId = movieId;
		mMovieResultType = movieResultType;
	}

	@NotNull
	public String getId() {
		return mId;
	}

	public void setId(@NotNull String id) {
		mId = id;
	}

	public String getIso_639_1() {
		return mIso_639_1;
	}

	public void setIso_639_1(String iso_639_1) {
		mIso_639_1 = iso_639_1;
	}

	public String getIso_3166_1() {
		return mIso_3166_1;
	}

	public void setIso_3166_1(String iso_3166_1) {
		mIso_3166_1 = iso_3166_1;
	}

	public String getKey() {
		return mKey;
	}

	public void setKey(String key) {
		mKey = key;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getSite() {
		return mSite;
	}

	public void setSite(String site) {
		mSite = site;
	}

	public Integer getSize() {
		return mSize;
	}

	public void setSize(Integer size) {
		mSize = size;
	}

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		mType = type;
	}

	public Integer getMovieId() {
		return mMovieId;
	}

	public void setMovieId(Integer movieId) {
		mMovieId = movieId;
	}

	public String getMovieResultType() {
		return mMovieResultType;
	}

	public void setMovieResultType(String movieResultType) {
		mMovieResultType = movieResultType;
	}

	@NonNull
	@Override
	public String toString() {
		return getName();
	}
}
