package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("parentId")
    @Expose
    @Ignore
    public Integer parentId;
    @SerializedName("category_en")
    @Expose
    public String categoryEn;
    @SerializedName("category_hi")
    @Expose
    @Ignore
    public String categoryHi;
    @SerializedName("category_bn")
    @Expose
    @Ignore
    public String categoryBn;
    @SerializedName("category_mr")
    @Expose
    @Ignore
    public String categoryMr;
    @SerializedName("category_te")
    @Expose
    @Ignore
    public String categoryTe;
    @SerializedName("color")
    @Expose
    @Ignore
    public String color;
    @SerializedName("slug")
    @Expose
    @Ignore
    public String slug;
    @SerializedName("photo")
    @Expose
    @Ignore
    public String photo;
    @SerializedName("status")
    @Expose
    @Ignore
    public String status;
    @SerializedName("created_at")
    @Expose
    @Ignore
    public String createdAt;

    public String getCategoryEn() {
        return "#"+categoryEn;
    }
}
