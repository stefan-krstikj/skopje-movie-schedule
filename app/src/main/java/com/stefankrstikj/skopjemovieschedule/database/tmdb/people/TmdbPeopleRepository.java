package com.stefankrstikj.skopjemovieschedule.database.tmdb.people;

import androidx.lifecycle.LiveData;

import com.stefankrstikj.skopjemovieschedule.database.AppDatabase;
import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;

public class TmdbPeopleRepository {
	private static final String TAG = "TmdbPeopleRepository";

	private TmdbPeopleDao mTmdbPeopleDao;
	private static volatile TmdbPeopleRepository instance;

	public static TmdbPeopleRepository getInstance(TmdbPeopleDao tmdbPeopleDao){
		if(instance == null){
			synchronized (TmdbPeopleDao.class){
				if(instance == null){
					instance = new TmdbPeopleRepository(tmdbPeopleDao);
				}
			}
		}
		return instance;
	}

	private TmdbPeopleRepository(TmdbPeopleDao tmdbPeopleDao) {
		mTmdbPeopleDao = tmdbPeopleDao;
	}

	public void insert(TmdbPerson tmdbPerson){
		AppDatabase.databaseWriteExecutor.execute(() -> mTmdbPeopleDao.insert(tmdbPerson));
	}

	public LiveData<TmdbPerson> getPersonById(Integer id){
		return mTmdbPeopleDao.getPersonById(id);
	}
}
