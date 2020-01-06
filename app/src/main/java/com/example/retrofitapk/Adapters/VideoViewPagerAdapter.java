package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

public class VideoViewPagerAdapter extends PagerAdapter {

    private List<Datum> videoPosts;
    private LayoutInflater inflater;
    private Context context;
    public SimpleExoPlayer player;
    boolean playWhenReady;

    public VideoViewPagerAdapter(Context context, List<Datum> videoUrl) {
        this.context = context;
        this.videoPosts =videoUrl;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.e("VideoViewPagerAdapter", "onDestroyItem"+position);
    }

    @Override
    public int getCount() {
        return videoPosts.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        Log.e("VideoViewPagerAdapter", "onInstantiateItem"+position);
        View videoLayout = inflater.inflate(R.layout.slide_video_layout, view, false);
        PlayerView playerView = videoLayout.findViewById(R.id.post_video);
        TextView tvPostContent = videoLayout.findViewById(R.id.post_content);
        String postContent = videoPosts.get(position).getPostContent();
        if (postContent.length()==0){
            postContent = " Untitled video ";
        }
        tvPostContent.setText(position+"- "+postContent+",  postId-"+videoPosts.get(position).getId());
        player = ExoPlayerFactory.newSimpleInstance(context);
        playerView.setPlayer(player);
        String videoUrl = videoPosts.get(position).getMediaContent();
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);

        view.addView(videoLayout, 0);
        return videoLayout;
    }

    public void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }
}
