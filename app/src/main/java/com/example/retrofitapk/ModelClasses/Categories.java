package com.example.retrofitapk.ModelClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

public class Categories {

    public String status;
    public List<CatData> data = null;

    public String getStatus() {
        return status;
    }

    public List<CatData> getData() {
        return data;
    }

    @Entity(tableName = "categories_data_table")
    public static class CatData{
        @PrimaryKey
        @NonNull
        public String id;
        public String parentId;
        public String category_en;
        public String category_hi;
        public String category_bn;
        public String category_mr;
        public String category_te;
        public String color;
        public String slug;
        public String photo;
        public String status;
        public String created_at;

        public String getId() {
            return id;
        }

        public String getParentId() {
            return parentId;
        }

        public String getCategory_hi() {
            return category_hi;
        }

        public String getCategory_bn() {
            return category_bn;
        }

        public String getCategory_mr() {
            return category_mr;
        }

        public String getCategory_te() {
            return category_te;
        }

        public String getColor() {
            return color;
        }

        public String getSlug() {
            return slug;
        }

        public String getStatus() {
            return status;
        }

        public String getCategory_en() {
            return category_en;
        }

        public String getPhoto() {
            return "https://rgyan.com/"+photo;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public void setCategory_en(String category_en) {
            this.category_en = category_en;
        }

        public void setCategory_hi(String category_hi) {
            this.category_hi = category_hi;
        }

        public void setCategory_bn(String category_bn) {
            this.category_bn = category_bn;
        }

        public void setCategory_mr(String category_mr) {
            this.category_mr = category_mr;
        }

        public void setCategory_te(String category_te) {
            this.category_te = category_te;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }



}
