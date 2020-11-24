package com.stefankrstikj.skopjemovieschedule.api.tmdb;

import android.util.Log;

import com.stefankrstikj.skopjemovieschedule.api.APIKeys;
import com.stefankrstikj.skopjemovieschedule.api.tmdb.response.TmdbMovieResponse;
import com.stefankrstikj.skopjemovieschedule.api.tmdb.service.TmdbMovieApiService;
import com.stefankrstikj.skopjemovieschedule.api.tmdb.service.TmdbPeopleApiService;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.TmdbMovieRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.cast.TmdbCastRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.genre.TmdbMovieGenreRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.recommendation.TmdbMovieRecommendationRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.review.TmdbMovieReviewRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.movie.video.TmdbMovieVideoRepository;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.people.TmdbPeopleRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieDetailed;
import com.stefankrstikj.skopjemovieschedule.models.TmdbMovieRecommendation;
import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;
import com.stefankrstikj.skopjemovieschedule.utils.URLList;

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
	private TmdbPeopleRepository mTmdbPeopleRepository;
	public static volatile TmdbApiClient mInstance;

	synchronized public static TmdbApiClient getInstance(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository,
														 TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository,
														 TmdbPeopleRepository tmdbPeopleRepository) {
		if (mInstance == null) {
			mInstance = new TmdbApiClient(tmdbMovieRepository, tmdbCastRepository, tmdbMovieRecommendationRepository, tmdbMovieGenreRepository, tmdbMovieReviewRepository, tmdbMovieVideoRepository, tmdbPeopleRepository);
		}
		return mInstance;
	}


	private TmdbApiClient(TmdbMovieRepository tmdbMovieRepository, TmdbCastRepository tmdbCastRepository, TmdbMovieRecommendationRepository tmdbMovieRecommendationRepository, TmdbMovieGenreRepository tmdbMovieGenreRepository, TmdbMovieReviewRepository tmdbMovieReviewRepository, TmdbMovieVideoRepository tmdbMovieVideoRepository, TmdbPeopleRepository tmdbPeopleRepository) {
		mTmdbMovieRepository = tmdbMovieRepository;
		mTmdbCastRepository = tmdbCastRepository;
		mTmdbMovieRecommendationRepository = tmdbMovieRecommendationRepository;
		mTmdbMovieGenreRepository = tmdbMovieGenreRepository;
		mTmdbMovieReviewRepository = tmdbMovieReviewRepository;
		mTmdbMovieVideoRepository = tmdbMovieVideoRepository;
		mTmdbPeopleRepository = tmdbPeopleRepository;
	}

	private static Retrofit getRetroFit() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

		if (retroFit == null) {

			retroFit = new Retrofit.Builder()
					.baseUrl(URLList.TMDBBaseUrl)
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getDetailsForMovie(tmdbMovieDetailed.getId(), APIKeys.TMDB_API_KEY);
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getAllTrendingMovies(APIKeys.TMDB_API_KEY);
	}

	public void getTrendingMovies() {
		getTrendingMoviesObservable()
				.flatMap(discoveryResponse -> Observable.just(discoveryResponse.getResults()))
				.flatMapIterable(baseData -> baseData)
				.doOnError(error -> Log.e(TAG, "GET TRENDING: " + error.getMessage()))
				.forEach(tmdbMovieDetailed -> getDetailsForMovie(tmdbMovieDetailed, "Trending"));
	}

	private Observable<TmdbMovieResponse> getAllMoviesForQueryObservable(String query) {
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getMoviesForQuery(query, APIKeys.TMDB_API_KEY);
	}

	public void getAllMoviesForQuery(String query) {
		getAllMoviesForQueryObservable(query)
				.flatMap(tmdbMovieResponse -> Observable.just(tmdbMovieResponse.getResults()))
				.flatMapIterable(data -> data)
				.forEach(tmdbMovieDetailed -> getDetailsForMovie(tmdbMovieDetailed, "Query"));
	}

	private Observable<TmdbMovieResponse> getUpcomingMoviesObservable() {
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getAllUpcomingMovies(APIKeys.TMDB_API_KEY);
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getAllTopRatedMovies(APIKeys.TMDB_API_KEY);
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getAllPopularMovies(APIKeys.TMDB_API_KEY);
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		return tmdbMovieApiService.getAllNowPlayingMovies(APIKeys.TMDB_API_KEY);
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		tmdbMovieApiService.getCastForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbCastResponse -> Observable.just(tmdbCastResponse.getResults()))
				.flatMapIterable(data -> data)
				.subscribe(data -> {
					data.setMovieId(id);
					mTmdbCastRepository.insert(data);
				});
	}

	private void getMovieReviews(Integer id) {
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		tmdbMovieApiService.getReviewsForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieReviewResponse -> Observable.just(tmdbMovieReviewResponse.getResults()))
				.flatMapIterable(data -> data)
				.subscribe(data -> {
					data.setMovieId(id);
					mTmdbMovieReviewRepository.insert(data);
				});
	}

	public void getMovieRecommendations(Integer id) {
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		tmdbMovieApiService.getRecommendationsForMovie(id, APIKeys.TMDB_API_KEY)
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
		TmdbMovieApiService tmdbMovieApiService = getRetroFit().create(TmdbMovieApiService.class);
		tmdbMovieApiService.getVideosForMovie(id, APIKeys.TMDB_API_KEY)
				.flatMap(tmdbMovieVideosResponse -> Observable.just(tmdbMovieVideosResponse.getResponse()))
				.flatMapIterable(data -> data)
				.doOnError(error -> Log.e(TAG, error.getMessage()))
				.subscribe(data -> {
					data.setMovieId(id);
					mTmdbMovieVideoRepository.insert(data);
				});
	}

	private Observable<TmdbPerson> getDetailsForPersonObs(Integer id) {
		TmdbPeopleApiService tmdbPeopleApiService = getRetroFit().create(TmdbPeopleApiService.class);
		return tmdbPeopleApiService.getDetailsForPerson(id, APIKeys.TMDB_API_KEY);
	}

	public void getDetailsForPerson(Integer id) {
		getDetailsForPersonObs(id)
				.flatMap(data -> Observable.just(data))
				.subscribe(data -> {
					mTmdbPeopleRepository.insert(data);
				}, throwable -> Log.e(TAG, throwable.getMessage()));
	}

//	private void getDetailsForTmdbMovie(TmdbMovieDetailed tmdbMovieDetailed) {
//		// da povikam koi genres gi ima, pa potoa da povikam cast i recommendations i se toa
//		Log.v(TAG, "Getting details for: " + tmdbMovieDetailed.getTitle());
//		TmdbApiService tmdbApiService = getRetroFit().create(TmdbApiService.class);
//		tmdbApiService.getDetailsForMovie(tmdbMovieDetailed.getId(), APIKeys.TMDB_API_KEY);
//
//	}
}
