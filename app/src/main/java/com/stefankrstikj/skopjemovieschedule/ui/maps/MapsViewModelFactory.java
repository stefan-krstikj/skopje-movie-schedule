package com.stefankrstikj.skopjemovieschedule.ui.maps;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stefankrstikj.skopjemovieschedule.database.MapLocationRepository;

public class MapsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MapLocationRepository mMapLocationRepository;


    public MapsViewModelFactory(MapLocationRepository mMapLocationRepository) {
        this.mMapLocationRepository = mMapLocationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MapsViewModel mapsViewModel= new MapsViewModel(mMapLocationRepository);
        return (T) mapsViewModel;
    }
}
