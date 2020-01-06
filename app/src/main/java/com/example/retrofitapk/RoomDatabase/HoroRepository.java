package com.example.retrofitapk.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.ModelClasses.HoroModels.Data;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscope;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscopedetails;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Follow;

import java.util.List;

public class HoroRepository {
    private HoroDao horoDao;
    private LiveData<List<HoroData>> mAllHoro;
    private LiveData<Data> horodetail;
    private LiveData<List<Categories.CatData>> catData;
    private LiveData<List<Datum>> postData;
    private LiveData<List<Follow>> followData;

    public HoroRepository(Application application) {
        HoroDatabase db = HoroDatabase.getDatabase(application);
        horoDao = db.horoDao();
        mAllHoro = horoDao.getHoroData();
        catData = horoDao.getCategoriesData();
        postData = horoDao.getPostData();
    }

    LiveData<List<Follow>> getAllFollows() {
        return followData;
    }
    LiveData<List<Datum>> getAllPosts() {
        return postData;
    }
    LiveData<List<Categories.CatData>> getAllCategories() {
        return catData;
    }
    LiveData<List<HoroData>> getAllHoro() {
        return mAllHoro;
    }
    LiveData<Data> getHoroDetail(int id) {
        Log.e("HoroRepo", "ID is-"+id);
        return horodetail = horoDao.getHoroDetails(id);
    }

    void insertPostFollow(Follow... data) {
        new insertAsyncTaskFollow(horoDao).execute(data);
    }
    void insertPostData(Datum... data) {
        new insertAsyncTaskPost(horoDao).execute(data);
    }
    void deletePostData(Datum data) {
        new deleteAsyncTaskPost(horoDao).execute(data);
    }
    void updatePostData(Datum data) {
        new updateAsyncTaskPost(horoDao).execute(data);
    }

    void insertCategory(Categories.CatData data) {
        new insertAsyncTask2(horoDao).execute(data);
    }
    void insertHoro(HoroData data) {
        new insertAsyncTask(horoDao).execute(data);
    }
    void insertHoroDetail(Data data) {
        new insertAsyncTask1(horoDao).execute(data);
    }

    public void updateHoroDetail(Data data) {
        new updateAsyncTask1(horoDao).execute(data);
    }

    private static class insertAsyncTask2 extends AsyncTask<Categories.CatData, Void, Void> {
        private HoroDao mAsyncTaskDao;
        insertAsyncTask2(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Categories.CatData... params) {
            mAsyncTaskDao.insertCatData(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask1 extends AsyncTask<Data, Void, Void> {
        private HoroDao mAsyncTaskDao;
        insertAsyncTask1(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Data... params) {
            mAsyncTaskDao.insertHoroDetails(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<HoroData, Void, Void> {
        private HoroDao mAsyncTaskDao;
        insertAsyncTask(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final HoroData... params) {
            mAsyncTaskDao.insertHoroData(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskPost extends AsyncTask<Datum, Void, Void> {
        private HoroDao mAsyncTaskDao;
        insertAsyncTaskPost(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Datum... params) {
            mAsyncTaskDao.insertPostData(params);
            return null;
        }
    }

    private static class deleteAsyncTaskPost extends AsyncTask<Datum, Void, Void> {
        private HoroDao mAsyncTaskDao;
        deleteAsyncTaskPost(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Datum... params) {
            mAsyncTaskDao.deletePosts(params[0]);
            return null;
        }
    }

    private static class updateAsyncTaskPost extends AsyncTask<Datum, Void, Void> {
        private HoroDao mAsyncTaskDao;
        updateAsyncTaskPost(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Datum... params) {
            mAsyncTaskDao.updatePosts(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskFollow extends AsyncTask<Follow, Void, Void> {
        private HoroDao mAsyncTaskDao;
        insertAsyncTaskFollow(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Follow... params) {
            mAsyncTaskDao.insertPostFollow(params);
            return null;
        }
    }

    private static class updateAsyncTask1 extends AsyncTask<Data, Void, Void> {
        private HoroDao mAsyncTaskDao;
        updateAsyncTask1(HoroDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Data... params) {
            mAsyncTaskDao.updateHoroDetails(params[0]);
            return null;
        }
    }


}
