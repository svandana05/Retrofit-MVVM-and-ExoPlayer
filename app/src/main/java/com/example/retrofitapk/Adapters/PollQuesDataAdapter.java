package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapk.ModelClasses.PollModel.PollQuesByID;
import com.example.retrofitapk.ModelClasses.PollModel.Polldatum;
import com.example.retrofitapk.ModelClasses.PollModel.PollsCategoy;
import com.example.retrofitapk.R;

import java.util.List;

public class PollQuesDataAdapter extends RecyclerView.Adapter<PollQuesDataAdapter.DataViewHolder> {

    Context context;
    List<Polldatum> pollquesList;

    public PollQuesDataAdapter(Context context, List<Polldatum> pollquesList) {
        this.pollquesList = pollquesList;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poll_ques_item, null);
        PollQuesDataAdapter.DataViewHolder dataViewHolder = new PollQuesDataAdapter.DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(PollQuesDataAdapter.DataViewHolder holder, final int position) {
        // set the data
        holder.tvQues.setText(pollquesList.get(position).getPollquestion());
        holder.rb1.setText(pollquesList.get(position).getPollopitons().get(0).getOptions());
        holder.rb2.setText(pollquesList.get(position).getPollopitons().get(1).getOptions());
        holder.rb3.setText(pollquesList.get(position).getPollopitons().get(2).getOptions());
        holder.rb4.setText(pollquesList.get(position).getPollopitons().get(3).getOptions());
    }

    @Override
    public int getItemCount() {
        return pollquesList.size(); // size of the list items
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView tvQues;
        RadioButton rb1, rb2, rb3, rb4;

        public DataViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tvQues = itemView.findViewById(R.id.tv_ques);
            rb1 = itemView.findViewById(R.id.rb_1);
            rb2 = itemView.findViewById(R.id.rb_2);
            rb3 = itemView.findViewById(R.id.rb_3);
            rb4 = itemView.findViewById(R.id.rb_4);
        }
    }
}
