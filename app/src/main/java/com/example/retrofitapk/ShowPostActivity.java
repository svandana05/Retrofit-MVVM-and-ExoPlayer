package com.example.retrofitapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitapk.Adapters.PostDataAdapter;
import com.example.retrofitapk.ModelClasses.PostModel.Datum;
import com.example.retrofitapk.ModelClasses.PostModel.Post;
import com.example.retrofitapk.RoomDatabase.HoroViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPostActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerViewPostList;
    SwipeRefreshLayout swipeRefreshLayout;
    PostDataAdapter postDataAdapter;
    ProgressBar progressBar;
    ApiClient api;
    Post newPost, savedPost;
    public boolean isConnected;
    private HoroViewModel horoViewModel;
    public int lastVisiblePosition;
    LinearLayoutManager layoutManager;
    ConnectivityManager connectivityManager;
    NetworkRequest networkBuilder;
    ConnectivityManager.NetworkCallback networkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        recyclerViewPostList = findViewById(R.id.recycler_view_post_list);
        // SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewPostList.setLayoutManager(layoutManager);
        horoViewModel = ViewModelProviders.of(this).get(HoroViewModel.class);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        api = retrofit.create(ApiClient.class);

        connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkBuilder = new NetworkRequest.Builder().build();
        networkCallback = new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                Log.e("ShowPostActivity", "Network is available.");
                isConnected = true;
                if (newPost == null){
                    try {
                        loadPost(1);
                        Log.e("onAvailable", "newPost is null load newPost to page 1");
                    }catch (IllegalStateException ill){}
                }
                if (newPost !=null && postDataAdapter.loading){
                    int page = newPost.getData().get(newPost.getData().size()-1).getPost_page_id()+1;
                    Log.e("onAvailable", "try loading data for page "+page);
                    loadPost(page);
                }
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
                isConnected = false;
                Log.e("ShowPostActivity", "Network is lost.");
                Toast.makeText(ShowPostActivity.this, "No internet connection is available.", Toast.LENGTH_LONG).show();

            }
        };

        horoViewModel.getAllPosts().observe(ShowPostActivity.this, new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {
                Log.e("getAllPosts", " Post data size is-"+data.size());
                if (data.size()>1){
                    if (newPost==null){
                        savedPost = new Post();
                        savedPost.setCurrentPage(data.get(data.size()-1).getPost_page_id());
                        savedPost.setLastPage(data.get(data.size()-1).getPost_last_page_id());
                        savedPost.setData(data);
                        postDataAdapter = new PostDataAdapter(ShowPostActivity.this, savedPost);
                        recyclerViewPostList.setAdapter(postDataAdapter);
                    }else {
                        newPost.setCurrentPage(data.get(data.size()-1).getPost_page_id());
                        newPost.setLastPage(data.get(data.size()-1).getPost_last_page_id());
                        newPost.setData(data);
                        postDataAdapter = new PostDataAdapter(ShowPostActivity.this, newPost);
                        recyclerViewPostList.setAdapter(postDataAdapter);
                    }
                }
            }
        });

        recyclerViewPostList.setScrollbarFadingEnabled(true);

    }


    public void loadPost(int page){
        Call<Post> call = api.getPost(page);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                try {
                    Post post = response.body();
                    for (int i=0; i <post.getData().size(); i++){
                        post.getData().get(i).setPost_page_id(post.getCurrentPage());
                        post.getData().get(i).setPost_last_page_id(post.getLastPage());
                        if (page==1){
                            newPost = post;
                            horoViewModel.insertPosts(post.getData().get(i));
                            horoViewModel.updatePosts(post.getData().get(i));
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                    if (page==1 && savedPost!= null && savedPost.getData().size()>27){
                        for (int j=28; j<savedPost.getData().size(); j++){
                            Log.e("ShowPostActivity", "Delete post from database-"+savedPost.getData().get(j).getId());
                            horoViewModel.deletePosts(savedPost.getData().get(j));
                        }
                        postDataAdapter = new PostDataAdapter(ShowPostActivity.this, newPost);
                        recyclerViewPostList.setAdapter(postDataAdapter);
                    }
                    Log.e("ShowPostActivity", " Post loaded for page-"+post.getCurrentPage());
                    if (page!=1){
                        postDataAdapter.loading = false;
                        postDataAdapter.addPost(post);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectivityManager.registerNetworkCallback(networkBuilder, networkCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (networkCallback != null)
            connectivityManager.unregisterNetworkCallback(networkCallback);

        try {
            postDataAdapter.player.stop();
            postDataAdapter.player.release();
        }catch (NullPointerException e){}
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            postDataAdapter.player.stop();
            //postDataAdapter.player.release();
        }catch (NullPointerException e){}
    }


    @Override
    public void onRefresh() {
        if (isConnected){
            loadPost(1);
        }else {
            Toast.makeText(this, "Make sure you have an internet connection.", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
