package com.example.retrofitapk.ModelClasses.PollModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PollQuesByID {

    @SerializedName("polldata")
    @Expose
    private List<Polldatum> polldata = null;
    @SerializedName("numberofday")
    @Expose
    private Integer numberofday;
    @SerializedName("userselectedoptions")
    @Expose
    private Object userselectedoptions;

    public List<Polldatum> getPolldata() {
        return polldata;
    }

    public Integer getNumberofday() {
        return numberofday;
    }


    public Object getUserselectedoptions() {
        return userselectedoptions;
    }

}
