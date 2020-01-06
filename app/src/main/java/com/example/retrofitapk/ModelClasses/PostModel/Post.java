package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


//@Entity(tableName = "post_master_table")
public class Post {


    //@PrimaryKey
    //@NonNull
    //@ColumnInfo(name = "current_page")
    @SerializedName("current_page")
    @Expose
    public Integer currentPage;
    @SerializedName("data")
    @Expose
    //@Ignore
    public List<Datum> data = null;
    @SerializedName("first_page_url")
    @Expose
    @Ignore
    public String firstPageUrl;
    @SerializedName("from")
    @Expose
    @Ignore
    public Integer from;
    @SerializedName("last_page")
    @Expose
    public Integer lastPage;
    @SerializedName("last_page_url")
    @Expose
    @Ignore
    public String lastPageUrl;
    @SerializedName("next_page_url")
    @Expose
    @Ignore
    public String nextPageUrl;
    @SerializedName("path")
    @Expose
    @Ignore
    public String path;
    @SerializedName("per_page")
    @Expose
    @Ignore
    public Integer perPage;
    @SerializedName("prev_page_url")
    @Expose
    @Ignore
    public Object prevPageUrl;
    @SerializedName("to")
    @Expose
    @Ignore
    public Integer to;
    @SerializedName("total")
    @Expose
    @Ignore
    public Integer total;

    public List<Datum> getData() {
        return data;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setCurrentPage(@NonNull Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
