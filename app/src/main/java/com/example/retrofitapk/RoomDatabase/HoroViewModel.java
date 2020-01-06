package com.example.retrofitapk.RoomDatabase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscope;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Follow;

import java.util.List;

public class HoroViewModel extends AndroidViewModel {
    private HoroRepository mRepository;

    private LiveData<List<HoroData>> mAllWords;
    private LiveData<List<Categories.CatData>> catData;
    private LiveData<List<Datum>> postData;
    private LiveData<List<Follow>> followData;

    public HoroViewModel (Application application) {
        super(application);
        mRepository = new HoroRepository(application);
        mAllWords = mRepository.getAllHoro();
        catData = mRepository.getAllCategories();
        postData = mRepository.getAllPosts();
        followData = mRepository.getAllFollows();
    }

    public LiveData<List<Follow>> getAllFollows() { return followData; }
    public void insertFollow(Follow... data) { mRepository.insertPostFollow(data); }

    public LiveData<List<Datum>> getAllPosts() { return postData; }
    public void insertPosts(Datum... data) { mRepository.insertPostData(data); }
    public void deletePosts(Datum data) { mRepository.deletePostData(data); }
    public void updatePosts(Datum data) { mRepository.updatePostData(data); }

    public LiveData<List<Categories.CatData>> getAllCategories() { return catData; }
    public void insertCategories(Categories.CatData data) { mRepository.insertCategory(data); }

    public LiveData<List<HoroData>> getAllWords() { return mAllWords; }
    public void insertHoro(HoroData data) { mRepository.insertHoro(data); }

}
