package com.example.retrofitapk.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofitapk.ModelClasses.HoroModels.Data;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscopedetails;

public class HoroDetailsViewModel extends AndroidViewModel {
    private HoroRepository mRepository;
    int id;

    private LiveData<Data> mHoroDetails;

    public HoroDetailsViewModel(Application application, int horoid) {
        super(application);
        mRepository = new HoroRepository(application);
        id = horoid;
        mHoroDetails = mRepository.getHoroDetail(id);
    }

    public LiveData<Data> getmHoroDetails() { return mHoroDetails; }
    public void insertHoroDetail(Data data) { mRepository.insertHoroDetail(data); }
    public void updateHoroDetail(Data data) { mRepository.updateHoroDetail(data); }



    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final HoroRepository mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = new HoroRepository(application);
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new HoroDetailsViewModel(mApplication, mProductId);
        }
    }

}
