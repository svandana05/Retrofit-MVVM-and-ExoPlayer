package com.example.retrofitapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofitapk.Adapters.SlideImageAdapter;
import com.example.retrofitapk.Adapters.VideoViewPagerAdapter;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Post;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivity extends AppCompatActivity{

    private VerticalViewPager mPager;
    ApiClient api;
    VideoViewPagerAdapter viewPagerAdapter;
    List<Datum> videoPosts;
    int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mPager =  findViewById(R.id.video_viewpager);
        getVideoPost(); // loads video post from server

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("VideoActivity", "onPageSelected:- "+position);
                //viewPagerAdapter.releasePlayer();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getVideoPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiClient.class);
        Call<Post> call = api.getVideoPost(2, 1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                try {
                    Post post = response.body();
                    videoPosts = post.getData();
                    viewPagerAdapter = new VideoViewPagerAdapter(VideoActivity.this, videoPosts);
                    mPager.setAdapter(viewPagerAdapter);
                }catch (NullPointerException e){ }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }

}
