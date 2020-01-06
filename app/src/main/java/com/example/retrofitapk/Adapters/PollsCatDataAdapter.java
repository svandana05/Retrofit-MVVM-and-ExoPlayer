package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapk.ModelClasses.PollModel.PollsCategoy;
import com.example.retrofitapk.R;

import java.util.List;

public class PollsCatDataAdapter extends RecyclerView.Adapter<PollsCatDataAdapter.DataViewHolder> {

    Context context;
    List<PollsCategoy.Datum> pollsCatList;
    private OnItemClick mCallback;
    Integer cat_ID;
    int row_index = -1;

    public interface OnItemClick {
        void onClick(Integer catId);
    }

    public PollsCatDataAdapter(Context context, List<PollsCategoy.Datum> pollsCatList, OnItemClick listener) {
        this.mCallback = listener;
        this.pollsCatList = pollsCatList;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.polls_cat_list_item, null);
        PollsCatDataAdapter.DataViewHolder dataViewHolder = new PollsCatDataAdapter.DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        // set the data
        holder.pollsName.setText(pollsCatList.get(position).getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                cat_ID = pollsCatList.get(position).getId();
                mCallback.onClick(cat_ID);
                notifyDataSetChanged();

            }
        });

        if(row_index == position){
            holder.pollsName.setBackgroundColor(Color.parseColor("#FF1744"));
            holder.pollsName.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.pollsName.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.pollsName.setTextColor(Color.parseColor("#FF1744"));
        }

    }

    @Override
    public int getItemCount() {
        return pollsCatList.size(); // size of the list items
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView pollsName;

        public DataViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            pollsName = itemView.findViewById(R.id.btn_polls_name);
        }
    }

}
