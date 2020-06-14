package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import android.util.Log;

import com.stefankrstikj.skopjemovieschedule.api.APIKeys;
import com.stefankrstikj.skopjemovieschedule.database.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieVideo;

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
	private TmdbMovieGenreRepository mTmdbMovieGenreRepository;
	private TmdbMovieReviewRepository mTmdbMovieReviewRepository;
	private TmdbMovieVideoRepository mTmdbMovieVideoRepository;
	public static volatile TmdbApiClient mInstance;

	synchronized public static TmdbApiClient getInstance(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
														 TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository) {
		if (mInstance == null) {
			mInstance = new TmdbApiClient(tmdbMovieRepository, tmdbCastRepository, tmdbMovieRecommendationRepository, tmdbMovieGenreRepository, tmdbMovieReviewRepository, tmdbMovieVideoRepository);
		}
		return mInstance;
	}


	private TmdbApiClient(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
						  TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieVideoRepository = tmdbMovieVideoRepository;
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

//	private Observable<List<TmdbMovieGenre>> getAllGenresObservable() {
//		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
//		return tmdbApiService.getAllGenres(APIKeys.TMDB_API_KEY);
//	}
//
//	public void getAllGenres() {
//		getAllGenresObservable()
//				.flatMapIterable(baseData -> baseData)
//				.doOnError(error -> Log.v(TAG, error.getMessage()))
//				.subscribe(tmdbMovieGenre -> {
//					mTmdbMovieGenreRepository.insert(tmdbMovieGenre);
//				});
//	}


	private Observable<TmdbMovieDetailed> getDetailsForMovieObservable(TmdbMovieDetailed tmdbMovieDetailed) {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getDetailsForMovie(tmdbMovieDetailed.getId(), APIKeys.TMDB_API_KEY);
	}

	private void getDetailsForMovie(TmdbMovieDetailed tmdbMovieDetailed, String resultType) {
		getDetailsForMovieObservable(tmdbMovieDetailed)
				.doOnError(error -> Log.e(TAG, "DETAILS ERROR: " + error.getMessage()))
				.subscribe(data -> {
					data.setResultType(resultType);
					mTmdbMovieRepository.insert(data);
					getMovieCast(tmdbMovieDetailed.getId());
					getMovieReviews(tmdbMovieDetailed.getId());
					getMovieVideos(tmdbMovieDetailed.getId());
				});
	}

	private Observable<TmdbMovieResponse> getTrendingMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllTrendingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getTrendingMovies() {
		getTrendingMoviesObservable()
				.flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, "GET TRENDING: " + error.getMessage()))
				.forEach(tmdbMovieDetailed -> getDetailsForMovie(tmdbMovieDetailed, "Trending"));
	}

	private Observable<TmdbMovieResponse> getAllMoviesForQueryObservable(String query){
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getMoviesForQuery(query, APIKeys.TMDB_API_KEY);
	}

	public void getAllMoviesForQuery(String query){
		getAllMoviesForQueryObservable(query)
				.flatMap(tmdbMovieResponse -> Observable.just(tmdbMovieResponse.getResults()))
				.flatMapIterable(data -> data)
				.forEach(tmdbMovieDetailed -> getDetailsForMovie(tmdbMovieDetailed, "Query"));
	}

	private Observable<TmdbMovieResponse> getUpcomingMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllUpcomingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getUpcomingMovies() {
		getUpcomingMoviesObservable().flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							getDetailsForMovie(tmdbMovieDetailed, "Upcoming");
						}
				);
	}

	private Observable<TmdbMovieResponse> getTopRatedMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllTopRatedMovies(APIKeys.TMDB_API_KEY);
	}

	public void getAllTopRatedMovies() {
		getTopRatedMoviesObservable().flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							getDetailsForMovie(tmdbMovieDetailed, "Top Rated");
						}
				);
	}

	private Observable<TmdbMovieResponse> getAllPopularMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllPopularMovies(APIKeys.TMDB_API_KEY);
	}

	public void getAllPopularMovies() {
		getAllPopularMoviesObservable().flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							getDetailsForMovie(tmdbMovieDetailed, "Popular");
						}
				);
	}

	private Observable<TmdbMovieResponse> getAllNowPlayingMoviesObservable() {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		return tmdbApiService.getAllNowPlayingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getAllNowPlayingMovies() {
		getAllNowPlayingMoviesObservable().flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(tmdbMovieDetailed -> {
							getDetailsForMovie(tmdbMovieDetailed, "Now Playing");
						}
				);
	}

	private void getMovieCast(Integer id) {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		tmdbApiService.getCastForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbCastResponse -> Observable.just(tmdbCastResponse.getResults()))
				.flatMapIterable(data -> data)
				.subscribe(data -> {
					data.setMovieId(id);
					mTmdbCastRepository.insert(data);
				});
	}

	private void getMovieReviews(Integer id) {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		tmdbApiService.getReviewsForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieReviewResponse -> Observable.just(tmdbMovieReviewResponse.getResults()))
				.flatMapIterable(data -> data)
				.subscribe(data -> {
					data.setMovieId(id);
					mTmdbMovieReviewRepository.insert(data);
				});
	}

	public void getMovieRecommendations(Integer id) {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		tmdbApiService.getRecommendationsForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieResponse -> Observable.just(tmdbMovieResponse.getResults()))
				.flatMapIterable(data -> data)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(data -> {
					TmdbMovieRecommendation recommendation = new TmdbMovieRecommendation(id, data.getId());
					mTmdbMovieRecommendationRepository.insert(recommendation);
					getDetailsForMovie(data, "Recommendation");
				});
	}

	public void getMovieVideos(Integer id) {
		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
		tmdbApiService.getVideosForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieVideosResponse -> Observable.just(tmdbMovieVideosResponse.getResponse()))
				.flatMapIterable(data -> data)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(data -> {
					Log.v(TAG, "Received data: " + data.getName());
					data.setMovieId(id);
					mTmdbMovieVideoRepository.insert(data);
				});
	}


//	private void getDetailsForTmdbMovie(TmdbMovieDetailed tmdbMovieDetailed) {
//		// da povikam koi genres gi ima, pa potoa da povikam cast i recommendations i se toa
//		Log.v(TAG, "Getting details for: " + tmdbMovieDetailed.getTitle());
//		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
//		tmdbApiService.getDetailsForMovie(tmdbMovieDetailed.getId(), APIKeys.TMDB_API_KEY);
//
//	}
}
