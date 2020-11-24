package com.stefankrstikj.skopjemovieschedule.ui.discover.detailed.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stefankrstikj.skopjemovieschedule.api.tmdb.TmdbApiClient;
import com.stefankrstikj.skopjemovieschedule.database.tmdb.people.TmdbPeopleRepository;
import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;

public class DetailedTmdbPersonViewModel extends ViewModel {
	private TmdbPeopleRepository mTmdbPeopleRepository;
	private TmdbApiClient mApiClient;

	public DetailedTmdbPersonViewModel(TmdbPeopleRepository tmdbPeopleRepository, TmdbApiClient apiClient) {
		mTmdbPeopleRepository = tmdbPeopleRepository;
		mApiClient = apiClient;
	}

	public LiveData<TmdbPerson> getPersonForId(Integer id){
		return mTmdbPeopleRepository.getPersonById(id);
	}

	public void fetchPersonForId(Integer id){
		mApiClient.getDetailsForPerson(id);
	}
}
