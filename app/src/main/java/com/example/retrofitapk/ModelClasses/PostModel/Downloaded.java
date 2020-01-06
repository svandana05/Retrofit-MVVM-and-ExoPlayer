package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

public class Downloaded {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("postId")
    @Expose
    public Integer postId;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
