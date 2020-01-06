package com.example.retrofitapk.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.ModelClasses.HoroModels.Data;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Follow;

import java.util.List;

@Dao
public interface HoroDao {

    @Query("SELECT * FROM horoscope_table")
    LiveData<List<HoroData>> getHoroData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHoroData(HoroData horoData);

    @Query("SELECT * FROM horodetails_data_table WHERE horoID = :horoID")
    LiveData<Data> getHoroDetails(final int horoID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHoroDetails(Data horoDetails);

    @Update
    void updateHoroDetails(Data horoDetails);

    @Query("SELECT * FROM categories_data_table")
    LiveData<List<Categories.CatData>> getCategoriesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCatData(Categories.CatData catData);

    @Query("SELECT * FROM post_data_table ORDER BY post_id DESC")
    LiveData<List<Datum>> getPostData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPostData(Datum... postData);

    @Delete
    void deletePosts(Datum postData);

    @Update
    void updatePosts(Datum postData);

    @Query("SELECT * FROM post_follow_table")
    LiveData<List<Follow>> getPostFollow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPostFollow(Follow... followData);

}
