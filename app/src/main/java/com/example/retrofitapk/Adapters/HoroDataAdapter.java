package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitapk.ModelClasses.HoroModels.HoroData;
import com.example.retrofitapk.ModelClasses.HoroModels.Horoscope;
import com.example.retrofitapk.R;

import java.util.List;

public class HoroDataAdapter extends RecyclerView.Adapter<HoroDataAdapter.DataViewHolder> {

    Context context;
    List<HoroData> dataList;
    public RecyclerViewClickListener itemListener;

    public HoroDataAdapter(Context context, List<HoroData> dataList, RecyclerViewClickListener itemListener) {
        this.dataList = dataList;
        this.context = context;
        this.itemListener = itemListener;
    }

    @Override
    public HoroDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.horo_list_item, null);
        HoroDataAdapter.DataViewHolder dataViewHolder = new HoroDataAdapter.DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(HoroDataAdapter.DataViewHolder holder, final int position) {
        // set the data
        holder.name.setText(dataList.get(position).getSunsign_en());
        //holder.date.setText("Create At: " + catDataList.get(position).getCreated_at());
        Glide.with(context).load(dataList.get(position).getMphoto()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size(); // size of the list items
    }

    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
        TextView name;
        ImageView image;

        public DataViewHolder(final View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.tv_name);
            //date = (TextView) itemView.findViewById(R.id.tv_date);
            image = itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }

    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

}