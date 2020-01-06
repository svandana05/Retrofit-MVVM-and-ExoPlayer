package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "post_follow_table")
public class Follow {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer colId;

    public Integer postID;

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("followingid")
    @Expose
    public Integer followingid;
    @SerializedName("follow")
    @Expose
    public String follow;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @NonNull
    public Integer getColId() {
        return colId;
    }

    public Integer getPostID() {
        return postID;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFollowingid() {
        return followingid;
    }

    public String getFollow() {
        return follow;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
