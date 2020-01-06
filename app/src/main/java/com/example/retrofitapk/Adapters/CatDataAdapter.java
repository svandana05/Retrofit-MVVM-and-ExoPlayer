package com.example.retrofitapk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitapk.MainActivity;
import com.example.retrofitapk.ModelClasses.Categories;
import com.example.retrofitapk.R;

import java.util.List;

public class CatDataAdapter extends RecyclerView.Adapter<CatDataAdapter.DataViewHolder> {

    Context context;
    List<Categories.CatData> catDataList;

    public CatDataAdapter(Context context, List<Categories.CatData> catDataList) {
        this.catDataList = catDataList;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        DataViewHolder dataViewHolder = new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        // set the data
        holder.name.setText(catDataList.get(position).getCategory_en());
        //holder.date.setText("Create At: " + catDataList.get(position).getCreated_at());
        Glide.with(context).load(catDataList.get(position).getPhoto()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showCategoryDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catDataList.size(); // size of the list items
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public DataViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.tv_name);
            //date = (TextView) itemView.findViewById(R.id.tv_date);
            image = itemView.findViewById(R.id.iv_image);
        }
    }
}
