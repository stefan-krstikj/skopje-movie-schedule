package com.stefankrstikj.skopjemovieschedule.database.tmdb.people;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.stefankrstikj.skopjemovieschedule.models.TmdbPerson;

@Dao
public interface TmdbPeopleDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(TmdbPerson tmdbPerson);

	@Query("SELECT * FROM tmdb_persons WHERE id==:id")
	LiveData<TmdbPerson> getPersonById(Integer id);
}
