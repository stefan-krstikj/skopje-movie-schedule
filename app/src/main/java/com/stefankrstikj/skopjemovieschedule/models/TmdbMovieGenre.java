package com.stefankrstikj.skopjemovieschedule.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "tmdb_genre")
public class TmdbMovieGenre implements Serializable {
	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private Integer mId;

	@ColumnInfo(name = "name")
	@SerializedName("name")
	private String mName;

	public TmdbMovieGenre(@NotNull Integer id, String name) {
		mId = id;
		mName = name;
	}

	@NotNull
	public Integer getId() {
		return mId;
	}

	public void setId(@NotNull Integer id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	@Override
	public String toString() {
		return mName;
	}
}
