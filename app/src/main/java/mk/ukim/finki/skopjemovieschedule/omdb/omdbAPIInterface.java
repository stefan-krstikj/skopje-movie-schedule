package mk.ukim.finki.skopjemovieschedule.omdb;

import mk.ukim.finki.skopjemovieschedule.data.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface omdbAPIInterface {

    @GET("?")
    Call<omdbMovie> getMovieByTitleAndYear(
            @Query("apikey") String apiKey,
            @Query("t") String title,
            @Query("y") String year,
            @Query("plot") String plot
    );

    @GET("?")
    Call<omdbMovie> getMovieByTitle(
            @Query("apikey") String apiKey,
            @Query("t") String title,
            @Query("plot") String plot
    );

}
