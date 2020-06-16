package com.stefankrstikj.skopjemovieschedule.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "tmdb_persons")
public class TmdbPerson {

	@ColumnInfo(name = "birthday")
	@SerializedName("birthday")
	String mBirthday;

	@ColumnInfo(name = "known_for_department")
	@SerializedName("known_for_department")
	String mKnownForDepartment;

	@ColumnInfo(name = "deathday")
	@SerializedName("deathday")
	String mDeathday;

	@ColumnInfo(name = "id")
	@SerializedName("id")
	@PrimaryKey
	@NotNull
	Integer mId;

	@ColumnInfo(name = "name")
	@SerializedName("name")
	String mName;

	@ColumnInfo(name = "gender")
	@SerializedName("gender")
	String mGender;

	@ColumnInfo(name = "biography")
	@SerializedName("biography")
	String mBiography;

	@ColumnInfo(name = "popularity")
	@SerializedName("popularity")
	String mPopularity;

	@ColumnInfo(name = "place_of_birth")
	@SerializedName("place_of_birth")
	String mPlaceOfBirth;

	@ColumnInfo(name = "adult")
	@SerializedName("adult")
	String mAdult;

	@ColumnInfo(name = "imdb_id")
	@SerializedName("imdb_id")
	String mImdbId;

	@ColumnInfo(name = "homepage")
	@SerializedName("homepage")
	String mHomepage;

	public TmdbPerson(String birthday, String knownForDepartment, String deathday, @NotNull Integer id, String name, String gender, String biography, String popularity, String placeOfBirth, String adult, String imdbId, String homepage) {
		mBirthday = birthday;
		mKnownForDepartment = knownForDepartment;
		mDeathday = deathday;
		mId = id;
		mName = name;
		mGender = gender;
		mBiography = biography;
		mPopularity = popularity;
		mPlaceOfBirth = placeOfBirth;
		mAdult = adult;
		mImdbId = imdbId;
		mHomepage = homepage;
	}

	public String getBirthday() {
		return mBirthday;
	}

	public void setBirthday(String birthday) {
		mBirthday = birthday;
	}

	public String getKnownForDepartment() {
		return mKnownForDepartment;
	}

	public void setKnownForDepartment(String knownForDepartment) {
		mKnownForDepartment = knownForDepartment;
	}

	public String getDeathday() {
		return mDeathday;
	}

	public void setDeathday(String deathday) {
		mDeathday = deathday;
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

	public String getGender() {
		return mGender;
	}

	public void setGender(String gender) {
		mGender = gender;
	}

	public String getBiography() {
		return mBiography;
	}

	public void setBiography(String biography) {
		mBiography = biography;
	}

	public String getPopularity() {
		return mPopularity;
	}

	public void setPopularity(String popularity) {
		mPopularity = popularity;
	}

	public String getPlaceOfBirth() {
		return mPlaceOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		mPlaceOfBirth = placeOfBirth;
	}

	public String getAdult() {
		return mAdult;
	}

	public void setAdult(String adult) {
		mAdult = adult;
	}

	public String getImdbId() {
		return mImdbId;
	}

	public void setImdbId(String imdbId) {
		mImdbId = imdbId;
	}

	public String getHomepage() {
		return mHomepage;
	}

	public void setHomepage(String homepage) {
		mHomepage = homepage;
	}

	@NonNull
	@Override
	public String toString() {
		return getName();
	}
}
