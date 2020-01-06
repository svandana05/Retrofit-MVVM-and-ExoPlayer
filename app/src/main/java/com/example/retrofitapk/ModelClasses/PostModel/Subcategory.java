package com.example.retrofitapk.ModelClasses.PostModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subcategory {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("parentId")
    @Expose
    public Integer parentId;
    @SerializedName("category_en")
    @Expose
    public String categoryEn;
    @SerializedName("category_hi")
    @Expose
    public String categoryHi;
    @SerializedName("category_bn")
    @Expose
    public String categoryBn;
    @SerializedName("category_mr")
    @Expose
    public String categoryMr;
    @SerializedName("category_te")
    @Expose
    public String categoryTe;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("photo")
    @Expose
    public String photo;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
}
