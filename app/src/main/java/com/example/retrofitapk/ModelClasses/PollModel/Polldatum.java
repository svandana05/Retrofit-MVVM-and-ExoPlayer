package com.example.retrofitapk.ModelClasses.PollModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Polldatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("pollquestion")
    @Expose
    private String pollquestion;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("pollopitons")
    @Expose
    private List<Pollopiton> pollopitons = null;

    public Integer getId() {
        return id;
    }

    public Integer getCategory() {
        return category;
    }

    public String getPollquestion() {
        return pollquestion;
    }

    public String getSlug() {
        return slug;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
    public List<Pollopiton> getPollopitons() {
        return pollopitons;
    }


}
