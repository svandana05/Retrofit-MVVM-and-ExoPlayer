package com.example.retrofitapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.retrofitapk.Adapters.ActivePollDataAdapter;
import com.example.retrofitapk.ModelClasses.PollModel.AllPolls;
import com.example.retrofitapk.ModelClasses.PollModel.PollQuesByID;
import com.example.retrofitapk.ModelClasses.PollModel.Polldatum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PollQuestionActivity extends AppCompatActivity {

    Integer ques_id;
    TextView tvQues;
    RadioButton rb1, rb2, rb3, rb4;
    Button btnpollVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_question);
        tvQues = findViewById(R.id.tv_ques);
        rb1 = findViewById(R.id.rb_1);
        rb2 = findViewById(R.id.rb_2);
        rb3 = findViewById(R.id.rb_3);
        rb4 = findViewById(R.id.rb_4);
        btnpollVote = findViewById(R.id.btn_poll_vote);

        //intent data
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            ques_id = extras.getInt("QUES_ID");
        } else {
            ques_id = (Integer) savedInstanceState.getSerializable("QUES_ID");
        }

        getCurrentPollData();


    }

    public void getCurrentPollData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiClient api = retrofit.create(ApiClient.class);

        Call<PollQuesByID> call = api.getCurrentPoll(ques_id);

        call.enqueue(new Callback<PollQuesByID>() {
            @Override
            public void onResponse(Call<PollQuesByID> call, Response<PollQuesByID> response) {

                PollQuesByID currentPoll = response.body();
                List<Polldatum> currentPollData = currentPoll.getPolldata();

                tvQues.setText(currentPollData.get(0).getPollquestion());
                if (currentPollData.get(0).getStatus().equals("In-Active")){
                    rb1.setVisibility(View.GONE);
                    rb2.setVisibility(View.GONE);
                    rb3.setVisibility(View.GONE);
                    rb4.setVisibility(View.GONE);
                    btnpollVote.setVisibility(View.GONE);
                }else if (currentPollData.get(0).getStatus().equals("Active")) {
                    rb1.setText(currentPollData.get(0).getPollopitons().get(0).getOptions());
                    rb2.setText(currentPollData.get(0).getPollopitons().get(1).getOptions());
                    rb3.setText(currentPollData.get(0).getPollopitons().get(2).getOptions());
                    rb4.setText(currentPollData.get(0).getPollopitons().get(3).getOptions());
                }

            }

            @Override
            public void onFailure(Call<PollQuesByID> call, Throwable t) {

            }
        });
    }

}
