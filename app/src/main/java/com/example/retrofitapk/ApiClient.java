package com.example.retrofitapk;

import com.example.retrofitapk.ModelClasses.PollModel.AllPolls;
import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscope;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscopedetails;
import com.example.retrofitapk.ModelClasses.PollModel.PollQuesByID;
import com.example.retrofitapk.ModelClasses.PollModel.PollsCategoy;
import com.example.retrofitapk.ModelClasses.PostModel.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    String BASE_URL = "BASE_URL";

    @GET("getCategory")
    Call<Categories> getCategory();

    @GET("getHoroscope")
    Call<Horoscope> getHoroscope();

    @GET("getHoroscopedetails")
    Call<Horoscopedetails> getHoroscopedetails(@Query("id") Integer id);

    @GET("getPost")
    Call<Post> getPost(@Query("page") Integer page);

    @GET("getPost")
    Call<Post> getVideoPost(@Query("file_type") Integer filtType, @Query("page") Integer page);

    @GET("getPollcategory")
    Call<PollsCategoy> getPollscategory();

    @GET("getAllpolls")
    Call<AllPolls> getAllpolls(@Query("type") String  type, @Query("category") Integer  category);

    @GET("getcurrentPoll")
    Call<PollQuesByID> getCurrentPoll(@Query("questionId") Integer quesId);

}
