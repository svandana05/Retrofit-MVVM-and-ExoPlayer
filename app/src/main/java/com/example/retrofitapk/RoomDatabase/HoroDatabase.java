package com.example.retrofitapk.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.ModelClasses.HoroModels.Data;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Follow;

@Database(entities = {HoroData.class, Data.class, Categories.CatData.class, Datum.class, Follow.class}, version = 1, exportSchema = false)
public abstract class HoroDatabase extends RoomDatabase {

    public abstract HoroDao horoDao();

    private static volatile HoroDatabase INSTANCE;

    public static HoroDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HoroDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HoroDatabase.class, "horo_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final HoroDao mDao;

        PopulateDbAsync(HoroDatabase db) {
            mDao = db.horoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            //Horoscope.HoroData word = new Horoscope.HoroData();
            //mDao.insertHoroData("");
            return null;
        }
    }
}
