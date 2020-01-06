package com.example.retrofitapk.ModelClasses.PollModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPolls {
    @SerializedName("data")
    @Expose
    public List<PollData> data = null;

    public class PollData {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("pollquestion")
        @Expose
        public String pollquestion;
        @SerializedName("start_date")
        @Expose
        public String startDate;
        @SerializedName("end_date")
        @Expose
        public String endDate;
        @SerializedName("day")
        @Expose
        public Integer day;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("created_at")
        @Expose
        public String createdAt;

        public Integer getId() {
            return id;
        }

        public String getPollquestion() {
            return pollquestion;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public Integer getDay() {
            return day;
        }
    }
}
