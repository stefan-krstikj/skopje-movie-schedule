package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "tmdb_movie_cast",
		foreignKeys = {
				@ForeignKey(entity = TmdbMovieDetailed.class,
						parentColumns = {"id", "result_type"},
						childColumns = {"movie_id", "movie_result_type"},
						onDelete = ForeignKey.CASCADE)
		},
		indices = {
				@Index(name = "index_cast_movie_id", value = {"movie_id"}),
				@Index(name = "index_cast_movie_result_type", value = {"movie_result_type"})
		})
public class TmdbMovieCast {
	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private Integer mId;

	@ColumnInfo(name = "movie_id")
	@SerializedName("movie_id")
	private Integer mMovieId;

	@ColumnInfo(name = "movie_result_type")
	private String mMovieResultType;

	@ColumnInfo(name = "cast_id")
	@SerializedName("cast_id")
	private Integer mCastId;

	@ColumnInfo(name = "character")
	@SerializedName("character")
	private String mCharacter;

	@ColumnInfo(name = "credit_id")
	@SerializedName("credit_id")
	private String mCreditId;

	@ColumnInfo(name = "gender")
	@SerializedName("gender")
	private Integer mGender;

	@ColumnInfo(name = "name")
	@SerializedName("name")
	private String mName;

	@ColumnInfo(name = "order")
	@SerializedName("order")
	private Integer mOrder;

	@ColumnInfo(name = "profile_path")
	@SerializedName("profile_path")
	private String mProfilePath;

	public TmdbMovieCast(@NotNull Integer id, Integer movieId, String movieResultType, Integer castId, String character, String creditId, Integer gender, String name, Integer order, String profilePath) {
		mId = id;
		mMovieId = movieId;
		mMovieResultType = movieResultType;
		mCastId = castId;
		mCharacter = character;
		mCreditId = creditId;
		mGender = gender;
		mName = name;
		mOrder = order;
		mProfilePath = profilePath;
	}


	public String getMovieResultType() {
		return mMovieResultType;
	}

	public void setMovieResultType(String movieResultType) {
		mMovieResultType = movieResultType;
	}

	@NotNull
	public Integer getId() {
		return mId;
	}

	public void setId(@NotNull Integer id) {
		mId = id;
	}

	public Integer getMovieId() {
		return mMovieId;
	}

	public void setMovieId(Integer movieId) {
		mMovieId = movieId;
	}

	public Integer getCastId() {
		return mCastId;
	}

	public void setCastId(Integer castId) {
		mCastId = castId;
	}

	public String getCharacter() {
		return mCharacter;
	}

	public void setCharacter(String character) {
		mCharacter = character;
	}

	public String getCreditId() {
		return mCreditId;
	}

	public void setCreditId(String creditId) {
		mCreditId = creditId;
	}

	public Integer getGender() {
		return mGender;
	}

	public void setGender(Integer gender) {
		mGender = gender;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public Integer getOrder() {
		return mOrder;
	}

	public void setOrder(Integer order) {
		mOrder = order;
	}

	public String getProfilePath() {
		return mProfilePath;
	}

	public void setProfilePath(String profilePath) {
		mProfilePath = profilePath;
	}

	@NonNull
	@Override
	public String toString() {
		return this.getName();
	}
}
