package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapk.ModelClasses.PollModel.AllPolls;
import com.example.retrofitapk.PollQuestionActivity;
import com.example.retrofitapk.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivePollDataAdapter extends RecyclerView.Adapter<ActivePollDataAdapter.DataViewHolder> {

    Context context;
    List<AllPolls.PollData> pollDataList;

    public ActivePollDataAdapter(Context context, List<AllPolls.PollData> pollDataList ) {
        this.pollDataList = pollDataList;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poll_list_item, null);
        DataViewHolder dataViewHolder = new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        String curDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String polldate = pollDataList.get(position).getStartDate();
        String dayDifference = null;
        try {
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
            //Setting dates
            date1 = dates.parse(curDate);
            date2 = dates.parse(polldate);
            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            dayDifference = Long.toString(differenceDates);

        } catch (Exception exception) {
        }


        // set the data
        holder.tvPollQues.setText(pollDataList.get(position).getPollquestion());
        holder.tvPollDayAgo.setText(dayDifference+" days ago");
        holder.tvPollExpired.setText("Expired  in :- "+pollDataList.get(position).getDay()+" days");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quesID = pollDataList.get(position).getId();
                Intent quesIntent = new Intent(context, PollQuestionActivity.class);
                quesIntent.putExtra("QUES_ID", quesID);
                context.startActivity(quesIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pollDataList.size(); // size of the list items
    }

    public class DataViewHolder extends RecyclerView.ViewHolder  {
        // init the item view's
        TextView tvPollQues, tvPollDayAgo, tvPollExpired;

        public DataViewHolder(View itemView) {
            super(itemView);
            tvPollQues = itemView.findViewById(R.id.tv_poll_ques);
            tvPollDayAgo = itemView.findViewById(R.id.tv_poll_ago);
            tvPollExpired = itemView.findViewById(R.id.tv_poll_expired);
        }

    }
    public static int getDaysDifference(Date fromDate,Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

}
