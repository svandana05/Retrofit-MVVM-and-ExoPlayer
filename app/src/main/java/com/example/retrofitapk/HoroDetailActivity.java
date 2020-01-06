package com.example.retrofitapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitapk.ModelClasses.HoroModels.Data;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscopedetails;
import com.example.retrofitapk.RoomDatabase.HoroDetailsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HoroDetailActivity extends AppCompatActivity {

    Integer id;
    String imageUrl, title;
    ImageView horoImage;
    TextView horoTitle, horoDate;
    TextView descriptionPersonalLife, descriptionProfessional, descriptionHealth, descriptionTravel, descriptionLuck, descriptionEmotions;
    Data dataClass;
    Data.Prediction predictionClass;
    private HoroDetailsViewModel mWordViewModel;
    HoroDetailsViewModel.Factory factory;
    String personalLifeString , professionString , healthString , travelString , luckString, emotionsString, dateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horo_detail);
        viewInitialization();
        //intent data
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            id = extras.getInt("ID");
            imageUrl = extras.getString("IMAGE");
            title = extras.getString("TITLE");
        }

        horoTitle.setText(title);
        Glide.with(this).load(imageUrl).into(horoImage);

        factory = new HoroDetailsViewModel.Factory(getApplication(), id);
        mWordViewModel = new ViewModelProvider(this, factory)
                .get(HoroDetailsViewModel.class);
        mWordViewModel.getmHoroDetails().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                try {
                    if (data!=null&& data.getHoroID()==id){
                        predictionClass = data.getPrediction();
                        dateString = data.getPredictionDate();
                        personalLifeString = predictionClass.getPersonalLife();
                        professionString = predictionClass.getProfession();
                        healthString = predictionClass.getHealth();
                        travelString = predictionClass.getTravel();
                        luckString = predictionClass.getLuck();
                        emotionsString = predictionClass.getEmotions();
                        horoDate.setText(dateString);
                        descriptionPersonalLife.setText(personalLifeString);
                        descriptionProfessional.setText(professionString);
                        descriptionHealth.setText(healthString);
                        descriptionTravel.setText(travelString);
                        descriptionLuck.setText(luckString);
                        descriptionEmotions.setText(emotionsString);
                    }
                } catch (NullPointerException e){ }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiClient api = retrofit.create(ApiClient.class);
        Call<Horoscopedetails> call = api.getHoroscopedetails(id);
        call.enqueue(new Callback<Horoscopedetails>() {
            @Override
            public void onResponse(Call<Horoscopedetails> call, Response<Horoscopedetails> response) {
                try {
                    dataClass = response.body().getData();
                    if (dataClass!=null){
                        dataClass.setHoroID(id);
                        mWordViewModel.insertHoroDetail(dataClass);
                        Log.e("HoroDetailsActivity", "data is inserted- "+dataClass);
                        mWordViewModel.updateHoroDetail(dataClass);
                        Log.e("HoroDetailsActivity", "data is updated-"+dataClass);
                    }
                } catch (NullPointerException e){ }
            }
            @Override
            public void onFailure(Call<Horoscopedetails> call, Throwable t) {
            }
        });


    }


    public void viewInitialization(){
        horoImage = findViewById(R.id.horoImage);
        horoTitle = findViewById(R.id.horoTitle);
        horoDate = findViewById(R.id.horoDate);
        descriptionPersonalLife = findViewById(R.id.description_personal_life);
        descriptionProfessional = findViewById(R.id.description_profession);
        descriptionHealth = findViewById(R.id.description_health);
        descriptionTravel = findViewById(R.id.description_travel);
        descriptionLuck = findViewById(R.id.description_luck);
        descriptionEmotions = findViewById(R.id.description_emotions);
    }
}
