package com.example.retrofitapk.ModelClasses.HoroModels;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "horoscope_table")
public class HoroData {

    public HoroData() {
    }

    @PrimaryKey
    @NonNull
    public int id;

    @ColumnInfo(name = "sunsign_col")
    public String sunsign_en;
    public String sunsign_hi;
    public String sunsign_bn;
    public String sunsign_mr;
    public String sunsign_te;
    public String status;

    @ColumnInfo(name = "photo_col")
    public String mphoto;
    public String updated_at;
    public String created_at;

    public int getId() {
        return id;
    }

    public String getSunsign_en() {
        return sunsign_en;
    }

    public String getMphoto() {
        return "https://rgyan.com/"+mphoto;
    }
}
