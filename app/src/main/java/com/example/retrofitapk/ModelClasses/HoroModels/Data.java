package com.example.retrofitapk.ModelClasses.HoroModels;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "horodetails_data_table",
        foreignKeys = @ForeignKey(entity = HoroData.class,
                parentColumns = "id",
                childColumns = "horoID",
                onUpdate = CASCADE), indices = @Index(value = {"horoID"}, unique = true))
public class Data {
    public Data(){

    }

    @PrimaryKey(autoGenerate = true)
    private int colID;

    private int horoID;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("sun_sign")
    @Expose
    private String sunSign;
    @SerializedName("prediction_date")
    @Expose

    private String predictionDate;
    @SerializedName("prediction")
    @Expose
    @Embedded
    private Prediction prediction;

    public Boolean getStatus() {
        return status;
    }

    public int getHoroID() {
        return horoID;
    }

    public void setHoroID(int horoID) {
        this.horoID = horoID;
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public String getSunSign() {
        return sunSign;
    }

    public void setSunSign(String sunSign) {
        this.sunSign = sunSign;
    }

    public String getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(String predictionDate) {
        this.predictionDate = predictionDate;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public static class Prediction {

        @SerializedName("personal_life")
        @Expose
        private String personalLife;
        @SerializedName("profession")
        @Expose
        private String profession;
        @SerializedName("health")
        @Expose
        private String health;
        @SerializedName("travel")
        @Expose
        private String travel;
        @SerializedName("luck")
        @Expose
        private String luck;
        @SerializedName("emotions")
        @Expose
        private String emotions;

        public String getPersonalLife() {
            return personalLife;
        }

        public String getProfession() {
            return profession;
        }

        public String getHealth() {
            return health;
        }

        public String getTravel() {
            return travel;
        }

        public String getLuck() {
            return luck;
        }

        public String getEmotions() {
            return emotions;
        }

        public void setPersonalLife(String personalLife) {
            this.personalLife = personalLife;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public void setTravel(String travel) {
            this.travel = travel;
        }

        public void setLuck(String luck) {
            this.luck = luck;
        }

        public void setEmotions(String emotions) {
            this.emotions = emotions;
        }
    }

}
