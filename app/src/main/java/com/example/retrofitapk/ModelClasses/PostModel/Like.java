package com.example.retrofitapk.ModelClasses.PostModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("postId")
    @Expose
    public Integer postId;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("like")
    @Expose
    public Integer like;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
