package com.stefankrstikj.skopjemovieschedule.api_response.tmdb;

import android.util.Log;

import com.stefankrstikj.skopjemovieschedule.api_response.APIKeys;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiClient {
    private static String TAG = "tmdbApiClient";

    private static Retrofit retroFit = null;
    private static Retrofit getRetroFit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        if(retroFit == null){

            retroFit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroFit;
    }

    public static TmdbMovieDiscoveryResponse getPopularMovies() throws IOException {
        TmdbApiInterface tmdbApiInterface = getRetroFit().create(TmdbApiInterface.class);
        Call<TmdbMovieDiscoveryResponse> call = tmdbApiInterface.getAllPopularMovies(APIKeys.TMDB_API_KEY);
        TmdbMovieDiscoveryResponse m = call.execute().body();
        return m;
    }
}
