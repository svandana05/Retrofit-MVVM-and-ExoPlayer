package com.example.retrofitapk;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitapk.Adapters.ActivePollDataAdapter;
import com.example.retrofitapk.Adapters.PollsCatDataAdapter;
import com.example.retrofitapk.ModelClasses.PollModel.AllPolls;
import com.example.retrofitapk.ModelClasses.PollModel.PollsCategoy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivePollFragment extends Fragment implements PollsCatDataAdapter.OnItemClick {
    RecyclerView recyclerView, polldatalist;
    PollsCatDataAdapter pollsCatDataAdapter;
    ActivePollDataAdapter activePollDataAdapter;
    TextView emptyView;

    List<AllPolls.PollData> pollsData;
    List<PollsCategoy.Datum> pollsCatData;
    Integer cat_Id;


    public ActivePollFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expired_poll, container, false);
        recyclerView = rootView.findViewById(R.id.pollscategorylist);
        emptyView = rootView.findViewById(R.id.empty_view);

        polldatalist = rootView.findViewById(R.id.polldatalist);
        getPollsCatListName();
        getPollActiveData();


        return rootView;
    }

    public void getPollActiveData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiClient api = retrofit.create(ApiClient.class);

        Call<AllPolls> call = api.getAllpolls("Active", cat_Id);

        call.enqueue(new Callback<AllPolls>() {
            @Override
            public void onResponse(Call<AllPolls> call, Response<AllPolls> response) {

                AllPolls polls = response.body();
                pollsData =  polls.data;

                polldatalist.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                activePollDataAdapter = new ActivePollDataAdapter(getActivity(), pollsData);
                polldatalist.setAdapter(activePollDataAdapter);
                if (pollsData.isEmpty()) {
                    polldatalist.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                else {
                    polldatalist.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AllPolls> call, Throwable t) {
            }
        });
    }


    public void getPollsCatListName(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiClient api = retrofit.create(ApiClient.class);

        Call<PollsCategoy> call = api.getPollscategory();

        call.enqueue(new Callback<PollsCategoy>() {
            @Override
            public void onResponse(Call<PollsCategoy> call, Response<PollsCategoy> response) {
                PollsCategoy pollsCategoy = response.body();
                pollsCatData =  pollsCategoy.data;

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                pollsCatDataAdapter = new PollsCatDataAdapter(getActivity(), pollsCatData, ActivePollFragment.this);
                recyclerView.setAdapter(pollsCatDataAdapter);

            }

            @Override
            public void onFailure(Call<PollsCategoy> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(Integer catId) {
        cat_Id = catId;
        getPollActiveData();
    }
}
