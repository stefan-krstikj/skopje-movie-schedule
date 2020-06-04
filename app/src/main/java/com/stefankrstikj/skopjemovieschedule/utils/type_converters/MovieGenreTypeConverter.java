package com.stefankrstikj.skopjemovieschedule.utils.type_converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieGenre;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class MovieGenreTypeConverter {


	@TypeConverter
	public static List<TmdbMovieGenre> fromString(String data){
		Gson mGson = new Gson();
		if(data == null){
			return Collections.emptyList();
		}
		Type listType = new TypeToken<List<TmdbMovieGenre>>() {}.getType();
		return mGson.fromJson(data, listType);

	}

	@TypeConverter
	public static String fromTmdbMovieGenreList(List<TmdbMovieGenre> list){
		Gson mGson = new Gson();
		return mGson.toJson(list);
	}
}
