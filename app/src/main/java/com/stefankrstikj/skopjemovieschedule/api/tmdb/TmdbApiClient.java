package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import android.util.Log;

import com.stefankrstikj.skopjemovieschedule.api.APIKeys;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiClient {
	private static String TAG = "tmdbApiClient";
	private static Retrofit retroFit = null;

	private TmdbMovieRepository mTmdbMovieRepository;
	private TmdbCastRepository mTmdbCastRepository;
	private TmdbMovieRecommendationRepository mTmdbMovieRecommendationRepository;

	public TmdbApiClient(TmdbMovieRepository tmdbMovieRepository,
						 TmdbCastRepository tmdbCastRepository,
						 TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
	}

	private static Retrofit getRetroFit() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

		if (retroFit == null) {

			retroFit = new Retrofit.Builder()
					.baseUrl("https://api.themoviedb.org/3/")
					.client(client)
					.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}

		return retroFit;
	}


	private Observable<TmdbMovieResponse> getTrendingMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllTrendingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getTrendingMovies() {
		getTrendingMoviesObservable()
				.flatMap(discoveryResponse -> Observable.just(discoveryResponse.mResults))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.v(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							tmdbMovieDetailed.setResultType("Trending");
							mTmdbMovieRepository.insert(tmdbMovieDetailed);
						}
				);
	}

	private Observable<TmdbMovieResponse> getUpcomingMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllUpcomingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getUpcomingMovies() {
		getUpcomingMoviesObservable().flatMap(discoveryResponse -> Observable.just(discoveryResponse.mResults))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.v(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							tmdbMovieDetailed.setResultType("Upcoming");
							mTmdbMovieRepository.insert(tmdbMovieDetailed);
						}
				);
	}

//	private void getMovieCast(Integer id) {
//		Log.v(TAG, "Getting cast for: " + id);
//		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
//		tmdbApiService.getCastForMovie(id, APIKeys.TMDB_API_KEY)
//				.flatMap(tmdbCastResponse -> Observable.just(tmdbCastResponse.getResults()))
//				.flatMapIterable(data -> data)
//				.subscribe(data -> {
//					data.setMovieId(id);
//					mTmdbCastRepository.insert(data);
//				});
//	}

	private void getMovieRecommendations(Integer id) {
		Log.v(TAG, "Getting recommendation for: " + id);
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		tmdbApiService.getRecommendationsForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieResponse -> Observable.just(tmdbMovieResponse.mResults))
				.flatMapIterable(data -> data)
				.doOnError(error -> Log.v(TAG, error.getMessage()))
				.subscribe(data -> {
					TmdbMovieRecommendation recommendation = new TmdbMovieRecommendation(id, data.getId());
					mTmdbMovieRecommendationRepository.insert(recommendation);
				});
	}
}
