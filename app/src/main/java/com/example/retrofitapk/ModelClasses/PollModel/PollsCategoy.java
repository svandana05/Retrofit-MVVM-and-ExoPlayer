package com.example.retrofitapk.ModelClasses.PollModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PollsCategoy {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public class Datum {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("category")
        @Expose
        public String category;
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
        @SerializedName("slug")
        @Expose
        public String slug;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("description_hi")
        @Expose
        public String descriptionHi;
        @SerializedName("description_bn")
        @Expose
        public String descriptionBn;
        @SerializedName("description_mr")
        @Expose
        public String descriptionMr;
        @SerializedName("description_te")
        @Expose
        public String descriptionTe;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("update_at")
        @Expose
        public String updateAt;
        @SerializedName("parentId")
        @Expose
        public Integer parentId;
        @SerializedName("month")
        @Expose
        public Integer month;
        @SerializedName("order")
        @Expose
        public Integer order;

        public String getCategory() {
            return category;
        }

        public Integer getId() {
            return id;
        }
    }
}
