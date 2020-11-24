package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.person;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.people.TmdbPeopleRepository;

public class DetailedTmdbPersonViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private TmdbPeopleRepository mTmdbPeopleRepository;
	private TmdbApiClient mApiClient;

	public DetailedTmdbPersonViewModelFactory(TmdbPeopleRepository tmdbPeopleRepository, TmdbApiClient apiClient) {
		mTmdbPeopleRepository = tmdbPeopleRepository;
		mApiClient = apiClient;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DetailedTmdbPersonViewModel detailedTmdbPersonViewModel = new DetailedTmdbPersonViewModel(mTmdbPeopleRepository, mApiClient);
		return (T) detailedTmdbPersonViewModel;
	}
}
