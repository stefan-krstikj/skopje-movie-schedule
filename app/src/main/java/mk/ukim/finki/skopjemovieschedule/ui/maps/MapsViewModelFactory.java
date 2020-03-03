package mk.ukim.finki.skopjemovieschedule.ui.maps;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mk.ukim.finki.skopjemovieschedule.data.MapLocationRepository;
import mk.ukim.finki.skopjemovieschedule.ui.movies.MoviesViewModel;

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
