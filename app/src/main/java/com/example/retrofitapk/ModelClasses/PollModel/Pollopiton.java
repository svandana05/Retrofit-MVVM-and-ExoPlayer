package com.example.retrofitapk.ModelClasses.PollModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pollopiton {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("questionId")
    @Expose
    private Integer questionId;
    @SerializedName("options")
    @Expose
    private String options;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getOptions() {
        return options;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }
}
