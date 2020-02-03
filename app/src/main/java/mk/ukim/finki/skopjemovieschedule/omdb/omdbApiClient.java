package mk.ukim.finki.skopjemovieschedule.omdb;


import android.util.Log;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class omdbApiClient {
    private static String TAG = "omdbApiClient";

    private static Retrofit retroFit = null;
    private static Retrofit getRetroFit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        if(retroFit == null){

            retroFit = new Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroFit;
    }

    public static omdbMovie getMovie(String title) throws IOException {
        omdbAPIInterface omdbAPIInterface = getRetroFit().create(omdbAPIInterface.class);
        Call<omdbMovie> call = omdbAPIInterface.getMovieByTitle("434c6b1f", title);
        omdbMovie movie = call.execute().body();
        Log.v(TAG + " getMovie()", "Received: " + movie.toString());
        return movie;
    }
}
