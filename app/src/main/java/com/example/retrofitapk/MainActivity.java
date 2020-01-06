package com.example.retrofitapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.retrofitapk.Adapters.CatDataAdapter;
import com.example.retrofitapk.Adapters.SlideImageAdapter;
import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.Adapters.HoroDataAdapter;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscope;
import com.example.retrofitapk.RoomDatabase.HoroViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements HoroDataAdapter.RecyclerViewClickListener{

    RecyclerView recyclerView;
    CatDataAdapter adapder;
    RecyclerView horoRecyclerView;
    HoroDataAdapter horoDataAdapter;
    List<HoroData> horoData;
    List<Categories.CatData> catData;
    Button btnShowPost, btnShowVideos, btnActivePolls, btnExpiredPolls;
    ConnectivityManager connectivityManager;
    ConnectivityManager.NetworkCallback networkCallback;
    NetworkRequest networkBuilder;
    ImageView img_right_scroll, img_LeftScroll;


    Integer id;
    private HoroViewModel mWordViewModel;
    ApiClient apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_LeftScroll = findViewById(R.id.img_LeftScroll);
        img_right_scroll = findViewById(R.id.img_right_scroll);
        btnShowPost = findViewById(R.id.btn_show_post);
        recyclerView = findViewById(R.id.list);
        horoRecyclerView = findViewById(R.id.horoscopeRV);
        btnActivePolls = findViewById(R.id.btn_active_polls);
        btnExpiredPolls = findViewById(R.id.btn_expired_polls);
        btnShowVideos = findViewById(R.id.btn_show_video);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horoRecyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        img_right_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = layoutManager.findLastCompletelyVisibleItemPosition();
                Log.e("Horo Pos", " "+pos);
                if (pos == horoRecyclerView.getAdapter().getItemCount()-1){
                    img_right_scroll.setVisibility(View.GONE);
                    img_LeftScroll.setVisibility(View.VISIBLE);
                }else {
                    horoRecyclerView.smoothScrollToPosition(pos+1);
                }
            }
        });
        img_LeftScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("Horo Pos", " "+pos);
                if (pos == 0){
                    img_LeftScroll.setVisibility(View.GONE);
                    img_right_scroll.setVisibility(View.VISIBLE);
                }else {
                    horoRecyclerView.smoothScrollToPosition(pos-1);
                }
            }
        });

        btnShowVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });

        btnShowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowPostActivity.class));
            }
        });

        btnExpiredPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PollsActivity.class);
                intent.putExtra("item", 1);
                startActivity(intent);
            }
        });

        btnActivePolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PollsActivity.class);
                intent.putExtra("item", 0);
                startActivity(intent);
            }
        });


        mWordViewModel = ViewModelProviders.of(this).get(HoroViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<HoroData>>() {
            @Override
            public void onChanged(@Nullable final List<HoroData> words) {
                // Update the cached copy of the words in the adapter.
                try {
                    if (words.size()>1){
                        horoData = words;
                        horoDataAdapter = new HoroDataAdapter(MainActivity.this, horoData, MainActivity.this);
                        horoRecyclerView.setAdapter(horoDataAdapter);
                        Log.e("MainActivity", "Loaded data from database....");
                    }
                }catch (NullPointerException e){}
            }
        });


        mWordViewModel.getAllCategories().observe(this, new Observer<List<Categories.CatData>>() {
            @Override
            public void onChanged(List<Categories.CatData> catDataList) {
                try {
                    if (catDataList.size()>1){
                        catData = catDataList;
                        adapder = new CatDataAdapter(MainActivity.this, catData);
                        recyclerView.setAdapter(adapder);
                        Log.e("MainActivity", "Loaded data from database....");
                    }
                }catch (NullPointerException e){}
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        apiClient = retrofit.create(ApiClient.class);

        connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkBuilder = new NetworkRequest.Builder().build();
        networkCallback = new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                Log.e("MainActivity", "Network is available.");
                if (catData == null || horoData ==null){
                    Log.e("MainActivity", "Loading data from server....");
                    getCategoryData();
                    getHoroscopeData();
                }
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
                Log.e("MainActivity", "Network is lost.");
                Toast.makeText(MainActivity.this, "No internet connection is available.", Toast.LENGTH_LONG).show();

            }
        };

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "On start method is called.");
        connectivityManager.registerNetworkCallback(networkBuilder, networkCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "On stop method is called.");
        if (networkCallback != null)
            connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    private void getCategoryData(){
        Call<Categories> call = apiClient.getCategory();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                try {
                    Categories category = response.body();
                    catData = category.getData();
                    for (int i=0; i<catData.size(); i++){
                        mWordViewModel.insertCategories(catData.get(i));
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
            }
        });
    }

    private void getHoroscopeData(){
        Call<Horoscope> call1 = apiClient.getHoroscope();
        call1.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                try {
                    Horoscope horoscope = response.body();
                    horoData = horoscope.data;

                    for (int i=0; i<horoData.size(); i++){
                        mWordViewModel.insertHoro(horoData.get(i));
                    }
                }catch (NullPointerException e){}
            }
            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {

            }
        });
    }

    public void showCategoryDialog(int pos){
        ViewPager mPager;

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_category, null,false);

        mPager =  view.findViewById(R.id.image_viewpager);
        TabLayout tabIndicator =  view.findViewById(R.id.tab_indicator);
        Button btnViewPost = view.findViewById(R.id.btn_show_cat);
        builder.setView(view);
        final AlertDialog ad = builder.show();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        mPager.setAdapter(new SlideImageAdapter(this, catData));
        mPager.setCurrentItem(pos, true);
        tabIndicator.setupWithViewPager(mPager, true);

        btnViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowPostActivity.class));
            }
        });
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        id = horoData.get(position).getId();
        String imageUrl = horoData.get(position).getMphoto();
        String title = horoData.get(position).getSunsign_en();

        Intent horoDetail = new Intent(MainActivity.this, HoroDetailActivity.class);
        horoDetail.putExtra("ID", id);
        horoDetail.putExtra("IMAGE", imageUrl);
        horoDetail.putExtra("TITLE", title);
        startActivity(horoDetail);
    }

}
