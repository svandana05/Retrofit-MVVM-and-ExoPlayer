package com.example.retrofitapk.ModelClasses.PostModel;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("created_at")
    @Expose
    @Ignore
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    @Ignore
    public String updatedAt;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("provider")
    @Expose
    @Ignore
    public Object provider;
    @SerializedName("provider_id")
    @Expose
    @Ignore
    public Object providerId;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("affiliate_id")
    @Expose
    public String affiliateId;
    @SerializedName("referred_id")
    @Expose
    public String referredId;
    @SerializedName("total_point")
    @Expose
    public String totalPoint;

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return "https://rgyan.com/public/uploads/profile/"+avatar;
    }

    public String getStatus() {
        return status;
    }
}
